/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class DocumentacionDAOJPA implements DocumentacionDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    @EJB
    ProyectosDAO daoProyectos;
    
    @EJB
    TareasDAO daoTareas;
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Documentacion documentacion) throws Exception
    {
        em.persist( documentacion );
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void edit(Documentacion documentacion) throws Exception
    {
        em.merge( documentacion );
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Documentacion documentacion) throws Exception
    {
        documentacion = em.find(Documentacion.class, documentacion.getDocuId());
        if ( documentacion != null )
        {
            em.remove(documentacion);
        }
    }

    @Override
    public Documentacion find(Object id) throws Exception
    {
        return em.find(Documentacion.class, id );
    }

    @Override
    public List<Documentacion> findAll() throws Exception
    {
        return em.createNamedQuery("Documentacion.findAll").getResultList();
    }
    
    /**
     * Crea o guarda una nueva documentación asociada a un proyecto y actualiza el
     * otro lado de la relación.
     * @param documentacion
     * @param proyId
     * @throws Exception 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarDocumentoProyecto( Documentacion documentacion, Integer proyId ) throws Exception
    {
        Proyectos proyecto = null;
        DocumentosProyectos dp = null;
        if ( documentacion.getDocuId() == null )
        {
            create( documentacion );
            if ( proyId != null )
            {
                proyecto = daoProyectos.find( proyId);
                dp = new DocumentosProyectos( proyId, documentacion.getDocuId());
                dp.setDocumentacion(documentacion);
                dp.setProyectos(proyecto);
                documentacion.setDocumentosProyectos( dp );
                proyecto.getDocumentos().add( dp );
            }
        }
        edit( documentacion );
        if ( proyecto != null )
        {
            daoProyectos.edit(proyecto );
        }
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarDocumentoTarea( Documentacion documentacion, Integer tareId ) throws Exception
    {
        Tareas tarea = null;
        DocumentosTareas dt = null;
        if ( documentacion.getDocuId() == null )
        {
            create( documentacion );
            if ( tareId != null )
            {
                tarea = daoTareas.find( tareId );
                dt = new DocumentosTareas( tareId, documentacion.getDocuId());
                dt.setDocumentacion(documentacion);
                dt.setTareas( tarea );
                documentacion.setDocumentosTareas( dt );
                tarea.getDocumentos().add( dt );
            }
        }
        edit( documentacion );
        if ( tarea != null )
        {
            daoTareas.edit( tarea );
        }
    }
}
