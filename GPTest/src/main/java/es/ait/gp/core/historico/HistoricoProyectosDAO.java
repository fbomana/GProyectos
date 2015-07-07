/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import es.ait.gp.core.proyectos.Proyectos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aitkiar
 */
@Local
public interface HistoricoProyectosDAO
{
    /**
     * Crea una nueva entrada en el historico.
     * 
     * @param historico 
     */
    public void create( HistoricoProyectos historico );
    
    /**
     * Busca una entrada concreta en el historico.
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public HistoricoProyectos find( int id ) throws Exception;
    
    /**
     * Devuelve un listado con las entradas de historico de un determinado proyecto
     * ordenadas por fecha en orden descendente.
     * 
     * @param proyecto
     * @return 
     */
    public List<HistoricoProyectos> find( Proyectos proyecto );
}
