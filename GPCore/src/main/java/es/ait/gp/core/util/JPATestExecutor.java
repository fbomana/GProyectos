/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.util;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase para probar consultas JPQL online.
 *
 * @author aitkiar
 */
@Stateless
public class JPATestExecutor
{

    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;
    
    
    public JPATestResult runTest( String query )
    {
        try
        {
            return new JPATestResult( runTestQuery(query));
        }
        catch ( Exception e )
        {
            return new JPATestResult( e );
        }
    }
    
    private List runTestQuery( String query ) throws Exception
    {
        return em.createQuery(query).getResultList();
    }
    
    
}
