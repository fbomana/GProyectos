/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aitkiar
 */
public class RolesDAOJPATest
{
    private static RolesDAO instance;
    private static EJBContainer container;
    
    
    public RolesDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (RolesDAO)container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/RolesDAOJPA");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        container.close();
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of create method, of class RolesDAOJPA.
     */
    @Test
    public void testCRUD() throws Exception
    {
        System.out.println("create");
        Roles role = new Roles();
        role.setRoleDescripcion("Role creado durante las pruebas de JPA");
        instance.create(role);

        System.out.println("find");
        instance.find(role.getRoleId());

        System.out.println("edit");
        role.setRoleDescripcion("Modificado");
        instance.edit(role);

        System.out.println("findAll");
        List resultado = instance.findAll();
        assertFalse( resultado.isEmpty());

        System.out.println("remove");
        instance.remove(role);
    }    
}
