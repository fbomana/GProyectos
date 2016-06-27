/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.*;

/**
 *
 * @author aitkiar
 */
public class TiposDocumentacionDAOJPATest
{
    private static TiposDocumentacionDAO dao;
    private static EJBContainer container;
    public TiposDocumentacion tipo;
    
    public TiposDocumentacionDAOJPATest( EJBContainer container )
    {
        this.container = container;
        try
        {
            dao = (TiposDocumentacionDAO)container.getContext().lookup("java:global/classes/TiposDocumentacionDAOJPA");
        }
        catch ( Exception e )
        {
        }
    }

    /**
     * Test para probar el funcionamiento básico de los métodos de CRUD. Se 
     * ejecuta del tirón para asegurarnos de que todo está dentro de una misma 
     * transacción.
     */
    public void test() throws Exception
    {
        tipo = new TiposDocumentacion( null, 
            "Tipo de prueba para CRUD", 
            "Tipo de prueba de las operaciones CRUD básicas.\nSe crea "
                + "desde los test automáticos." );

        tipo.setTdocPlantilla( Files.readAllBytes(Paths.get( System.getProperty("user.home") + 
            "/Proyectos/netbeans/proyectos/GestorProyectos/GPCore/", "build.xml")));
        try
        {
            dao.create(tipo);
            assertNotNull( tipo.getTdocId());
            
            tipo.setTdocNombre( "Tipo de prueba para CRUD. Modificado.");
            dao.edit( tipo );
            
            tipo = dao.find( tipo.getTdocId() );
            assertNotNull(tipo);
            assertEquals(tipo.getTdocNombre(), "Tipo de prueba para CRUD. Modificado.");            
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
    public void cleanUp() throws Exception
    {
        dao.remove(tipo);
        try
        {
            tipo = dao.find( tipo.getTdocId());
            assertNull( tipo );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
