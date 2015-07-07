/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface DocumentacionDAO
{
    void create( Documentacion documentacion ) throws Exception;

    void edit( Documentacion documentacion ) throws Exception;

    void remove( Documentacion documentacion ) throws Exception;
    
    public Documentacion find(Object id) throws Exception;
    
    public List<Documentacion> findAll() throws Exception;

    /**
     * Guarda un nuevo documento asociado a una tarea.
     * 
     * @param documentacion
     * @param tareId
     * @throws Exception 
     */
    public void guardarDocumentoTarea( Documentacion documentacion, Integer tareId ) throws Exception;

}
