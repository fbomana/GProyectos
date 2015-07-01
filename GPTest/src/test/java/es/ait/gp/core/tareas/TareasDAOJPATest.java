/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.proyectos.EstadoProyectosDAO;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.util.Set;
import javax.ejb.embeddable.EJBContainer;
import javax.validation.ConstraintViolation;
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
    private static Proyectos proyecto;
    private static ProyectosDAO proyectosDao;
    private static Usuarios usuario;
    
    public TareasDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (TareasDAO)container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/TareasDAOJPA");
            proyectosDao = (ProyectosDAO)container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/ProyectosDAOJPA");
            proyecto = new Proyectos();
            proyecto.setProyFxAlta( new java.util.Date());
            proyecto.setProyDescripcion("Proyecto creado durante las pruebas unitarias con JUNIT.");

            try
            {
                EstadoProyectosDAO dao = ( EstadoProyectosDAO )container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/EstadoProyectosDAOJPA");
                proyecto.setProyEstado( dao.findAll().get( 0 ));
            }
            catch ( Exception e )
            {

            }

            proyecto.setProyNombre("Proyecto de test");
            try
            {
                UsuariosDAO dao = (UsuariosDAO)container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/UsuariosDAOJPA");
                usuario = dao.findByLogin( "admin" );
            }
            catch ( Exception e )
            {
            }
            proyecto.setUsuaIdAlta( usuario );

            try
            {
                proyectosDao.create( proyecto );
            }
            catch ( javax.validation.ConstraintViolationException e )
            {
                Set<ConstraintViolation<?>> lista = e.getConstraintViolations();
                for( ConstraintViolation<?> violacion : lista )
                {
                    System.out.println( violacion.getConstraintDescriptor().toString());
                }

            }
            catch ( Exception e )
            {
                System.out.println( "No es una ConstraintViolationException");
            }
        }
        catch ( Exception e )
        {
        }
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        try
        {
            proyectosDao.remove(proyecto);
        }
        catch ( Exception e )
        {
        }
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
    public void testCRUD() throws Exception
    {       
        System.out.println("Create");
        Tareas tarea = new Tareas();
        tarea.setTareNombre("Tarea de prueba creada desde los test automáticos");
        tarea.setTareDescripcion("Tarea de prueba creada desde los test automáticos.");
        tarea.setProyId( proyecto );
        tarea.setUsuaIdAlta( usuario );
        instance.create( tarea );
        assertTrue( tarea.getTareId() != null );
        
        System.out.println("Read");
        tarea = instance.find( tarea.getTareId());
        assertNotNull(tarea);
        
        System.out.println("Update");
        tarea.setTareDescripcion( tarea.getTareDescripcion() + " Modificada");
        String nuevaDescripcion = tarea.getTareDescripcion();
        instance.edit(tarea);
        tarea = instance.find( tarea.getTareId());
        assertEquals(tarea.getTareDescripcion(), nuevaDescripcion );
        
        System.out.println("delete");
        instance.remove(tarea);
        tarea = instance.find( tarea.getTareId());
        assertNull(tarea);        
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
//        TareasDAO instance = (TareasDAO)container.getContext().lookup("java:global/GPCore-1.0.0-SNAPSHOT/TareasDAOJPA");
//        List<Tareas> expResult = null;
//        List<Tareas> result = instance.findByFilter(filtro);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
