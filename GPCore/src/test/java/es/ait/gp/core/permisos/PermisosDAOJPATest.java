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
public class PermisosDAOJPATest
{
    private PermisosDAO instance;
    private EJBContainer container;
    public Permisos permiso;
    
    
    public PermisosDAOJPATest( EJBContainer container )
    {
        this.container = container;
        try
        {
            instance = (PermisosDAO)container.getContext().lookup("java:global/classes/PermisosDAOJPA");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }


    /**
     * Test of create method, of class PermisosDAOJPA.
     */
    public void test() throws Exception
    {
        System.out.println("create");
        permiso = new Permisos();
        permiso.setPermNombre("TEST_JPATEST");
        permiso.setPermDescripcion("Permiso creado durante las pruebas de JPA");
        instance.create(permiso);

        System.out.println("find");
        instance.find(permiso.getPermId());

        System.out.println("edit");
        permiso.setPermDescripcion("Descripcion modificada");
        instance.edit(permiso);

        System.out.println("findAll");
        List resultado = instance.findAll();
        assertFalse( resultado.isEmpty());
    }
    
    public void cleanUp() throws Exception
    {
        instance.remove( permiso );
    }
}
