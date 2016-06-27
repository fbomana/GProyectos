/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util;

import es.ait.gp.core.util.JPATestExecutor;
import es.ait.gp.core.util.JPATestResult;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author aitkiar
 */
@Named("jpatest")
@RequestScoped
public class JPATest
{
    private String jpqlQuery;
    
    private JPATestResult result;
    
    @EJB
    JPATestExecutor test;
   
    public void test()
    {
        if ( jpqlQuery != null && !"".equals( jpqlQuery ))
        {
            result = test.runTest( jpqlQuery );
        }
    }

    /**
     * @return the jpqlQuery
     */
    public String getJpqlQuery()
    {
        return jpqlQuery;
    }

    /**
     * @param jpqlQuery the jpqlQuery to set
     */
    public void setJpqlQuery(String jpqlQuery)
    {
        this.jpqlQuery = jpqlQuery;
    }

    /**
     * @return the result
     */
    public JPATestResult getResult()
    {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(JPATestResult result)
    {
        this.result = result;
    }
}
