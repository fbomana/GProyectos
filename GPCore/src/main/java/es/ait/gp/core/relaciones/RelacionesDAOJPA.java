/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.relaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase que implementa el DAO sobre la tabla de relaciones usando JPA.
 * 
 * @author aitkiar
 */
public abstract class RelacionesDAOJPA
{

    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    public void create(Relaciones relacion)
    {
        em.persist(relacion);
    }

    public void edit(Relaciones relacion)
    {
        em.merge(relacion);
    }

    public void remove(Relaciones relacion)
    {
        em.remove( find( relacion.getRelaId() ));
    }

    public Relaciones find(Object id)
    {
        return em.find(Relaciones.class, id);
    }

    public List<Relaciones> findAll()
    {
        return em.createNamedQuery("Relaciones.findAll").getResultList();
    }
    
}
