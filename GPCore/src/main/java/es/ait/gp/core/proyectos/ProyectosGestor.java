/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.historico.ConstantesAccionesHistorico;
import es.ait.gp.core.historico.HistoricoProyectos;
import es.ait.gp.core.historico.HistoricoProyectosDAO;
import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aitkiar
 */
@Stateless
public class ProyectosGestor implements ProyectosGestorRemote
{
    @EJB
    private ProyectosDAO dao;
    
    @EJB
    private HistoricoProyectosDAO daoHistorico;
    
    @EJB
    private UsuariosDAO daoUsuarios;
    
    @EJB
    private RolesDAO daoRoles;
    
            
    @Override
    public Proyectos nuevoProyecto(Proyectos proyecto, Usuarios usuario) throws Exception
    {
        HistoricoProyectos historico = new HistoricoProyectos();
        historico.setAchiId( ConstantesAccionesHistorico.PROYECTOS_NUEVO);
        historico.setHiprDescripcion("Nuevo Proyecto: " + proyecto.getProyNombre());
        historico.setHiprFxAccion( proyecto.getProyFxAlta());
        historico.setProyDescripcionNew( proyecto.getProyDescripcion());
        historico.setProyEstadoNew( proyecto.getProyEstado().getEsprEstado());
        historico.setProyFxFinNew( proyecto.getProyFxFin());
        historico.setProyNombreNew( proyecto.getProyNombre());
        historico.setProyPadreNew( proyecto.getProyPadre() != null ? proyecto.getProyPadre().getProyId() : null );
        historico.setUsuaIdAccion( usuario );
        
        dao.create( proyecto );
        historico.setProyId( proyecto );
        daoHistorico.create( historico );
        return proyecto;
    }

    @Override
    public void modificarProyecto(Proyectos proyecto, Usuarios usuario) throws Exception
    {
        HistoricoProyectos historico = new HistoricoProyectos();
        historico.setAchiId( ConstantesAccionesHistorico.PROYECTOS_EDITAR);
        historico.setHiprDescripcion("Proyecto Modificado");
        historico.setHiprFxAccion( new Date());
        historico.setProyDescripcionNew( proyecto.getProyDescripcion());
        historico.setProyEstadoNew( proyecto.getProyEstado().getEsprEstado());
        historico.setProyFxFinNew( proyecto.getProyFxFin());
        historico.setProyNombreNew( proyecto.getProyNombre());
        historico.setProyPadreNew( proyecto.getProyPadre() != null ? proyecto.getProyPadre().getProyId() : null );
        historico.setUsuaIdAccion( usuario );
        
        dao.edit( proyecto );
        historico.setProyId(proyecto);
        daoHistorico.create( historico );        
    }

    @Override
    public void borrarProyecto( Proyectos proyecto, Usuarios usuario ) throws Exception
    {
        proyecto = dao.find(  proyecto.getProyId());
        usuario = daoUsuarios.find( usuario.getUsuaId());
        
        Roles role = daoRoles.findByName( "Superadministrador" );
        if ( proyecto == null || usuario == null )
        {
            throw new Exception("El proyecto o el usuario no existen");
        }
        
        if ( !usuario.getRolesList().contains( role ) && !proyecto.getUsuaIdAlta().equals( usuario ))
        {
            throw new Exception("El usuario no puede eliminar el proyecto");
        }
        
        //TO-DO:
        //Hacer borrado recursivo de los elementos del proyecto ( tareas, documentacion, ... )
        daoHistorico.remove( proyecto );
        dao.remove( proyecto );
    }
    
}
