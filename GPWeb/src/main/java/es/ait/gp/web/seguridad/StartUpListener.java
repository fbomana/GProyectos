/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.seguridad;

import es.ait.gp.core.documentacion.TiposDocumentacionDAO;
import es.ait.gp.core.documentacion.TiposDocumentacionMap;
import es.ait.gp.core.proyectos.EstadoProyectosDAO;
import es.ait.gp.core.proyectos.EstadoProyectosMap;
import es.ait.gp.core.util.PrimeraEjecucion;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Ejecuta las inicializciones necesarias cuando se arranca la aplicación.
 *
 * @author aitkiar
 */
public class StartUpListener implements ServletContextListener
{
    @Inject
    PrimeraEjecucion pe;
    
    @EJB
    EstadoProyectosDAO estadoProyectosDao;
    
    @EJB
    TiposDocumentacionDAO tiposDocumentacionDao;
    
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        try
        {
            System.out.println("Configuración al inicio. Configurando BBDD");
            
            pe.execute();
            System.out.println("Inicializando variables globales...");
            
            System.out.println("\tInicializando estados de proyecto");
            EstadoProyectosMap.getInstance( estadoProyectosDao.findAll());
            
            System.out.println("\tInicializando tipos de documentación");
            TiposDocumentacionMap.getInstance( tiposDocumentacionDao.findAll() );
            
            System.out.println("Fin de la inicialización de variables globales");
            System.out.println("Configuración finalizada");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
    }
}
