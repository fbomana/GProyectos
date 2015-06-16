/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.tareas;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import es.ait.gp.web.util.RequestUtils;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author aitkiar
 */
@Named
@RequestScoped
public class TareasDetalleBean
{
    private Tareas tarea;
    private Integer tareId;
    
    @EJB
    TareasDAO dao;

    public TareasDetalleBean()
    {
    }
    
    @PostConstruct
    private void inicializacion()
    {
        try
        {
            String id = RequestUtils.getRequestParameter("tareId");
            if ( id == null )
            {
                id = RequestUtils.getRequestParameter("form:tareId");
            }
            
            if ( id != null )
            {
                setTarea(dao.find( new Integer( id )));
            }
            else
            {
                setTarea(new Tareas());
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @return the tarea
     */
    public Tareas getTarea()
    {
        return tarea;
    }

    /**
     * @param tarea the tarea to set
     */
    public void setTarea(Tareas tarea)
    {
        this.tarea = tarea;
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
    public void setTareId(Integer tareId)
    {
        this.tareId = tareId;
    }
    
    /**
     * Función que redirige a la ventana de edición de tareas.
     * 
     * @return 
     */
    public String editar()
    {
        return "tareasDetalleForm.xhtml?faces-redirect=true&tareId=" + tarea.getTareId();
    }
    
    public String nuevoDocumento() throws Exception
    {
        if ( tarea != null )
        {
            return "/protegido/documentacion/documentacionDetalleForm.xhtml?faces-redirect=true&tareId=" + tarea.getTareId();
        }
        return null;
    }
    
    public String detalleDocumento( Integer docuId ) throws Exception
    {
        return "/protegido/documentacion/documentacionDetalle.xhtml?faces-redirect=true&docuId=" + docuId;
    }
}
