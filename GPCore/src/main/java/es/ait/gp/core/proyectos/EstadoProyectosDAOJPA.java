/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class EstadoProyectosDAOJPA implements EstadoProyectosDAO
{

    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    protected EntityManager getEntityManager()
    {
        return em;
    }

    @Override
    public void create(EstadoProyectos estadoProyectos) throws Exception
    {
        em.persist( estadoProyectos );
    }

    @Override
    public void edit(EstadoProyectos estadoProyectos) throws Exception
    {
        em.merge( estadoProyectos );
    }

    @Override
    public void remove(EstadoProyectos estadoProyectos) throws Exception
    {
        estadoProyectos  = em.find( EstadoProyectos.class, estadoProyectos.getEsprEstado() );
        em.remove( estadoProyectos );
    }

    @Override
    public EstadoProyectos find(Object id) throws Exception
    {
        return em.find( EstadoProyectos.class, id );
    }

    @Override
    public List<EstadoProyectos> findAll() throws Exception
    {
        return em.createQuery("select e from EstadoProyectos e order by e.esprEstado").getResultList();
    }
}
