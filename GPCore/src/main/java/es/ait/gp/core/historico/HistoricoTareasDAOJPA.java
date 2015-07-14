/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import es.ait.gp.core.tareas.Tareas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class HistoricoTareasDAOJPA implements HistoricoTareasDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    EntityManager em;
            
    @Override
    public void create( HistoricoTareas historico ) throws Exception
    {
        em.persist( historico );
        Tareas tarea = em.find( Tareas.class, historico.getTareId().getTareId());
        tarea.getHistorico().add( historico );
        em.merge( tarea );
    }

    @Override
    public HistoricoTareas find( int id ) throws Exception
    {
        return em.find(HistoricoTareas.class, id );
    }

    @Override
    public List<HistoricoTareas> find( Tareas tarea ) throws Exception
    {
        return em.createQuery( "select h from HistoricoTareas h where h.tareId.tareId = :tareId order by h.hitrFxAccion desc, h.hitrId desc").setParameter( "tareId", tarea.getTareId()).getResultList();
    }
    
}
