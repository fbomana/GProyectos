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
public interface TiposDocumentacionDAO
{
    public void create( TiposDocumentacion tipo ) throws Exception;

    public void edit( TiposDocumentacion tipo ) throws Exception;

    public void remove( TiposDocumentacion tipo ) throws Exception;
    
    public TiposDocumentacion find(Object id) throws Exception;
    
    public List<TiposDocumentacion> findAll() throws Exception;

}
