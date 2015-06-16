/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.principal;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import es.ait.gp.core.usuarios.Usuarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Bean que obtiene y muesta la información de la pantalla general.
 * @author aitkiar
 */
@Named
@RequestScoped
public class HomeUsuarioBean
{
    private List<Tareas> tareas;
    private List<Proyectos> proyectos;
    
    @EJB
    private TareasDAO dao;
    
    @EJB
    private ProyectosDAO daoProyectos;
    
    public HomeUsuarioBean()
    {
    }
    
    /**
     * Obtiene la lista de tareas del usuario en sesion. Hay que cambiarlo una vez
     * que tengamos afinada la búsqueda de proyectos y tareas.
     */
    @PostConstruct
    public void obtenerDatos()
    {
        try
        {
            Usuarios usuario = ( Usuarios )((HttpSession)FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("usuario");
            if ( usuario != null )
            {
                setTareas(dao.findByUser( usuario ));
                setProyectos( daoProyectos.findByUser( usuario, true ));
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
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
}
