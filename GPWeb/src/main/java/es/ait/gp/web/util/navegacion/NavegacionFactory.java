/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import es.ait.gp.core.documentacion.Documentacion;
import es.ait.gp.core.documentacion.DocumentacionDAO;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aitkiar
 */
@Stateless
public class NavegacionFactory
{
    @EJB
    private ProyectosDAO daoProyectos;
    @EJB
    private TareasDAO daoTareas;
    @EJB
    private DocumentacionDAO daoDocumentacion;
    
    public Navegacion getNavegacion( Object objeto )
    {
        if ( objeto != null )
        {
            if ( objeto instanceof Proyectos )
            {
                return new NavegacionProyecto(objeto);
            }
            else if ( objeto instanceof Tareas )
            {
                return new NavegacionTarea( objeto );
            }
            else if ( objeto instanceof Documentacion )
            {
                return new NavegacionDocumentacion( objeto );
            }
        }
        return new NavegacionHome( null );
    }
    
    public Navegacion getNavegacion( HttpServletRequest request ) throws Exception
    {
        if ( request != null )
        {
            if ( request.getServletPath().equals("/protegido/proyectos/proyectosDetalle.xhtml") && request.getQueryString() != null )
            {
                return new NavegacionProyecto ( daoProyectos.find( new Integer( request.getParameter("proyId"))));
            }
            else if ( request.getServletPath().equals("/protegido/tareas/tareasDetalle.xhtml") && request.getQueryString() != null )
            {
                return new NavegacionTarea( daoTareas.find( new Integer( request.getParameter("tareId"))));
            }
            else if ( request.getServletPath().equals( "/protegido/documentacion/documentacionDetalle.xhtml") && request.getQueryString() != null )
            {
                return new NavegacionDocumentacion( daoDocumentacion.find( new Integer( request.getParameter("docuId"))));
            }
        }
        return null;
    }
}
