/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.historico.ConstantesAccionesHistorico;
import es.ait.gp.core.historico.HistoricoProyectos;
import es.ait.gp.core.historico.HistoricoProyectosDAO;
import es.ait.gp.core.historico.HistoricoTareas;
import es.ait.gp.core.historico.HistoricoTareasDAO;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author aitkiar
 */
@Stateless
public class DocumentacionGestor implements DocumentacionGestorRemote
{
    @EJB
    private ProyectosDAO daoProyectos;
    
    @EJB
    private TareasDAO daoTareas;
    
    @EJB
    private DocumentacionDAO daoDocumentacion;
    
    @EJB
    private HistoricoProyectosDAO daoHistorico;
    
    @EJB
    private HistoricoTareasDAO daoHistoricoTareas;
    
    @EJB
    private VersionesDAO daoVersiones;
    
    /**
     * Crea o guarda una nueva documentación asociada a un proyecto y actualiza el
     * otro lado de la relación.
     * @param documentacion
     * @param version
     * @param proyId
     * @param usuario
     * @throws Exception 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarDocumentoProyecto( Documentacion documentacion, Versiones version, Integer proyId, Usuarios usuario ) throws Exception
    {
        Proyectos proyecto = daoProyectos.find( proyId );
        DocumentosProyectos dp;
        
        HistoricoProyectos historico = new HistoricoProyectos( proyecto, usuario );
        historico.setHiprFxAccion( new java.util.Date());
        
        if ( documentacion.getDocuId() == null )
        {
            historico.setAchiId( ConstantesAccionesHistorico.PROYECTOS_NUEVA_DOCUMENTACION);
            historico.setHiprDescripcion("Nueva documentacion:" + documentacion.getDocuNombre());
                    
            daoDocumentacion.create( documentacion );

            dp = new DocumentosProyectos( proyId, documentacion.getDocuId());
            dp.setDocumentacion(documentacion);
            dp.setProyectos(proyecto);
            documentacion.setDocumentosProyectos( dp );
            proyecto.getDocumentos().add( dp );
            daoProyectos.edit( proyecto );
        }
        else
        {
            historico.setAchiId( ConstantesAccionesHistorico.PROYECTOS_NUEVA_VERSION);
            historico.setHiprDescripcion("Documentación modificada:" + documentacion.getDocuNombre());
        }
        daoHistorico.create(historico);
        daoDocumentacion.edit( documentacion );
        if ( version != null )
        {
            version.setDocuId(documentacion);
            daoVersiones.create(version);
        }
    }
    
    /**
     * Crea una nueva versión de un documento ( y el documento ) asociado a una tarea. Actuakliza el historico 
     * añadiendo la acción de modidficación de documentación.
     * @param documentacion
     * @param version
     * @param tareId
     * @param usuario
     * @throws Exception 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarDocumentoTarea( Documentacion documentacion, Versiones version, Integer tareId, Usuarios usuario ) throws Exception
    {
        Tareas tarea = daoTareas.find( tareId );
        DocumentosTareas dt;
        
        HistoricoTareas historico = new HistoricoTareas( tarea, usuario );
        historico.setHitrFxAccion( new java.util.Date());
        
        if ( documentacion.getDocuId() == null )
        {
            daoDocumentacion.create(documentacion);
            
            historico.setAchiId( ConstantesAccionesHistorico.TAREAS_NUEVA_DOCUMENTACION);
            historico.setHitrDescripcion("Nueva documentacion: " + documentacion.getDocuNombre());
            
            dt = new DocumentosTareas( tareId, documentacion.getDocuId());
            dt.setDocumentacion(documentacion);
            dt.setTareas(tarea);
            
            tarea.getDocumentos().add(dt);
            daoTareas.edit(tarea);
        }
        else
        {
            historico.setAchiId( ConstantesAccionesHistorico.TAREAS_NUEVA_DOCUMENTACION);
            historico.setHitrDescripcion("Documentación modificada: " + documentacion.getDocuNombre());
        }
        daoHistoricoTareas.create( historico );
        daoTareas.edit( tarea );
        if ( version != null )
        {
            version.setDocuId(documentacion);
            daoVersiones.create( version );
        }
    }
}
