/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.usuarios;

import java.util.Date;
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
public class UsuariosDAOJPATest
{
    private static UsuariosDAO instance;
    private static EJBContainer container;
    
    public UsuariosDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (UsuariosDAO)container.getContext().lookup("java:global/classes/UsuariosDAOJPA");
        }
        catch ( Exception e )
        {
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
     * Test of create method, of class UsuariosDAOJPA.
     */
    @Test
    public void testCreate() throws Exception
    {
//        System.out.println("create");
//        Usuarios usuario = new Usuarios();
//        usuario.setUsuaNombre("Prueba JUNIT");
//        usuario.setUsuaLogin("pruebajunit");
//        usuario.setUsuaPassword("pruebajunit");
//        usuario.setUsuaEmail("aitkiar@gmail.com");
//        usuario.setUsuaFxAlta( new Date());
//        usuario.setUsuaActivo( true );
//
//        instance.create(usuario);
//
//        assertTrue( true );
    }

    /**
     * Test of edit method, of class UsuariosDAOJPA.
     */
    @Test
    public void testEdit() throws Exception
    {
//        System.out.println("edit");
//        try
//        {
//            Usuarios usuario = instance.find( -1 );
//            if ( usuario != null )
//            {
//                usuario.setUsuaNombre("admin modificado");
//                instance.edit(usuario);
//
//                assertTrue( !"admin".equals(usuario.getUsuaPassword()));
//            }
//            else
//            {
//                fail ( "usuario null");
//            }
//        }
//        catch ( Exception e )
//        {
//            e.printStackTrace();
//            fail( e.getMessage() );
//        }
    }

    /**
     * Test of remove method, of class UsuariosDAOJPA.
     */
    @Test
    public void testRemove() throws Exception
    {
//        System.out.println("remove");
//
//        try
//        {
//            Usuarios usuario = instance.find( -1 );
//            if ( usuario != null )
//            {
//                instance.remove( usuario );
//            }
//            else
//            {
//                fail ( "usuario null");
//            }
//        }
//        catch ( Exception e )
//        {
//            e.printStackTrace();
//            fail( e.getMessage() );
//        }

    }

    /**
     * Test of find method, of class UsuariosDAOJPA.
     */
    @Test
    public void testFind() throws Exception
    {
//        System.out.println("find");
//        Object id = null;
//
//        Usuarios expResult = null;
//        Usuarios result = instance.find( 1 );
//        assertTrue( result != null && result.getUsuaId() == 1 );

    }

    /**
     * Test of changePassword method, of class UsuariosDAOJPA.
     */
    @Test
    public void testChangePassword() throws Exception
    {
//        System.out.println("changePassword");
//        try
//        {
//            Usuarios usuario = instance.find( 1 );
//            if ( usuario != null )
//            {
//                String old = usuario.getUsuaPassword();
//                usuario.setUsuaPassword( "admin" );
//                instance.changePassword(usuario);
//                assertTrue( !old.equals(usuario.getUsuaPassword()));
//            }
//            else
//            {
//                fail ( "usuario null ");
//            }
//        }
//        catch ( Exception e )
//        {
//            e.printStackTrace();
//            fail( e.getMessage() );
//        }
//        assertTrue( true );

    }
    
}
