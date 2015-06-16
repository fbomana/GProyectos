/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Clase con utilidades para gestionar la request.
 *
 * @author aitkiar
 */
public class RequestUtils
{

    /**
     * Método que serializa un objeto y devuelve su transformación en base64.
     *
     * @param objeto el objeto que queremos codificar.
     * @return null si no se pasó ningún objeto o la cadena codificada como
     * Base64 resultante de serializar el objeto.
     */
    public static String encodeObject(Object objeto) throws Exception
    {
        if (objeto == null)
        {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new ObjectOutputStream(out).writeObject(objeto);
        return Base64.getEncoder().encodeToString( out.toByteArray());
    }

    /**
     * Método que deserializa un String codificado como Base64 en el objeto que
     * contiene. Requiere que la serialización se haya realizado meidante el
     * método encodeObject.
     *
     * @param objeto String con la codificación en Base64 de la serialización
     * del objeto.
     * @return El objeto deserializado o null si no se pasó ninguna cadena como
     * parámetro.
     * @throws Exception
     */
    public static Object decodeObject(String objeto) throws Exception
    {
        if (objeto == null)
        {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(objeto));
        return new ObjectInputStream(in).readObject();
    }
    
    public static void pintarParametrosRequest()
    {
        Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Iterator<String> nombres = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterNames();
        
        while ( nombres.hasNext() )
        {
            String nombre = nombres.next();
            System.out.println( nombre + "=[" + parametros.get( nombre ) + "]");
        }
    }
    
    public static void pintarAtributosRequest()
    {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Enumeration<String> nombres = request.getAttributeNames();
        
        while ( nombres.hasMoreElements())
        {
            String nombre = nombres.nextElement();
            System.out.println( nombre + "=[" + request.getAttribute( nombre ) + "]");
        }
    }
    
    public static Object getRequestAttribute( String name )
    {
        return ((HttpServletRequest )FacesContext.getCurrentInstance().getExternalContext().getRequest()).getAttribute( name );
    }
    
    public static void setRequestAttribute( String name, Object object )
    {
        ((HttpServletRequest )FacesContext.getCurrentInstance().getExternalContext().getRequest()).setAttribute(name, object);
    }
    
    public static String getRequestParameter( String name )
    {
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter( name );
    }
    
    public static String[] getRequestParameterValues( String name )
    {
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameterValues( name );
    }
    
    public static Object getSessionAttribute( String name )
    {
        return ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession( false )).getAttribute( name );
    }
    
    public static void setSessionAttribute( String name, Object value )
    {
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession( false )).setAttribute( name, value );
    }
}
