/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.util.Comparator;

/**
 *
 * @author aitkiar
 */
public class VersionesFxComparator implements Comparator<Versiones>
{

    @Override
    public int compare(Versiones o1, Versiones o2)
    {
        if ( o1 != null && o2 != null )
        {
            if ( o1.getDocuId().getDocuId().intValue() == o2.getDocuId().getDocuId().intValue())
            {
                return o2.getVersFxAlta().compareTo( o1.getVersFxAlta());
            }
            
            return o1.getDocuId().getDocuId().compareTo(o2.getDocuId().getDocuId() );
        }
        
        if ( o1 != null )
        {
            return 1;
        }
        return -1;
    }
    
}
