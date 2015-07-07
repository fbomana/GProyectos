/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.historico.ConstantesAccionesHistorico;
import es.ait.gp.core.historico.HistoricoProyectos;
import es.ait.gp.core.historico.HistoricoProyectosDAO;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
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
    private DocumentacionDAO daoDocumentacion;
    
    @EJB
    private HistoricoProyectosDAO daoHistorico;
    
    /**
     * Crea o guarda una nueva documentación asociada a un proyecto y actualiza el
     * otro lado de la relación.
     * @param documentacion
     * @param proyId
     * @param usuario
     * @throws Exception 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarDocumentoProyecto( Documentacion documentacion, Integer proyId, Usuarios usuario ) throws Exception
    {
        Proyectos proyecto = daoProyectos.find( proyId );
        DocumentosProyectos dp = null;
        
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
    }
}
