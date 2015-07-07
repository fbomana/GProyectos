/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface PermisosDAO
{
    void create(Permisos permisos) throws Exception;

    void edit(Permisos permisos) throws Exception;

    void remove(Permisos permisos) throws Exception;

    Permisos find(Object id) throws Exception;
    
    Permisos findByName(String nombre) throws Exception;

    List<Permisos> findAll() throws Exception;
}
