/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface EstadoProyectosDAO
{

    void create(EstadoProyectos estadoProyectos) throws Exception;

    void edit(EstadoProyectos estadoProyectos) throws Exception;

    void remove(EstadoProyectos estadoProyectos) throws Exception;

    EstadoProyectos find(Object id) throws Exception;

    List<EstadoProyectos> findAll() throws Exception;
    
}
