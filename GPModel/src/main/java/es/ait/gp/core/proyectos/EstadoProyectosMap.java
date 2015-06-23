/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.proyectos.EstadoProyectos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que mantiene una lista de los estados para evitar acceder a BBDD a
 * datos que cambian esporádicamente.
 *
 * @author aitkiar
 */
public class EstadoProyectosMap
{

    private final List<EstadoProyectos> estados;
    private final Map<String, EstadoProyectos> mapa;

    private static EstadoProyectosMap instance;

    private EstadoProyectosMap(List<EstadoProyectos> estados)
    {
        this.estados = estados;
        this.mapa = new HashMap<>();
        for (int i = 0; estados != null && i < estados.size(); i++)
        {
            mapa.put(estados.get(i).getEsprEstado(), estados.get(i));
        }
    }

    public static EstadoProyectosMap getInstance(List<EstadoProyectos> estados)
    {
        if (instance == null)
        {
            instance = new EstadoProyectosMap(estados);
        }
        return instance;
    }

    public static EstadoProyectosMap getInstance()
    {
        if (instance == null)
        {
            instance = new EstadoProyectosMap( null );
        }
        return instance;
    }
    
    public void put ( EstadoProyectos estado )
    {
        if ( estado != null )
        {
            if ( mapa.get( estado.getEsprEstado()) == null )
            {
                estados.add ( estado );
                mapa.put( estado.getEsprEstado(), estado );
            }
                
        }
    }
    
    public EstadoProyectos get( String estado )
    {
        if ( estado != null )
        {
            return mapa.get( estado );
        }
        return null;
    }
    
    /**
     * devuelve un duplicado de la lista de todos los estados.
     * @return 
     */
    public List<EstadoProyectos> getAll()
    {
        return new ArrayList<>( estados );
    }
}
