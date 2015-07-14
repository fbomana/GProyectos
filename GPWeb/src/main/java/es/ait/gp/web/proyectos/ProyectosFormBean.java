/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.proyectos;

import es.ait.gp.core.proyectos.EstadoProyectos;
import es.ait.gp.core.proyectos.EstadoProyectosMap;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.proyectos.ProyectosGestorRemote;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.web.util.RequestUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Bean que gestiona la pantalla de detalle de proyecto. Mantiene una variable 
 * interna proyecto con el proyecto que se está viendo, o un proyecto en blanco, 
 * si se trata de un proyecto nuevo.
 * 
 * @author aitkiar
 */
@Named
@RequestScoped
public class ProyectosFormBean
{
    private Proyectos proyecto;
    private String filtro;
    private String vieneDe;
    private String accion;
    private List<EstadoProyectos> estados;
    
    @EJB
    ProyectosDAO dao;
    
    @EJB
    ProyectosGestorRemote daoG;
    
    public ProyectosFormBean()
    {
    }
    
    @PostConstruct
    private void inicializacion()
    {
        try
        {
            setEstados(EstadoProyectosMap.getInstance().getAll());
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            if ( RequestUtils.getRequestAttribute("vieneDe") != null )
            {
                vieneDe = RequestUtils.getRequestAttribute("vieneDe").toString();
            }
            
            if ( RequestUtils.getRequestAttribute("subproyecto") != null)
            {
                proyecto = new Proyectos();
                proyecto.setProyPadre( (Proyectos) RequestUtils.getRequestAttribute("subproyecto") );
                proyecto.setProyEstado( EstadoProyectosMap.getInstance().get("INICIADO"));
            }
            else
            {
                if ( parametros.get("proyId") != null && !"".equals( parametros.get("proyId")))
                {
                    proyecto = dao.find( new Integer( parametros.get("proyId")));
                }
                else if ( parametros.get("form:proyId") != null && !"".equals( parametros.get("form:proyId")))
                {
                    proyecto = dao.find( new Integer( parametros.get("form:proyId")));
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( proyecto == null )
            {
                proyecto = new Proyectos();
                proyecto.setProyEstado( EstadoProyectosMap.getInstance().get("INICIADO"));
            }
//            if ( proyecto != null && proyecto.getProyId() != null )
//            {
//                ((PilaNavegacion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("navegacion")).navegar( proyecto );
//            }
        }
    }

    /**
     * @return the proyecto
     */
    public Proyectos getProyecto()
    {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyectos proyecto)
    {
        this.proyecto = proyecto;
    }

    /**
     * @return the filtro
     */
    public String getFiltro()
    {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro)
    {
        this.filtro = filtro;
    }
    
    /**
     * @return the estados
     */
    public List<EstadoProyectos> getEstados()
    {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(List<EstadoProyectos> estados)
    {
        this.estados = estados;
    }

    /**
     * @return the vieneDe
     */
    public String getVieneDe()
    {
        return vieneDe;
    }

    /**
     * @param vieneDe the vieneDe to set
     */
    public void setVieneDe(String vieneDe)
    {
        this.vieneDe = vieneDe;
    }

    /**
     * @return the accion
     */
    public String getAccion()
    {
        if ( accion == null )
        {
            if ( proyecto.getProyId() != null )
            {
                accion = "edicion";
            }
            else
            {
                accion = "alta";
            }
        }
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion( String accion )
    {
        this.accion = accion;
    }

    public String guardar() throws Exception
    {
        if ( proyecto == null )
        {
            proyecto = new Proyectos();
        }
        Usuarios usuario = ( Usuarios )((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession( false )).getAttribute("usuario");        
        if ( proyecto.getProyId() == null )
        {
            proyecto.setUsuaIdAlta( usuario );
            proyecto.setProyFxAlta( new Date( System.currentTimeMillis()));
            proyecto = daoG.nuevoProyecto( proyecto, usuario );
            accion = "alta";
        }
        else
        {
            daoG.modificarProyecto( proyecto, usuario );
            accion = "edicion";
        }
        if ( "detalle".equals( vieneDe ) )
        {
            return "proyectosDetalle?faces-redirect=true&proyId=" + proyecto.getProyId();
        }
        return "proyectosBuscarListado.xhtml";
    }
    
    public String cancelar() throws Exception
    {
        // El boton lleva un inmediate, por lo que no se carga el parámetro vieneDe y lo tenemos que hace manualmente.
        if ( vieneDe == null )
        {
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            vieneDe = parametros.get("form:vieneDe");
        }
        if ( "detalle".equals( vieneDe ) )
        {
            if ( proyecto.getProyPadre() != null && "alta".equals( accion ))
            {
                RequestUtils.setRequestAttribute("proyecto", proyecto.getProyPadre());
            }
            return "proyectosDetalle";
        }
        return "proyectosBuscarListado";
    }
}
