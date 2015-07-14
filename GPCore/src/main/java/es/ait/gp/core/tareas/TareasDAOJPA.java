/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.usuarios.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Dao para acceder a la tabla tareas usando JPA.
 * @author aitkiar
 */
@Stateless
public class TareasDAOJPA implements TareasDAO
{

    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;
    
    @Override
    public void create(Tareas tarea) throws Exception
    {
        em.persist( tarea );
        
        System.out.println( tarea.getProyId());
        Proyectos proyecto = em.find( Proyectos.class, tarea.getProyId().getProyId());
        proyecto.getTareas().add( tarea );
        em.merge( proyecto );
    }

    @Override
    public void edit(Tareas tarea) throws Exception
    {
        em.merge( tarea );
    }

    @Override
    public void remove(Tareas tarea) throws Exception
    {
        tarea = em.find( Tareas.class, tarea.getTareId()); // necesario para que se pueda borrar el objeto si no está bajo el control del contenedor.
        em.remove( tarea );
    }

    @Override
    public Tareas find(Object id) throws Exception
    {
        return em.find( Tareas.class, id );
    }

    /**
     * Devuelve un listado de las tareas del usuario ordenadas por id de relaciónç
     * y nombre de la tarea.
     * @param usuario
     * @return
     * @throws Exception 
     */
    @Override
    public List<Tareas> findByUser(Usuarios usuario) throws Exception
    {
        return ( List<Tareas>)em.createQuery("select distinct t from Tareas t, "
            + "in ( t.usuariosTareas ) u where "
            + "u.usuariosTareasPK.usuaId = :usuaId order by t.proyId.proyId, t.tareNombre")
            .setParameter( "usuaId", usuario.getUsuaId() ).getResultList();
    }

    /**
     * Devuelve un listado de todas las tareas que cumplan el filtro indicado.
     * 
     * @param filtro
     * @return
     * @throws Exception 
     */
    @Override
    public List<Tareas> findByFilter(Tareas filtro) throws Exception
    {
        String jpql = "select t from Tareas t";
        String separador = " where ";
        ArrayList parametros = new ArrayList();
        
        int i = 1;
        if ( filtro != null )
        {
            if ( filtro.getProyId() != null )
            {
                jpql += separador + "t.proyId = ?" + i++;
                parametros.add( filtro.getProyId());
                separador = " and ";
            }
            
            if ( filtro.getTareDescripcion() != null )
            {
                jpql += separador + "t.tareDescripcion like ?" + i++;
                parametros.add( "%" + filtro.getTareDescripcion() + "%");
                separador = " and ";
            }
            
            if ( filtro.getTareNombre() != null )
            {
                jpql += separador + "t.tareNombre like ?" + i++;
                parametros.add( "%" + filtro.getTareNombre() + "%" );
                separador = " and ";
            }
            
            if ( filtro.getUsuaIdAlta() != null )
            {
                jpql += separador + "t.usuaIdAlta = ?" + i++;
                parametros.add( filtro.getUsuaIdAlta() );
                separador = " and ";
            }
        }
        
        jpql += " order by t.tareNombre";
        
        Query consulta = em.createQuery( jpql );
        for ( int j = 0; j < parametros.size(); j++ )
        {
            consulta = consulta.setParameter( j+1, parametros.get( j ));
        }
        return consulta.getResultList();
    }
    
}
