/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import es.ait.gp.core.tareas.Tareas;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aitkiar
 */
public class NavegacionTarea extends Navegacion
{

    public NavegacionTarea( Object objeto )
    {
        super ( objeto );
        Tareas tarea = ( Tareas ) getObjeto();
        setTexto( tarea.getTareNombre() );
        setUrl("/protegido/tareas/tareasDetalle.xhtml?"
            + "faces-redirect=true&tareId=" + tarea.getTareId());
    }
    
    @Override
    public boolean mismoObjeto(HttpServletRequest request)
    {
        Tareas tarea = ( Tareas ) getObjeto();
        if ( request.getServletPath().equals("/protegido/tareas/tareasDetalle.xhtml"))
        {
            return tarea.getTareId().toString().equals( request.getParameter("tareId"));
        }
        return false;
    }
    
    @Override
    public boolean mismoObjeto(Object objeto)
    {
        Tareas tarea = ( Tareas ) getObjeto();
        if ( objeto instanceof Tareas )
        {
            return tarea.getTareId().intValue() == ((Tareas)objeto).getTareId().intValue();
        }
        return false;
    }
}
