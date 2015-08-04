/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.embeddable.EJBContainer;

/**
 *
 * @author aitkiar
 */
public class ProyectosGestorTest
{
    private EJBContainer container;
    private ProyectosGestor gestor;
    public Proyectos proyecto;
    
    public ProyectosGestorTest( EJBContainer container ) throws Exception
    {
        this.container = container;
        gestor = ( ProyectosGestor) container.getContext().lookup("java:global/classes/ProyectosGestor");
    }
    
    public void test( Usuarios usuario ) throws Exception
    {
        proyecto = new Proyectos();
        proyecto.setProyFxAlta( new java.util.Date());
        proyecto.setProyDescripcion("Proyecto creado durante las pruebas unitarias con JUNIT.");
        
        try
        {
            EstadoProyectosDAO dao = ( EstadoProyectosDAO )container.getContext().lookup("java:global/classes/EstadoProyectosDAOJPA");
            proyecto.setProyEstado( dao.findAll().get( 0 ));
        }
        catch ( Exception e )
        {
            
        }
        
        proyecto.setProyNombre("Proyecto de test");
        proyecto.setUsuaIdAlta( usuario );
        
        gestor.nuevoProyecto( proyecto, usuario );
        
        proyecto.setProyDescripcion( proyecto.getProyDescripcion() + ". Modificado");
        gestor.modificarProyecto( proyecto, usuario );
    }
    
    public void cleanUp( Usuarios usuario ) throws Exception
    {
        gestor.borrarProyecto( proyecto, usuario );
    }
}
