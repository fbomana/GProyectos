/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.Remote;

/**
 *
 * @author aitkiar
 */
@Remote
public interface DocumentacionGestorRemote
{
    /**
     * Añade una nueva documentación o versión de documentación a un proyecto y 
     * gestiona la actualización del histórico.
     * @param documentacion
     * @param proyId
     * @throws Exception 
     */
    public void guardarDocumentoProyecto( Documentacion documentacion, Versiones version, Integer proyId, Usuarios usuario ) throws Exception;
    
    /**
     * Añade una nueva documentación o versión de la misma a una tarea y gestiona la actualizacioón del histórico de tareas.
     * @param documentacion
     * @param version
     * @param tareId
     * @param usuario
     * @throws Exception 
     */
    public void guardarDocumentoTarea( Documentacion documentacion, Versiones version, Integer tareId, Usuarios usuario ) throws Exception;
}
