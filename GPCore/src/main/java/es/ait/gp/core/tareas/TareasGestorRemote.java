/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.Remote;

/**
 *
 * @author aitkiar
 */
@Remote
public interface TareasGestorRemote
{
    public Tareas nuevaTarea( Tareas tarea, Usuarios usuario ) throws Exception;
    public void modificarTarea( Tareas tarea, Usuarios usuario ) throws Exception;
}
