/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.tareas;

import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import es.ait.gp.core.tareas.TareasGestorRemote;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.web.util.RequestUtils;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Bean que gestiona el alta y edición de tareas.
 *
 * @author aitkiar
 */
@Named
@RequestScoped
public class TareasFormBean
{
    private String tareNombre;
    private String tareDescripcion;
    private Integer tareId;
    private String proyId;
    
    @EJB
    TareasDAO dao;
    
    @EJB
    ProyectosDAO daoProyectos;
    
    @EJB
    TareasGestorRemote gestor;
    
    public TareasFormBean()
    {
    }
    
    @PostConstruct
    private void postConstruct()
    {
        String id = RequestUtils.getRequestParameter("tareId");
        if ( id == null )
        {
            id = RequestUtils.getRequestParameter("form:tareId");
        }
        if ( id != null && !"".equals( id.trim() ) && RequestUtils.getRequestParameter("form:tareNombre") == null )
        {
            try
            {
                Tareas tarea = dao.find( new Integer( id ) );
                tareId = tarea.getTareId();
                tareNombre = tarea.getTareNombre();
                tareDescripcion = tarea.getTareDescripcion();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        else
        {
            if ( RequestUtils.getRequestParameter( "proyId" ) != null )
            {
                proyId = RequestUtils.getRequestParameter( "proyId" );
            }
        }
        
    }


    /**
     * @return the tareNombre
     */
    public String getTareNombre()
    {
        return tareNombre;
    }

    /**
     * @param tareNombre the tareNombre to set
     */
    public void setTareNombre(String tareNombre)
    {
        this.tareNombre = tareNombre;
    }

    /**
     * @return the tareDescripcion
     */
    public String getTareDescripcion()
    {
        return tareDescripcion;
    }

    /**
     * @param tareDescripcion the tareDescripcion to set
     */
    public void setTareDescripcion(String tareDescripcion)
    {
        this.tareDescripcion = tareDescripcion;
    }

    /**
     * @return the tareId
     */
    public Integer getTareId()
    {
        return tareId;
    }

    /**
     * @param tareId the tareId to set
     */
    public void setTareId( Integer tareId)
    {
        this.tareId = tareId;
    }


    /**
     * @return the proyId
     */
    public String getProyId()
    {
        return proyId;
    }

    /**
     * @param proyId the proyId to set
     */
    public void setProyId( String proyId )
    {
        this.proyId = proyId;
    }

    public String guardar() throws Exception
    {
        Tareas tarea;
        
        Usuarios usuario = ( Usuarios ) RequestUtils.getSessionAttribute("usuario");
        if ( tareId == null )
        {
            tarea = new Tareas();
            tarea.setTareNombre( tareNombre );
            tarea.setTareDescripcion( tareDescripcion );
            tarea.setUsuaIdAlta( (Usuarios) RequestUtils.getSessionAttribute( "usuario"));
            tarea.setProyId( daoProyectos.find( new Integer( proyId )));
            tarea = gestor.nuevaTarea( tarea, usuario );
        }
        else
        {
            tarea = dao.find( tareId ); 
            tarea.setTareNombre(tareNombre);
            tarea.setTareDescripcion(tareDescripcion);
            gestor.modificarTarea( tarea, usuario );
        }
        return "tareasDetalle.xhtml?faces-redirect=true&tareId=" + tarea.getTareId();
    }
    
    public String cancelar() throws Exception
    {
        if ( tareId != null && !"".equals( tareId ))
        {
            return "tareasDetalle.xhtml?faces-redirect=true&tareId=" + tareId;
        }
        return "/protegido/proyectos/proyectosDetalle.xhtml?faces-redirect=true&proyId=" + proyId;
    }
}
