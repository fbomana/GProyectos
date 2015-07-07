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
public interface RolesDAO
{
    void create(Roles roles) throws Exception;

    void edit(Roles roles) throws Exception;

    void remove(Roles roles) throws Exception;

    Roles find(Object id) throws Exception;
    
    Roles findByName(String nombre ) throws Exception;

    List<Roles> findAll() throws Exception;
}
