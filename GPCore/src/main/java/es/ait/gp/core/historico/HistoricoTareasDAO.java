/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import es.ait.gp.core.tareas.Tareas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface HistoricoTareasDAO
{
    /**
     * Crea una nueva entrada en el histórico.
     * @param historico 
     */
    public void create( HistoricoTareas historico ) throws Exception;
    
    /**
     * Busca una entrada concreta en el historico.
     * @param id 
     * @return 
     */
    public HistoricoTareas find( int id ) throws Exception;
    
    /**
     * Busca todas las entradas en el historico para una tarea determinada.
     * @param tarea
     * @return 
     */
    public List<HistoricoTareas> find ( Tareas tarea ) throws Exception;
}
