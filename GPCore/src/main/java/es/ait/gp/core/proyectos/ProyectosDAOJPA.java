/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.usuarios.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementación del DAO de proyectos con JPA.
 *
 * @author aitkiar
 */
@Stateless
public class ProyectosDAOJPA implements ProyectosDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    protected EntityManager getEntityManager()
    {
        return em;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Proyectos proyecto) throws Exception
    {
        em.persist( proyecto );
        if ( proyecto.getProyPadre() != null )
        {
            Proyectos padre = proyecto.getProyPadre();
            padre.getProyectosList().add( proyecto );
            em.merge( padre );
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void edit(Proyectos proyecto) throws Exception
    {
        em.merge( proyecto );
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Proyectos proyecto) throws Exception
    {
        proyecto = em.find( Proyectos.class, proyecto.getProyId()); // necesario para que se pueda borrar el objeto si no está bajo el control del contenedor.
        em.remove( proyecto );
    }

    @Override
    public Proyectos find(Object id) throws Exception
    {
        return em.find( Proyectos.class, id );
    }

    @Override
    public List<Proyectos> findByFilter(ProyectosFiltro filtro) throws Exception
    {
        String jpql = "Select p from Proyectos p";
        List parametros = new ArrayList<Object>();
        int numParametros = 0;
        
        if ( filtro != null )
        {
            String separador = " where ";
            
            if ( filtro.getPrimerNivel() != null && filtro.getPrimerNivel())
            {
                jpql += separador + "p.proyPadre is null";
                separador = " and ";
            }
            
            if ( filtro.getProyDescripcion() != null && !"".equals( filtro.getProyDescripcion()))
            {
                jpql += separador + "p.proyDescripcion like ?" + ( ++numParametros );
                parametros.add( "%$" + filtro.getProyDescripcion() + "%");
                separador = " and ";                
            }
            
            if ( filtro.getProyEstado() != null && !"".equals( filtro.getProyEstado()))
            {
                jpql += separador + "p.proyEstado = ?" + ( ++numParametros );
                parametros.add( EstadoProyectosMap.getInstance().get( filtro.getProyEstado()) );
                separador = " and ";
            }
            
            if ( filtro.getProyFxAlta() != null )
            {
                jpql += separador + "p.proyFxAlta = ?" + ( ++numParametros );
                parametros.add( filtro.getProyFxAlta());
                separador = " and ";
            }
            
            if ( filtro.getProyFxFin() != null )
            {
                jpql += separador + "p.proyFxFin = ?" + ( ++numParametros );
                parametros.add( filtro.getProyFxFin());
                separador = " and ";
            }
            
            if ( filtro.getProyId() != null )
            {
                jpql += separador + "p.proyId = ?" + ( ++numParametros );
                parametros.add( filtro.getProyId());
                separador += " and ";
            }
            
            if ( filtro.getProyNombre() != null )
            {
                jpql += separador + "p.proyNombre like ?" + ( ++numParametros );
                parametros.add( "%" + filtro.getProyNombre() + "%");
                separador += " and ";
            }

            if ( filtro.getProyPadre() != null )
            {
                jpql += separador + "p.proyPadre = ?" + ( ++numParametros );
                parametros.add( filtro.getProyPadre());
                separador += " and ";
            }
        }
        
        Query query = em.createQuery( jpql + " order by p.proyNombre");
        for ( int i = 0; i < numParametros; i++ )
        {
            query = query.setParameter( i + 1, parametros.get( i ));
        }
        
        return query.getResultList();
    }

    
    /**
     * Muestra la lista de proyectos que el usuario ha creado o para los que tiene
     * alguna tarea pendiente.
     * @param usuario
     * @param primerNivel
     * @return
     * @throws Exception 
     */
    @Override
    public List<Proyectos> findByUser(Usuarios usuario, boolean primerNivel ) throws Exception
    {
        String jpql = "select distinct p from Proyectos p where ( p.usuaIdAlta = :usuario or p in ( select distinct t.proyId from Tareas t, "
            + "in ( t.usuariosTareas ) u where u.usuariosTareasPK.usuaId = :usuaId ))";
        if ( primerNivel )
        {
            jpql += " and p.proyPadre is null ";
        }
        
        jpql += " order by p.proyNombre";

        return em.createQuery( jpql ).setParameter("usuario", usuario ).setParameter("usuaId", usuario.getUsuaId() ).getResultList();
    }
    
    public List<Proyectos> findAll() throws Exception
    {
        return em.createNamedQuery( "Proyectos.findAll").getResultList();
    }
}
