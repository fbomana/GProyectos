/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author aitkiar
 */
@Stateless
public class UsuariosTareasDAOJPA implements UsuariosTareasDAO
{
    @PersistenceUnit ( unitName = "GPCorePU")
    private EntityManager em;
    
    @Override
    public void create(UsuariosTareas tarea) throws Exception
    {
        em.persist( tarea );
    }

    @Override
    public void edit(UsuariosTareas tarea) throws Exception
    {
        em.merge( tarea );
    }

    @Override
    public void remove(UsuariosTareas tarea) throws Exception
    {
        tarea = em.find( UsuariosTareas.class, tarea.getUsuariosTareasPK());
        if ( tarea != null )
        {
            em.remove( tarea );
        }
    }

    @Override
    public UsuariosTareas find(UsuariosTareasPK id) throws Exception
    {
        return em.find( UsuariosTareas.class, id );
    }
    
    @Override
    public UsuariosTareas findByUsuarioTarea (UsuariosTareasPK id) throws Exception
    {
        try
        {     
            return ( UsuariosTareas )em.createQuery( "select u from UsuariosTareas u where u.usuariosTareasPK.usuaId =:usuaId "
                + "and u.usuariosTareasPK.tareId = :tareId").setParameter("usuaId", id.getUsuaId())
                .setParameter("tareId", id.getTareId()).getSingleResult();
        }
        catch ( NoResultException e )
        {
            return null;
        }
    }
    
    
}
