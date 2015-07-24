/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.relaciones;

import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.Remote;

/**
 *
 * @author aitkiar
 */
@Remote
public interface RelacionesGestorRemote
{
    /**
     * Relaciona un usuario con una tarea.
     * 
     * @param tarea
     * @param usuario
     * @param relacion
     * @return devuelve el objeto tarea actualizado con los cambios de la relación.
     * @throws Exception 
     */
    public Tareas relacionar( Tareas tarea, Usuarios usuario, Relaciones relacion ) throws Exception;
    
    /**
     * Elimina la relación entre un usuario y una tarea.
     * @param tarea
     * @param usuario
     * @param relacion
     * @return Devuelve el objeto tarea actualizado.
     * @throws Exception 
     */
    public Tareas desrelacionar( Tareas tarea, Usuarios usuario, Relaciones relacion ) throws Exception;
}
