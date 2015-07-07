/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class RolesDAOJPA implements RolesDAO
{

    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    protected EntityManager getEntityManager()
    {
        return em;
    }
    
    @Override
    public void create(Roles role ) throws Exception
    {
        em.persist( role );
        if ( role.getPermisosList() != null && !role.getPermisosList().isEmpty() )
        {
            for( Permisos permiso : role.getPermisosList())
            {
                if ( permiso.getRolesList() == null )
                {
                    permiso.setRolesList(( new ArrayList<Roles>()));
                }
                permiso.getRolesList().add( role );
                em.merge( permiso );
            }
        }
    }

    @Override
    public void edit( Roles role ) throws Exception
    {
        em.merge( role );
        
        if ( role.getPermisosList() != null && !role.getPermisosList().isEmpty() )
        {
            for( Permisos permiso : role.getPermisosList())
            {
                if ( permiso.getRolesList() == null )
                {
                    permiso.setRolesList(( new ArrayList<Roles>()));
                }
                if ( !permiso.getRolesList().contains( role ))
                {
                    permiso.getRolesList().add( role );
                    em.merge( permiso );
                }
            }
        }
    }

    @Override
    public void remove( Roles role ) throws Exception
    {
        role  = em.find( Roles.class, role.getRoleId());
        em.remove( role );
    }

    @Override
    public Roles find(Object id) throws Exception
    {
        return em.find( Roles.class, id );
    }

    @Override
    public Roles findByName( String name) throws Exception
    {
        try
        {
            return ( Roles ) em.createNamedQuery("Roles.findByRoleDescripcion").setParameter("roleDescripcion", name).getSingleResult();
        }
        catch ( javax.persistence.NoResultException e )
        {
            return null;
        }
    }
    
    @Override
    public List<Roles> findAll() throws Exception
    {
        return em.createNamedQuery("Roles.findAll").getResultList();
    }
    
}
