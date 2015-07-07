/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aitkiar
 */
public class TiposDocumentacionDAOJPATest
{
    private static TiposDocumentacionDAO instance;
    private static EJBContainer container;
    
    public TiposDocumentacionDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (TiposDocumentacionDAO)container.getContext().lookup("java:global/classes/TiposDocumentacionDAOJPA");
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
     * Test para probar el funcionamiento básico de los métodos de CRUD. Se 
     * ejecuta del tirón para asegurarnos de que todo está dentro de una misma 
     * transacción.
     */
    @Test
    public void testCreateReadUpdateDelete() throws Exception
    {
        TiposDocumentacion tipo = new TiposDocumentacion( null, 
            "Tipo de prueba para CRUD", 
            "Tipo de prueba de las operaciones CRUD básicas.\nSe crea "
                + "desde los test automáticos." );

        tipo.setTdocPlantilla( Files.readAllBytes(Paths.get( System.getProperty("user.home") + 
            "/Proyectos/netbeans/proyectos/GestorProyectos/GPCore/", "build.xml")));
        try
        {
            instance.create(tipo);
            assertNotNull( tipo.getTdocId());
            
            tipo.setTdocNombre( "Tipo de prueba para CRUD. Modificado.");
            instance.edit( tipo );
            
            tipo = instance.find( tipo.getTdocId() );
            assertNotNull(tipo);
            assertEquals(tipo.getTdocNombre(), "Tipo de prueba para CRUD. Modificado.");
            
            instance.remove(tipo);
            try
            {
                tipo = instance.find( tipo.getTdocId());
                assertNull( tipo );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        catch ( Exception e )
        {
            fail("Error sin determinar");
            e.printStackTrace();
        }
    }

    /**
     * Crea dos tipos de prueba y llama al método findAll. Da por bueno el test si
     * encuentra al menos los dos tipos de prueba.
     * @throws Exception 
     */
    @Test
    public void testCreateFindAllDelete() throws Exception
    {
        TiposDocumentacion tipo1 = new TiposDocumentacion( null, 
            "Tipo de prueba para CRUD. 1", 
            "Tipo de prueba de las operaciones CRUD básicas.\nSe crea "
                + "desde los test automáticos." );

        tipo1.setTdocPlantilla( Files.readAllBytes(Paths.get( System.getProperty("user.home") + 
            "/Proyectos/netbeans/proyectos/GestorProyectos/GPCore/", "build.xml")));
        
        TiposDocumentacion tipo2 = new TiposDocumentacion( null, 
            "Tipo de prueba para CRUD. 2", 
            "Tipo de prueba de las operaciones CRUD básicas.\nSe crea "
                + "desde los test automáticos." );
        
        instance.create( tipo1 );
        instance.create( tipo2 );
        
        List<TiposDocumentacion> resultado = instance.findAll();
        
        assertNotNull( resultado );
        assertTrue( resultado.size() >= 2 );
        
        assertTrue( resultado.contains( tipo1 ));
        assertTrue( resultado.contains( tipo2 ));
        
        int tam = resultado.size();
        
        instance.remove( tipo2 );
        instance.remove( tipo1 );
        
        resultado = instance.findAll();
        
        assertNotNull( resultado );
        assertTrue( tam - resultado.size() == 2 );
    }
}
