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
import es.ait.gp.core.proyectos.ProyectosFiltro;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.web.util.RequestUtils;
import es.ait.gp.web.util.navegacion.PilaNavegacionInterface;
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
public class ProyectosDetalleBean
{
    private Proyectos proyecto;
    private String filtro;
    private List<EstadoProyectos> estados;
    private List<Proyectos> subproyectos;
    private List<Tareas> tareas;
    
    @EJB
    ProyectosDAO dao;
    
    
    @EJB
    TareasDAO daoTareas;
    
    public ProyectosDetalleBean()
    {
    }
    
    @PostConstruct
    private void inicializacion()
    {
        try
        {
            setEstados(EstadoProyectosMap.getInstance().getAll());
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            if ( RequestUtils.getRequestAttribute("proyecto") != null )
            {
                proyecto = ( Proyectos ) RequestUtils.getRequestAttribute("proyecto");
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

            buscarSubproyectos();
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
            if ( proyecto != null && proyecto.getProyId() != null )
            {
                ((PilaNavegacionInterface)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("navegacion")).navegar( proyecto );
            }
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
     * @return the subproyectos
     */
    public List<Proyectos> getSubproyectos()
    {
        return subproyectos;
    }

    /**
     * @param subproyectos the subproyectos to set
     */
    public void setSubproyectos(List<Proyectos> subproyectos)
    {
        this.subproyectos = subproyectos;
    }

    /**
     * @return the tareas
     */
    public List<Tareas> getTareas()
    {
        return tareas;
    }

    /**
     * @param tareas the tareas to set
     */
    public void setTareas(List<Tareas> tareas)
    {
        this.tareas = tareas;
    }

    public String guardar() throws Exception
    {
        if ( proyecto == null )
        {
            proyecto = new Proyectos();
        }
        if ( proyecto.getProyId() == null )
        {
            Usuarios usuario = ( Usuarios )((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession( false )).getAttribute("usuario");
            proyecto.setUsuaIdAlta( usuario );
            proyecto.setProyFxAlta( new Date( System.currentTimeMillis()));
            dao.create( proyecto );
        }
        else
        {
            dao.edit( proyecto );
        }
        return "proyectosBuscarListado.xhtml";
    }
    
    public String cancelar() throws Exception
    {
        return "proyectosBuscarListado";
    }
    
    public void buscarSubproyectos() throws Exception
    {
        if ( proyecto != null )
        {
            ProyectosFiltro filtroSubproyectos = new ProyectosFiltro();
            filtroSubproyectos.setProyPadre( proyecto );
            filtroSubproyectos.setPrimerNivel( false );
            setSubproyectos(dao.findByFilter( filtroSubproyectos ));
        }
    }
    
    public String nuevoSubproyecto() throws Exception
    {
        if ( proyecto != null )
        {
            RequestUtils.setRequestAttribute( "subproyecto", proyecto );
            RequestUtils.setRequestAttribute( "vieneDe", "detalle" );
            return "proyectosDetalleForm";
        }
        return null;
    }
    
    public String irSubproyecto( int proyId )
    {
        return "proyectosDetalle.xhtml?faces-redirect=true&proyId=" + proyId;
    }
    
    public void buscarTareas() throws Exception
    {
        if ( proyecto != null )
        {
            Tareas filtroTareas = new Tareas();
            filtroTareas.setProyId( proyecto );
            
            setTareas(daoTareas.findByFilter( filtroTareas ));
        }
    }
    
    public String editar() throws Exception
    {
        if ( proyecto != null )
        {
            RequestUtils.setRequestAttribute("vieneDe", "detalle");
            return "proyectosDetalleForm";
        }
        return null;
    }
    
    public String nuevaTarea() throws Exception
    {
        if ( proyecto != null )
        {
            return "/protegido/tareas/tareasDetalleForm.xhtml?faces-redirect=true&proyId=" + proyecto.getProyId();
        }
        return null;
    }
    
    public String irTarea( int tareId )
    {
        return "/protegido/tareas/tareasDetalle.xhtml?faces-redirect=true&tareId=" + tareId;
    }

    public String nuevoDocumento() throws Exception
    {
        if ( proyecto != null )
        {
            return "/protegido/documentacion/documentacionDetalleForm.xhtml?faces-redirect=true&proyId=" + proyecto.getProyId();
        }
        return null;
    }
    
    public String detalleDocumento( Integer docuId ) throws Exception
    {
        return "/protegido/documentacion/documentacionDetalle.xhtml?faces-redirect=true&docuId=" + docuId;
    }
}
