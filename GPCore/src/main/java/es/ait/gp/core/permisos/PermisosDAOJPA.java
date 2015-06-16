/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class PermisosDAOJPA implements PermisosDAO
{

    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    protected EntityManager getEntityManager()
    {
        return em;
    }
    
    @Override
    public void create(Permisos permiso ) throws Exception
    {
        em.persist( permiso );
    }

    @Override
    public void edit(Permisos permiso ) throws Exception
    {
        em.merge( permiso );
    }

    @Override
    public void remove( Permisos permiso ) throws Exception
    {
        permiso  = em.find( Permisos.class, permiso.getPermId());
        em.remove( permiso );
    }

    @Override
    public Permisos find(Object id) throws Exception
    {
        return em.find( Permisos.class, id );
    }

    @Override
    public List<Permisos> findAll() throws Exception
    {
        return em.createNamedQuery("Permisos.findAll").getResultList();
    }

    @Override
    public Permisos findByName(String name) throws Exception
    {
        return ( Permisos )em.createNamedQuery("Permisos.findByPermNombre").setParameter("permNombre", name ).getSingleResult();
    }
    
}
