/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Objeto que guarda los niveles de navegación por los que hemos pasado en forma de pila
 * 
 * Las operaciones típicas de la pila push, pop y head están representadas por los métodos
 * navegar, anterior y actual.
 * 
 * @author aitkiar
 */
@Stateful
public class PilaNavegacion implements PilaNavegacionInterface
{
    private List<Navegacion> pila;
    
    @Inject
    NavegacionFactory factory;
    
    public PilaNavegacion()
    {
        pila = new ArrayList<>();
    }
    
    @Override
    public void reset()
    {
        pila = new ArrayList<>();
    }
    
    /**
     * Añade un nuevo objeto a la pila de navegación tras comprobar que no es una
     * repetición del último objeto que hay en la pila.
     * 
     * @param objeto 
     */
    @Override
    public void navegar( Object objeto ) throws IllegalArgumentException
    {
        if ( objeto == null )
        {
            throw new IllegalArgumentException( "Objeto de navegación vacío");
        }
        
        if (pila.isEmpty() || !pila.get( pila.size() -1 ).mismoObjeto( objeto ))
        {
            pila.add( factory.getNavegacion( objeto ) );
        }
    }
    
    
    /**
     * Devuelve la url para el acceso directo al objeto en la posición pos de la pila
     * Si el objeto es de un tipo desconocido, o si la posición es negativa devuelve
     * un enlace a la página principal del usuario.
     * @param pos
     * @return 
     */
    private Navegacion getURL( int pos )
    {
        if ( pila.size() > pos && pos >= 0 )
        {
            return pila.get( pos );
        }
        return factory.getNavegacion( (Object)null );
    }

    /**
     * Devuelve la url al objeto que hay en la pila, o a la home si la lista está vacía.
     * @return 
     */
    @Override
    public Navegacion head()
    {
        return getURL( pila.size() - 1 );
    }
    
    /**
     * Elimina el objeto acutal de la pila y devuelve la url al objeto anterior de la misma
     * 
     * @return 
     */
    @Override
    public Navegacion anterior()
    {
       if ( !pila.isEmpty() ) 
       {
           pila.remove( pila.size() - 1 );
       }
       return head();
    }
    
    @Override
    public List<Navegacion> getMenu()
    {
        List<Navegacion> resultado = new ArrayList<>();
        for ( int i = -1; i < pila.size(); i ++ )
        {
            resultado.add( getURL( i ));
        }
        return resultado;
    }
    
    @Override
    public void checkNavegacion( HttpServletRequest request ) throws Exception
    {
        List<Navegacion> menu = getMenu();
        boolean encontrado = false;
        
        for ( int i = 0; !encontrado && i < menu.size(); i++ )
        {
            if ( menu.get( i ).mismoObjeto(request))
            {
                encontrado = true;
                for ( int j = pila.size() - 1; j >= i; j-- )
                {
                    pila.remove( j );
                }
                break;
            }
        }
        
        if ( !encontrado )
        {
            Navegacion objeto = factory.getNavegacion( request );
            if ( objeto != null )
            {
                pila.add( objeto );
            }
        }
    }

}
