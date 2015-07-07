/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.relaciones;

import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz que implementan los DAOs que consultan la traba de relacionese.
 * 
 * @author aitkiar
 */
@Local
public interface RelacionesDAO
{

    void create(Relaciones relaciones);

    void edit(Relaciones relaciones);

    void remove(Relaciones relaciones);

    Relaciones find(Object id);

    List<Relaciones> findAll();
    
}
