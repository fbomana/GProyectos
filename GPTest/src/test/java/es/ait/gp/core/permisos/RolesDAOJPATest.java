/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.*;

/**
 *
 * @author aitkiar
 */
public class RolesDAOJPATest
{
    private static RolesDAO instance;
    private static EJBContainer container;
    public Roles role;
    
    
    public RolesDAOJPATest( EJBContainer container )
    {
        this.container = container;
        try
        {
            instance = (RolesDAO)container.getContext().lookup("java:global/classes/RolesDAOJPA");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
   
    public void test() throws Exception
    {
        System.out.println("create");
        role = new Roles();
        role.setRoleDescripcion("Role creado durante las pruebas de JPA");
        instance.create(role);

        System.out.println("find");
        instance.find(role.getRoleId());

        System.out.println("edit");
        role.setRoleDescripcion("Modificado");
        instance.edit(role);

        System.out.println("findAll");
        List resultado = instance.findAll();
        assertFalse( resultado.isEmpty());
    }
    
    public void cleanUp() throws Exception
    {
        System.out.println("remove");
        instance.remove( role );
    }
}
