/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aitkiar
 */
public class JPATestResult
{
    private List listado;
    private List<Method> metodos;
    private List<Map<String, Object>> listadoExpandido;
    private Exception excepcion;
    
    public JPATestResult( List listado )
    {
        this.listado = listado;
        if ( listado != null && ! listado.isEmpty())
        {
            Class clase = listado.get( 0).getClass();
            Method[] metodos =  clase.getDeclaredMethods();
            this.metodos = new ArrayList<>();
            for ( Method metodo : metodos )
            {
                if ( metodo.getName().startsWith("get"))
                {
                    this.metodos.add( metodo );
                }
            }
            
            listadoExpandido = new ArrayList<>();
            for ( int i = 0; i < listado.size(); i++ )
            {
                Map<String, Object> mapa = new HashMap<>();
                for ( Method metodo : this.metodos )
                {
                    try
                    {
                        mapa.put( metodo.getName(), metodo.invoke( listado.get(i) ));
                    }
                    catch ( IllegalAccessException | InvocationTargetException e )
                    {
                        mapa.put( metodo.getName(), "");
                    }
                }
                listadoExpandido.add( mapa );
            }
        }
    }
    
    public JPATestResult( Exception e )
    {
        this.excepcion = e;
    }

    /**
     * @return the metodos
     */
    public List<Method> getMetodos()
    {
        return metodos;
    }

    /**
     * @param metodos the metodos to set
     */
    public void setMetodos(List<Method> metodos)
    {
        this.metodos = metodos;
    }

    /**
     * @return the listadoExpandido
     */
    public List<Map<String, Object>> getListadoExpandido()
    {
        return listadoExpandido;
    }

    /**
     * @param listadoExpandido the listadoExpandido to set
     */
    public void setListadoExpandido(List<Map<String, Object>> listadoExpandido)
    {
        this.listadoExpandido = listadoExpandido;
    }

    /**
     * @return the excepcion
     */
    public Exception getExcepcion()
    {
        return excepcion;
    }

    /**
     * @param excepcion the excepcion to set
     */
    public void setExcepcion(Exception excepcion)
    {
        this.excepcion = excepcion;
    }
}
