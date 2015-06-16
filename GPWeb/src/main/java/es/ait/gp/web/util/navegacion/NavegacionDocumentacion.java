/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import es.ait.gp.core.documentacion.Documentacion;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aitkiar
 */
public class NavegacionDocumentacion extends Navegacion
{

    public NavegacionDocumentacion( Object objeto )
    {
        super ( objeto );
        Documentacion documentacion = ( Documentacion ) getObjeto();
        setTexto( documentacion.getDocuNombre());
        setUrl("/protegido/documentacion/documentacionDetalle.xhtml?"
            + "faces-redirect=true&docuId=" + documentacion.getDocuId());
    }
    
    @Override
    public boolean mismoObjeto(HttpServletRequest request)
    {
        Documentacion documentacion = ( Documentacion ) getObjeto();
        if ( request.getServletPath().equals("/protegido/documentacion/documentacionDetalle.xhtml"))
        {
            return documentacion.getDocuId().toString().equals( request.getParameter("docuId"));
        }
        return false;
    }
    
    @Override
    public boolean mismoObjeto(Object objeto)
    {
        Documentacion documentacion = ( Documentacion ) getObjeto();
        if ( objeto instanceof Documentacion )
        {
            return documentacion.getDocuId().intValue() == ((Documentacion)objeto).getDocuId().intValue();
        }
        return false;
    }
    
}
