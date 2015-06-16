/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import es.ait.gp.core.proyectos.Proyectos;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aitkiar
 */
public class NavegacionProyecto extends Navegacion
{

    public NavegacionProyecto( Object objeto )
    {
        super ( objeto );
        Proyectos proyecto = ( Proyectos ) getObjeto();
        setTexto( proyecto.getProyNombre() );
        setUrl("/protegido/proyectos/proyectosDetalle.xhtml?"
            + "faces-redirect=true&proyId=" + proyecto.getProyId());
    }
    
    @Override
    public boolean mismoObjeto(HttpServletRequest request)
    {
        Proyectos proyecto = ( Proyectos ) getObjeto();
        if ( request.getServletPath().equals("/protegido/proyectos/proyectosDetalle.xhtml"))
        {
            return proyecto.getProyId().toString().equals( request.getParameter("proyId"));
        }
        return false;
    }
    
    @Override
    public boolean mismoObjeto(Object objeto)
    {
        Proyectos proyecto = ( Proyectos ) getObjeto();
        if ( objeto instanceof Proyectos )
        {
            return proyecto.getProyId().intValue() == ((Proyectos)objeto).getProyId().intValue();
        }
        return false;
    }
    
}
