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
public class PermisosDAOJPATest
{
    private static PermisosDAO instance;
    private static EJBContainer container;
    
    
    public PermisosDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (PermisosDAO)container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/PermisosDAOJPA");
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
     * Test of create method, of class PermisosDAOJPA.
     */
    @Test
    public void testCRUD() throws Exception
    {
        System.out.println("create");
        Permisos permiso = new Permisos();
        permiso.setPermNombre("TEST_JPATEST");
        permiso.setPermDescripcion("Permiso creado durante las pruebas de JPA");
        instance.create(permiso);

        System.out.println("find");
        instance.find(permiso.getPermId());

        System.out.println("edit");
        permiso.setPermDescripcion("Descripcion modificada");
        instance.edit(permiso);

        System.out.println("findAll");
        List resultado = instance.findAll();
        assertFalse( resultado.isEmpty());

        System.out.println("remove");
        instance.remove(permiso);
    }    
}
