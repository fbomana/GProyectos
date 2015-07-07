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
 * Interfaz que cumplen los DAO's que atacan la tabla tareas.
 *
 * @author aitkiar
 */
@Local
public interface TareasDAO
{
    void create( Tareas tarea ) throws Exception;

    void edit( Tareas tarea ) throws Exception;

    void remove( Tareas tarea ) throws Exception;
    
    public Tareas find(Object id) throws Exception;
    
    public List<Tareas> findByUser( Usuarios usuario ) throws Exception;

    public List<Tareas> findByFilter( Tareas tarea ) throws Exception;
}
