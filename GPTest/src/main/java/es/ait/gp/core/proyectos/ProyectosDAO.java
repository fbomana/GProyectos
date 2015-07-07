/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.usuarios.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 * DAO para la gestión de proyectos. Define el interfaz que debe cumplir el DAO, independiente de su implementación.
 *
 * @author aitkiar
 */
@Local
public interface ProyectosDAO
{
    void create( Proyectos proyecto ) throws Exception;

    void edit( Proyectos proyecto ) throws Exception;

    void remove( Proyectos proyecto ) throws Exception;
    
    public Proyectos find(Object id) throws Exception;
    
    public List<Proyectos> findByFilter( ProyectosFiltro filtro ) throws Exception;
    
    public List<Proyectos> findByUser( Usuarios usuario, boolean primerNivel ) throws Exception;
    
    public List<Proyectos>findAll() throws Exception;
}
