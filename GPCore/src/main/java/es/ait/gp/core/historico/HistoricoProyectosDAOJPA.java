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
    
}
