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
        try
        {
            return ( Permisos )em.createNamedQuery("Permisos.findByPermNombre").setParameter("permNombre", name ).getSingleResult();
        }
        catch ( javax.persistence.NoResultException e )
        {
            return null;
        }
    }
    
    /**
     * Busca los permisos que están asociados a un role o que no están asociados a un rol en función del parámetro booleano in,
     * 
     * @param role
     * @param in
     * @return 
     */
    @Override
    public List<Permisos> findInRole( Roles role, boolean in )
    {
       String query = "select p from Permisos p left join p.rolesList r where r.roleId " + ( in ? "=" : "<>" ) + ":roleId" ;
       
       return em.createQuery(query).setParameter("roleId", role.getRoleId()).getResultList();
    }
    
}
