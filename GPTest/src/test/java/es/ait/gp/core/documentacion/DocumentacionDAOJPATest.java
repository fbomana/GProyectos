/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

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
public class DocumentacionDAOJPATest
{
    private static DocumentacionDAO instance;
    private static TiposDocumentacionDAO daoTipos;
    private static EJBContainer container;
    private static TiposDocumentacion tdocPrueba;
    
    public DocumentacionDAOJPATest()
    {
    }
    
    /**
     * Obtiene los diferentes DAOs de prueba y crea un tipo de documentos de prueba
     * para poder ejecutar el test.
     */
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            
            instance = (DocumentacionDAO)container.getContext().lookup("java:global/classes/DocumentacionDAOJPA");
            daoTipos = (TiposDocumentacionDAO)container.getContext().lookup("java:global/classes/TiposDocumentacionDAOJPA");
            tdocPrueba = new TiposDocumentacion( null, 
            "Tipo de prueba para el test de documentación", 
            "Tipo de prueba de para los test unitarios de Documentación. Se borra al terminar los test" );
            daoTipos.create( tdocPrueba );
            
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina el tipo de documentación de prueba y cierra el entorno de ejecución de EJB's
     * @throws Exception 
     */
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        daoTipos.remove( tdocPrueba );
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
        
        Documentacion documentacion = new Documentacion ( null, "Documento de prueba");
        documentacion.setTdocId(tdocPrueba);
        try
        {
            instance.create(documentacion);
            assertNotNull( documentacion.getDocuId());
            
            documentacion.setDocuNombre("Documento de prueba. Modificado.");
            instance.edit( documentacion );
            
            documentacion = instance.find( documentacion.getDocuId());
            assertNotNull(documentacion);
            assertEquals(documentacion.getDocuNombre(), "Documento de prueba. Modificado.");
            
            instance.remove(documentacion);
            documentacion = instance.find( documentacion.getDocuId());
            assertNull( documentacion );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            fail("Error sin determinar");
        }
    }
}
