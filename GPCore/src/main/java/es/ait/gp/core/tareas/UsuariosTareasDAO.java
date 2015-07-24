/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.usuarios.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface UsuariosTareasDAO
{
    void create( UsuariosTareas tarea ) throws Exception;

    void edit( UsuariosTareas tarea ) throws Exception;

    void remove( UsuariosTareas tarea ) throws Exception;
    
    public UsuariosTareas find(UsuariosTareasPK id) throws Exception;
    
    public UsuariosTareas findByUsuarioTarea (UsuariosTareasPK id) throws Exception;
    
}
