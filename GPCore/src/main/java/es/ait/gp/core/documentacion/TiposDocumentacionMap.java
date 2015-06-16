/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aitkiar
 */
public class TiposDocumentacionMap
{
    private List<TiposDocumentacion> tipos;
    private Map<Integer, TiposDocumentacion> mapa;

    private static TiposDocumentacionMap instance;

    private TiposDocumentacionMap(List<TiposDocumentacion> tipos)
    {
        this.tipos = tipos;
        this.mapa = new HashMap<>();
        for (int i = 0; tipos != null && i < tipos.size(); i++)
        {
            mapa.put(tipos.get(i).getTdocId(), tipos.get(i));
        }
    }

    public static TiposDocumentacionMap getInstance(List<TiposDocumentacion> tipos)
    {
        if (instance == null)
        {
            instance = new TiposDocumentacionMap(tipos);
        }
        return instance;
    }
    
    public static void reset( List<TiposDocumentacion> tipos )
    {
        instance = new TiposDocumentacionMap(tipos);
    }

    public static TiposDocumentacionMap getInstance()
    {
        if (instance == null)
        {
            instance = new TiposDocumentacionMap( null );
        }
        return instance;
    }
    
    public void put ( TiposDocumentacion tipo )
    {
        if ( tipo != null )
        {
            if ( mapa.get( tipo.getTdocId()) == null )
            {
                tipos.add ( tipo );
                mapa.put( tipo.getTdocId(), tipo );
            }
                
        }
    }
    
    public TiposDocumentacion get( Integer tipo )
    {
        if ( tipo != null )
        {
            return mapa.get( tipo );
        }
        return null;
    }
    
    /**
     * devuelve un duplicado de la lista de todos los tipos.
     * @return 
     */
    public List<TiposDocumentacion> getAll()
    {
        return new ArrayList<>( tipos );
    }
}
