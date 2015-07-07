/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aitkiar
 */
public class VersionesDAOJPATest
{

    private static VersionesDAO instance;
    private static EJBContainer container;
    private static TiposDocumentacionDAO daoTipos;
    private static TiposDocumentacion tdocPrueba;
    private static DocumentacionDAO daoDocumentacion;
    private static Documentacion docPrueba;
    private static Usuarios usuaPrueba;
    private static UsuariosDAO daoUsuarios;
    
    public VersionesDAOJPATest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            instance = (VersionesDAO)container.getContext().lookup("java:global/classes/VersionesDAOJPA");
            daoTipos = (TiposDocumentacionDAO)container.getContext().lookup("java:global/classes/TiposDocumentacionDAOJPA");
            daoDocumentacion = (DocumentacionDAO)container.getContext().lookup("java:global/classes/DocumentacionDAOJPA");
            daoUsuarios = (UsuariosDAO)container.getContext().lookup("java:global/classes/UsuariosDAOJPA");
            
            tdocPrueba = new TiposDocumentacion( null, 
            "Tipo de prueba para el test de documentación", 
            "Tipo de prueba de para los test unitarios de Documentación. Se borra al terminar los test" );
            daoTipos.create( tdocPrueba );
            
            docPrueba = new Documentacion();
            docPrueba.setDocuNombre("Documento de prueba");
            docPrueba.setTdocId(tdocPrueba);
            daoDocumentacion.create(docPrueba);
            
            usuaPrueba = daoUsuarios.findByLogin("admin");
            
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
            daoDocumentacion.remove(docPrueba);
            daoTipos.remove( tdocPrueba );
        }
        catch ( Exception e )
        {
            System.out.println("Error al limpiar los datos de prueba");
            e.printStackTrace();
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
     * Test que comprueba la funcionalidad básica de crud del dao
     */
    @Test
    public void testCrud()
    {
        Versiones version = new Versiones();
        version.setDocuId(docPrueba);
        version.setVersFxAlta( new Date() );
        version.setUsuaIdVersion( usuaPrueba );
        try
        {
            version.setVersDocumento(Files.readAllBytes(Paths.get( System.getProperty("user.home") + 
                "/Proyectos/netbeans/proyectos/GestorProyectos/GPCore/", "build.xml")));
            version.setVersDocumentoNombre("build.xml");
            version.setVersDocumentoMime("text/xml");
            
            //Create
            instance.create( version );
            assertNotNull( version.getVersId());

            //Read
            version = instance.find( version.getVersId());
            assertNotNull( version );
            System.out.println( version.getVersDocumento().length );
            
            //Update
            Date old = version.getVersFxAlta();
            version.setVersFxAlta( new Date());
            instance.edit( version );
            version = instance.find( version.getVersId());
            assertTrue( old.before( version.getVersFxAlta() ));
            
            // Delete
            instance.remove( version );
            version = instance.find( version.getVersId());
            assertNull( version );
        }
        catch ( Exception e )
        {
            fail( e.getMessage() );
            e.printStackTrace();
        }
    }
}
