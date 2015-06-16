/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aitkiar
 */
public class NavegacionHome extends Navegacion
{

    public NavegacionHome( Object objeto )
    {
        super( objeto );
        setUrl( "/protegido/principal.xhtml" );
        setTexto( "Home" );
    }
    
    @Override
    public boolean mismoObjeto(HttpServletRequest request)
    {
        return request.getServletPath().equals( getUrl());
    }

    @Override
    public boolean mismoObjeto(Object objeto)
    {
        return objeto == null;
    }
    
}
