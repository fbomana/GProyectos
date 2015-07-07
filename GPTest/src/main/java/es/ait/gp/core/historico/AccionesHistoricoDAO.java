/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface AccionesHistoricoDAO
{
    public void create( AccionesHistorico accion ) throws Exception;
    
    public AccionesHistorico find( int id ) throws Exception;
}
