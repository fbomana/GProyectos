/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.historico.ConstantesAccionesHistorico;
import es.ait.gp.core.historico.HistoricoTareas;
import es.ait.gp.core.historico.HistoricoTareasDAO;
import es.ait.gp.core.usuarios.Usuarios;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Clase de negocio para el alta y la modificación de tareas. Forma parte del interfaz remoto de la aplicación.
 * @author aitkiar
 */
@Stateless
public class TareasGestor implements TareasGestorRemote
{
    @EJB
    private TareasDAO daoTareas;
    
    @EJB
    private HistoricoTareasDAO daoHistorico;
    
    @Override
    public Tareas nuevaTarea(Tareas tarea, Usuarios usuario) throws Exception
    {
        daoTareas.create( tarea );
        HistoricoTareas historico = new HistoricoTareas( tarea, usuario );
        historico.setHitrFxAccion( new java.util.Date());
        historico.setHitrDescripcion("Alta de tarea");
        historico.setAchiId( ConstantesAccionesHistorico.TAREAS_NUEVA );
        daoHistorico.create( historico );
        return tarea;
    }

    @Override
    public void modificarTarea(Tareas tarea, Usuarios usuario) throws Exception
    {
        daoTareas.edit( tarea );
        HistoricoTareas historico = new HistoricoTareas( tarea, usuario );
        historico.setHitrFxAccion( new java.util.Date());
        historico.setHitrDescripcion("Modificación de tarea");
        historico.setAchiId( ConstantesAccionesHistorico.TAREAS_EDITAR );
        daoHistorico.create( historico );
    }
    
}
