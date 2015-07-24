/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.usuarios;

import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.Assert;

/**
 *
 * @author aitkiar
 */
public class UsuariosDAOJPATest
{
    private static UsuariosDAO dao;
    private static RolesDAO daoRoles;
    private static EJBContainer container;

    public Usuarios usuario;
    
    public UsuariosDAOJPATest( EJBContainer container ) throws Exception
    {
        this.container = container;
        dao = ( UsuariosDAO )container.getContext().lookup("java:global/classes/UsuariosDAOJPA");
        daoRoles = ( RolesDAO )container.getContext().lookup("java:global/classes/RolesDAOJPA");
    }

    public void test() throws Exception
    {
        usuario = new Usuarios();
        usuario.setUsuaActivo( true );
        usuario.setUsuaEmail("test@a.a");
        usuario.setUsuaFxAlta( new java.util.Date());
        usuario.setUsuaLogin("usujunit");
        usuario.setUsuaNombre("Usuario creado para los test de JUNIT");
        usuario.setUsuaPassword("test");
        
        Roles role = daoRoles.findByName("Superadministrador");
        List<Roles> roles = new ArrayList();
        roles.add( role );
        usuario.setRolesList( roles );

        // Create
        dao.create(usuario);
        
        Assert.assertNotNull( usuario.getUsuaId());
        System.out.println("Usuario creado correctamente");
        
        // Update
        usuario.setUsuaNombre("Usuario modificado para los test de JUNIT");
        dao.edit( usuario );
        Assert.assertEquals("Usuario modificado para los test de JUNIT", usuario.getUsuaNombre());
        System.out.println("Usuario modificado correctamente");

        
    }
    
    public void cleanUp() throws Exception
    {
        //remove
        dao.remove( usuario );
        Assert.assertNull( dao.findByLogin("usujunit"));
        System.out.println("usuario borrado correctamente");
    }
    
}
