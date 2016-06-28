/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.usuarios;

import es.ait.gp.core.criptografia.CodificarCadenas;
import es.ait.gp.core.permisos.Roles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * DAO para la tabla usuarios implementado como JPA.
 * 
 * @author aitkiar
 */
@Stateless
public class UsuariosDAOJPA implements UsuariosDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UsuariosDAOJPA()
    {
    }

    /**
     * Guarda el usuario en BBDD. Al generar la contraseña trunca el número de milisegundos, 
     * ya que al recuperar la fecha de BBDD, ese valor se pierde, y por tanto es imposible 
     * hacer un login con la misma "salt"
     * @param usuario
     * @throws Exception 
     */
    @Override
    public void create(Usuarios usuario) throws Exception
    {
        usuario.setUsuaPassword ( CodificarCadenas.codificar( usuario.getUsuaPassword(), ((usuario.getUsuaFxAlta().getTime() / 1000 )* 1000 ) + ""));
        em.persist( usuario );
        if ( usuario.getRolesList() != null && !usuario.getRolesList().isEmpty())
        {
            for ( Roles role : usuario.getRolesList())
            {
                if ( role.getUsuariosList() == null )
                {
                    role.setUsuariosList( new ArrayList<Usuarios>());
                }
                role.getUsuariosList().add( usuario );
                em.merge( role );
            }
        }
    }

    @Override
    public void edit(Usuarios usuario) throws Exception
    {
        em.merge( usuario );
        
        if ( usuario.getRolesList() != null && !usuario.getRolesList().isEmpty())
        {
            for ( Roles role : usuario.getRolesList())
            {
                if ( role.getUsuariosList() == null )
                {
                    role.setUsuariosList( new ArrayList<Usuarios>());
                }
                if ( !role.getUsuariosList().contains( usuario ))
                {
                    role.getUsuariosList().add( usuario );
                    em.merge( role );
                }
            }
                
        }
    }

    @Override
    public void remove(Usuarios usuario) throws Exception
    {
        usuario = em.find( Usuarios.class, usuario.getUsuaId()); // necesario para que se pueda borrar el objeto si no está bajo el control del contenedor.
        em.remove( usuario );
    }

    @Override
    public Usuarios find(Object id) throws Exception
    {
        return em.find( Usuarios.class, id );
    }
    
    @Override
    public Usuarios findByLogin( String login ) throws Exception
    {
        try
        {
            return ( Usuarios )em.createNamedQuery("Usuarios.findByUsuaLogin").setParameter( "usuaLogin", login).getSingleResult();
        }
        catch ( NoResultException nre )
        {
            return null;
        }
    }

    @Override
    public void changePassword(Usuarios usuario) throws Exception
    {
        usuario.setUsuaPassword ( CodificarCadenas.codificar( usuario.getUsuaPassword(), ( usuario.getUsuaFxAlta().getTime() / 1000 ) * 1000 + ""));
        edit( usuario );
    }
    
    @Override
    public List<Usuarios>findByFilter( Usuarios usuario ) throws Exception
    {
        String jql = "select u from Usuarios u";
        String separador = " and ";
        if ( usuario != null )
        {
            if ( usuario.getRolesList() != null && !usuario.getRolesList().isEmpty())
            {
                jql += " left join u.rolesList r where r.roleId = :roleId and u.usuaActivo = :activo";
            }
            else
            {
                jql += " where u.usuaActivo = :activo";
            }
            
            if ( usuario.getUsuaLogin() != null )
            {
                jql += separador + "u.usuaLogin like :login";
            }
            
            if ( usuario.getUsuaNombre() != null )
            {
                jql += separador + "u.usuaNombre like :nombre";
            }
            
            if ( usuario.getUsuaEmail() != null )
            {
                jql += separador + "u.usuaEmail like :email";
            }
        }
        
        jql += " order by u.usuaNombre";
        Query query = em.createQuery( jql );
        if ( usuario != null )
        {
            if ( usuario.getRolesList() != null && !usuario.getRolesList().isEmpty())
            {
                query = query.setParameter("roleId",  usuario.getRolesList().get( 0 ).getRoleId());
            }

            query = query.setParameter("activo", usuario.getUsuaActivo() ? "S" : "N");
            
            if ( usuario.getUsuaLogin() != null )
            {
                query = query.setParameter("login", "%" + usuario.getUsuaLogin() + "%");
            }
            
            if ( usuario.getUsuaNombre() != null )
            {
                query = query.setParameter("nombre", "%" + usuario.getUsuaNombre() + "%");
            }
            
            if ( usuario.getUsuaEmail() != null )
            {
                query = query.setParameter("email", "%" + usuario.getUsuaEmail() + "%");
            }
        }
        
        return query.getResultList();
    }
}
