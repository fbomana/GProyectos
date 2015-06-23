/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.Remote;

/**
 *
 * @author aitkiar
 */
@Remote
public interface ProyectosGestorRemote
{
    public void nuevoProyecto( Proyectos proyecto, Usuarios usuario ) throws Exception;
    public void modificarProyecto( Proyectos proyecto, Usuarios usuario ) throws Exception;

}
