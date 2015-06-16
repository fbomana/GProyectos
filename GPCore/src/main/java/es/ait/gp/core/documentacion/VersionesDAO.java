/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz local del DAO de versiones.
 * 
 * @author aitkiar
 */
@Local
public interface VersionesDAO
{
    public void create( Versiones version ) throws Exception;

    public void edit( Versiones version ) throws Exception;

    public void remove( Versiones versiion ) throws Exception;
    
    public Versiones find(Object id) throws Exception;
    
    public List<Versiones> findAll() throws Exception;
    
}
