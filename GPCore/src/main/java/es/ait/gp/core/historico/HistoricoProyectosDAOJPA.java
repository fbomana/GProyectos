/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import es.ait.gp.core.proyectos.Proyectos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB que implemnenta el acceso a datos de la tabla de historico_proyectos.
 *
 * @author aitkiar
 */
@Stateless
public class HistoricoProyectosDAOJPA implements HistoricoProyectosDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    EntityManager em;
    
    @Override
    public void create(HistoricoProyectos historico)
    {
        em.persist( historico );
        Proyectos proyecto = em.find( Proyectos.class, historico.getProyId().getProyId());
        proyecto.getHistorico().add( historico );
        em.merge( proyecto );
    }

    @Override
    public HistoricoProyectos find(int id) throws Exception
    {
        return em.find( HistoricoProyectos.class, id );
    }

    @Override
    public List<HistoricoProyectos> find(Proyectos proyecto)
    {
        return em.createQuery("select h from HistoricoProyectos h where h.proyId.proyId = :proyId order by h.hiprFxAccion desc, h.hiprId desc").setParameter("proyId", proyecto.getProyId()).getResultList();
    }

    @Override
    public void remove( Proyectos proyecto ) throws Exception
    {
        em.createQuery( "delete from HistoricoProyectos hp where hp.proyId.proyId = :proyId").setParameter( "proyId", proyecto.getProyId()).executeUpdate();
        proyecto = em.find( Proyectos.class, proyecto.getProyId());
        proyecto.getHistorico().clear();
        em.merge( proyecto );        
    }
    
}
