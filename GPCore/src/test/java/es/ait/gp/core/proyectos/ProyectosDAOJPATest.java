/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.util.List;
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
public class ProyectosDAOJPATest
{
    private static ProyectosDAO instance;
    private static EJBContainer container;
    
    public ProyectosDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (ProyectosDAO)container.getContext().lookup("java:global/classes/ProyectosDAOJPA");
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
    
    @Test
    public void testCRUD() throws Exception
    {
        System.out.println("create");
        Proyectos proyecto = new Proyectos();
        proyecto.setProyFxAlta( new java.util.Date());
        proyecto.setProyDescripcion("Proyecto creado durante las pruebas unitarias con JUNIT.");
        
        try
        {
            EstadoProyectosDAO dao = ( EstadoProyectosDAO )container.getContext().lookup("java:global/classes/EstadoProyectosDAOJPA");
            proyecto.setProyEstado( dao.findAll().get( 0 ));
        }
        catch ( Exception e )
        {
            
        }
        
        proyecto.setProyNombre("Proyecto de test");
        Usuarios usuario = null;
        try
        {
            UsuariosDAO dao = (UsuariosDAO)container.getContext().lookup("java:global/classes/UsuariosDAOJPA");
            usuario = dao.findByLogin( "admin" );
        }
        catch ( Exception e )
        {
        }
        proyecto.setUsuaIdAlta( usuario );

        try
        {
            instance.create( proyecto );
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
        
        if ( proyecto.getProyId() == null )
        {
            System.out.println("proyecto.getGetProyectoId() == null");
        }

        System.out.println("find");
        try
        {
            instance.find( proyecto.getProyId());
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

        System.out.println("edit");
        proyecto.setProyDescripcion( proyecto.getProyDescripcion() + " Modificado" );
        instance.edit(proyecto);

        System.out.println("findAll");
        List resultado = instance.findAll();
        assertFalse( resultado.isEmpty());

        System.out.println("remove");
        instance.remove( proyecto );
    }    
}
