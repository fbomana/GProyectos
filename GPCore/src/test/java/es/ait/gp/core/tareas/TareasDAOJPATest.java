/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
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
public class TareasDAOJPATest
{
    private static TareasDAO instance;
    private static EJBContainer container;
    
    public TareasDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (TareasDAO)container.getContext().lookup("java:global/classes/TareasDAOJPA");
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
     * Test of create method, of class TareasDAOJPA.
     */
    @Test
    public void testCreate() throws Exception
    {
        Usuarios usuario = ((UsuariosDAO)container.getContext().lookup("java:global/classes/UsuariosDAOJPA")).find( 1 );
        Proyectos proyecto = ((ProyectosDAO)container.getContext().lookup("java:global/classes/ProyectosDAOJPA")).find( 1 );
        
        Tareas tarea = new Tareas();
        tarea.setTareNombre("Tarea de prueba creada desde los test automáticos");
        tarea.setTareDescripcion("Tarea de prueba creada desde los test automáticos.");
        tarea.setProyId( proyecto );
        tarea.setUsuaIdAlta( usuario );
        instance.create( tarea );
        assertTrue( tarea.getTareId() != null );
    }

    /**
     * Test of edit method, of class TareasDAOJPA.
     */
    @Test
    public void testEdit() throws Exception
    {
//        System.out.println("edit");
//        Tareas tarea = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        TareasDAO instance = (TareasDAO)container.getContext().lookup("java:global/classes/TareasDAOJPA");
//        instance.edit(tarea);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class TareasDAOJPA.
     */
    @Test
    public void testRemove() throws Exception
    {
//        System.out.println("remove");
//        Tareas tarea = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        TareasDAO instance = (TareasDAO)container.getContext().lookup("java:global/classes/TareasDAOJPA");
//        instance.remove(tarea);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class TareasDAOJPA.
     */
    @Test
    public void testFind() throws Exception
    {
//        System.out.println("find");
//        Object id = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        TareasDAO instance = (TareasDAO)container.getContext().lookup("java:global/classes/TareasDAOJPA");
//        Tareas expResult = null;
//        Tareas result = instance.find(id);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUser method, of class TareasDAOJPA.
     */
    @Test
    public void testFindByUser() throws Exception
    {
        Usuarios usuario = new Usuarios();
        usuario.setUsuaId( 1 );
        instance.findByUser(usuario);
        assertTrue(true);
    }

    /**
     * Test of findByFilter method, of class TareasDAOJPA.
     */
    @Test
    public void testFindByFilter() throws Exception
    {
//        System.out.println("findByFilter");
//        Tareas filtro = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        TareasDAO instance = (TareasDAO)container.getContext().lookup("java:global/classes/TareasDAOJPA");
//        List<Tareas> expResult = null;
//        List<Tareas> result = instance.findByFilter(filtro);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
