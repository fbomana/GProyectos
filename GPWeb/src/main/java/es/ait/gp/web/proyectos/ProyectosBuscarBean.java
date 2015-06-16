/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.proyectos;

import es.ait.gp.core.proyectos.EstadoProyectos;
import es.ait.gp.core.proyectos.EstadoProyectosDAO;
import es.ait.gp.core.proyectos.EstadoProyectosMap;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.proyectos.ProyectosFiltro;
import es.ait.gp.web.util.RequestUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Bean para la búsqueda de proyectos.
 * @author aitkiar
 */
@Named
@RequestScoped
public class ProyectosBuscarBean
{
    private String nombre;
    private String descripcion;
    private String estado;
    private Boolean primerNivel = true;
    private ProyectosFiltro filtro;
    private List<Proyectos> proyectos;
    private List<EstadoProyectos> estados;
    
    @EJB
    private ProyectosDAO dao;

    
    @PostConstruct
    private void postConstruct()
    {
        try
        {
            estados = EstadoProyectosMap.getInstance().getAll();
            
            RequestUtils.pintarParametrosRequest();
            String aux = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("filtro");
            if ( aux == null )
            {
                aux = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:filtro");
            }
            
            if ( aux != null )
            {
                setFiltro( aux );
                nombre = filtro.getProyNombre();
                descripcion = filtro.getProyDescripcion();
                estado = filtro.getProyEstado();
                primerNivel = filtro.getPrimerNivel();
                filtro = null;
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * @return the nivel
     */
    public String getEstado()
    {
        return estado;
    }

    /**
     * @param estado the nivel to set
     */
    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    /**
     * @return the primerNivel
     */
    public Boolean getPrimerNivel()
    {
        return primerNivel;
    }

    /**
     * @param primerNivel the primerNivel to set
     */
    public void setPrimerNivel(Boolean primerNivel)
    {
        this.primerNivel = primerNivel;
    }
  
    /**
     * @return the filtro
     * @throws java.lang.Exception
     */
    public String getFiltro() throws Exception
    {
        return RequestUtils.encodeObject( filtro );
    }

    /**
     * @param filtro the filtro to set
     * @throws java.lang.Exception
     */
    public void setFiltro( String filtro ) throws Exception
    {
        this.filtro = (ProyectosFiltro)RequestUtils.decodeObject( filtro );
    }

    /**
     * @return the proyectos
     */
    public List<Proyectos> getProyectos()
    {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(List<Proyectos> proyectos)
    {
        this.proyectos = proyectos;
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
     * Resetea el estado del formulario
     */
    public void limpiar()
    {
        nombre = null;
        descripcion = null;
        estado = null;
        primerNivel = true;
    }
    
    public String buscar() throws Exception
    {
        filtro = new ProyectosFiltro();
        filtro.setProyNombre(nombre);
        filtro.setProyDescripcion(descripcion);
        filtro.setProyEstado( estado );
        filtro.setPrimerNivel( primerNivel );
        proyectos = dao.findByFilter( filtro );
        return "proyectosBuscarListado";        
    }
}
