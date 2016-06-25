/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.usuarios.Usuarios;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author aitkiar
 */
public class DocumentacionGestorTest
{
    private static EJBContainer container;
    private DocumentacionGestorRemote gestor;
    private Documentacion documentacionProyecto;
    private Versiones versionProyecto;

    
    public DocumentacionGestorTest( EJBContainer container )
    {
        this.container = container;
        try
        {
            gestor = (DocumentacionGestorRemote)container.getContext().lookup("java:global/classes/DocumentacionGestor");
        }
        catch ( Exception e )
        {
        }
    }
    
    public void testDocumentosProyecto( Proyectos proyecto, Usuarios usuario ) throws Exception
    {
        documentacionProyecto = new Documentacion();
        documentacionProyecto.setDocuNombre("documento de prueba asociado a proyecto");      
        
        int tamHistorico = proyecto.getHistorico().size();
        
        gestor.guardarDocumentoProyecto(documentacionProyecto, null, proyecto.getProyId(), usuario );
        
        assertNotNull( documentacionProyecto.getDocuId());
        assertTrue( tamHistorico < proyecto.getHistorico().size());
        
        tamHistorico = proyecto.getHistorico().size();
        versionProyecto = new Versiones();
        versionProyecto.setVersDocumentoMime("text/xml");
        versionProyecto.setVersDocumentoNombre("pom.xml");
        versionProyecto.setVersDocumento( Files.readAllBytes( Paths.get(new File("/home/aitkiar/Proyectos/maven/proyectos/pom.xml").toURI())));
        gestor.guardarDocumentoProyecto(documentacionProyecto, versionProyecto, tamHistorico, usuario);
        
        assertNotNull( versionProyecto.getVersId());
        assertTrue( tamHistorico < proyecto.getHistorico().size());
    }
    
    public void cleanUpDocumentosProyecto( Proyectos proyecto, Usuarios usuario ) throws Exception
    {
        gestor.eliminarVersionProyecto(versionProyecto, proyecto.getProyId(), usuario);
        gestor.eliminarDocumentacionProyecto(documentacionProyecto, proyecto.getProyId(), usuario);
    }
}
