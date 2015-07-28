/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.completo;

import es.ait.gp.core.documentacion.DocumentacionDAO;
import es.ait.gp.core.documentacion.TiposDocumentacion;
import es.ait.gp.core.documentacion.TiposDocumentacionDAO;
import es.ait.gp.core.documentacion.TiposDocumentacionDAOJPATest;
import es.ait.gp.core.usuarios.UsuariosDAOJPATest;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Clase que realiza un test completo de los daos y los gestores pasando por el proceso de:
 * Dar de alta un usuario
 * Dar de alta un proyecto con el nuevo usuario
 * Asociar documentación al proyecto
 * Dar de alta una tarea para el proyecto
 * Crear un segundo usuario
 * Asignar la tarea al usuario
 * Subir documentacion a la tarea.
 *
 * @author aitkiar
 */
public class BigTest
{
    private static EJBContainer container;
    
    
    public BigTest()
    {
    }
    
    /**
     * Obtiene los diferentes DAOs y objetos que vamos a necesitar a lo largo del proceso de pruebas
     */
    @BeforeClass
    public static void setUpClass()
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try
        {
            
//            instance = (DocumentacionDAO)container.getContext().lookup("java:global/classes/DocumentacionDAOJPA");
//            daoTipos = (TiposDocumentacionDAO)container.getContext().lookup("java:global/classes/TiposDocumentacionDAOJPA");
//            tdocPrueba = new TiposDocumentacion( null, 
//            "Tipo de prueba para el test de documentación", 
//            "Tipo de prueba de para los test unitarios de Documentación. Se borra al terminar los test" );
//            daoTipos.create( tdocPrueba );
            
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
     * Clase de test que llama a las clases especializadas con los test de cada dao/gestor en el orden apropiado,
     * y a continuación llama a los cleanup en orden inverso para eliminar los restos del proceso de test de la BBDD.
     * 
     * @throws Exception 
     */
    @Test
    public void inicioTest() throws Exception
    {
        UsuariosDAOJPATest testUsuarios = new UsuariosDAOJPATest( container );
        TiposDocumentacionDAOJPATest testTiposDocumentacion = new TiposDocumentacionDAOJPATest( container );
                
        
        testUsuarios.test();
        testTiposDocumentacion.test();
        
        testTiposDocumentacion.cleanUp();
        testUsuarios.cleanUp();
    }
}
