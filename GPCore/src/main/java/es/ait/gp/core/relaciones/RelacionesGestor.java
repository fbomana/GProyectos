/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.relaciones;

import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.TareasDAO;
import es.ait.gp.core.tareas.UsuariosTareas;
import es.ait.gp.core.tareas.UsuariosTareasDAO;
import es.ait.gp.core.tareas.UsuariosTareasPK;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Métodos de negocio sobre las relaciones.
 * 
 * @author aitkiar
 */
@Stateless
public class RelacionesGestor implements RelacionesGestorRemote
{
    @EJB
    private TareasDAO daoTareas;
    
    @EJB
    private UsuariosTareasDAO daoUsuariosTareas;
    
    @EJB
    private UsuariosDAO daoUsuarios;
    
            
    /**
     * Relaciona un usuario con una tarea con la relación indicada. Si el usuario ya tenía una relación con la tarea, la cambia si es distinta, y
     * si es la misma que ya había no hace nada.
     * 
     * @param tarea
     * @param usuario
     * @param relacion
     * @return
     * @throws Exception 
     */
    @Override
    public Tareas relacionar(Tareas tarea, Usuarios usuario, Relaciones relacion) throws Exception
    {
        tarea = daoTareas.find( tarea.getTareId());
        usuario = daoUsuarios.find( usuario.getUsuaId());
        
        UsuariosTareasPK relacionActual = new UsuariosTareasPK();
        relacionActual.setTareId( tarea.getTareId());
        relacionActual.setUsuaId( usuario.getUsuaId());
        
        UsuariosTareas relacionUsuario = daoUsuariosTareas.findByUsuarioTarea(relacionActual);
        if ( relacionUsuario == null )
        {
            relacionUsuario = new UsuariosTareas( new UsuariosTareasPK( usuario.getUsuaId(), tarea.getTareId(), relacion.getRelaId()));
            relacionUsuario.setRelaciones( relacion );
            relacionUsuario.setTareas( tarea );
            relacionUsuario.setUsuarios( usuario );
            daoUsuariosTareas.create(relacionUsuario);
            tarea.getUsuariosTareas().add( relacionUsuario );
            daoTareas.edit( tarea );
            usuario.getUsuariosTareasList().add( relacionUsuario );
            daoUsuarios.edit( usuario );
        }
        else if ( !relacionUsuario.getRelaciones().getRelaId().equals( relacion.getRelaId()))
        {
            relacionUsuario.setRelaciones( relacion );
            relacionUsuario.getUsuariosTareasPK().setRelaId( relacion.getRelaId());
            daoUsuariosTareas.edit(relacionUsuario);
            int i = 0;
            while ( !tarea.getUsuariosTareas().get( i ).getUsuarios().equals( usuario ) && i < tarea.getUsuariosTareas().size())
            {
                i++;
            }
            if ( i < tarea.getUsuariosTareas().size())
            {
                tarea.getUsuariosTareas().remove( i );
                tarea.getUsuariosTareas().add( relacionUsuario );
                daoTareas.edit( tarea );
            }
            
            i = 0;
            while ( !usuario.getUsuariosTareasList().get( i ).getTareas().equals( tarea ))
            {
                i++;
            }
            if ( i < usuario.getUsuariosTareasList().size() &&  i < usuario.getUsuariosTareasList().size())
            {
                usuario.getUsuariosTareasList().remove( i );
                usuario.getUsuariosTareasList().add( relacionUsuario );
                daoUsuarios.edit( usuario );
            }
        }
        
        return tarea;
    }

    @Override
    public Tareas desrelacionar(Tareas tarea, Usuarios usuario, Relaciones relacion) throws Exception
    {
        tarea = daoTareas.find( tarea.getTareId());
        usuario = daoUsuarios.find( usuario.getUsuaId());
        
        UsuariosTareasPK relacionActual = new UsuariosTareasPK();
        relacionActual.setTareId( tarea.getTareId());
        relacionActual.setUsuaId( usuario.getUsuaId());
        
        UsuariosTareas relacionUsuario = daoUsuariosTareas.findByUsuarioTarea(relacionActual);
        
        if ( relacionUsuario != null )
        {
            daoUsuariosTareas.remove( relacionUsuario );

            int i = 0;
            while ( !tarea.getUsuariosTareas().get( i ).getUsuarios().equals( usuario ) && i < tarea.getUsuariosTareas().size())
            {
                i++;
            }
            if ( i < tarea.getUsuariosTareas().size())
            {
                tarea.getUsuariosTareas().remove( i );
                daoTareas.edit( tarea );
            }
            
            i = 0;
            while ( !usuario.getUsuariosTareasList().get( i ).getTareas().equals( tarea ) &&  i < usuario.getUsuariosTareasList().size())
            {
                i++;
            }
            if ( i < usuario.getUsuariosTareasList().size())
            {
                usuario.getUsuariosTareasList().remove( i );
                daoUsuarios.edit( usuario );
            }
        }
        
        return tarea;
    }
    
}
