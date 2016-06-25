/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

/**
 *
 * @author aitkiar
 */
public class ConstantesAccionesHistorico
{
    public static final AccionesHistorico PROYECTOS_NUEVO = new AccionesHistorico( 1, "Proyectos - nuevo proyecto");
    public static final AccionesHistorico PROYECTOS_CONSULTAR = new AccionesHistorico( 2, "Proyectos - proyecto consultado");
    public static final AccionesHistorico PROYECTOS_EDITAR = new AccionesHistorico( 3, "Proyectos - proyecto modificado");
    public static final AccionesHistorico PROYECTOS_NUEVA_TAREA = new AccionesHistorico( 4, "Proyectos - nueva tarea asociada al proyecto");
    public static final AccionesHistorico PROYECTOS_NUEVA_DOCUMENTACION = new AccionesHistorico( 5, "Proyectos - nueva documentación asociada al proyecto");
    public static final AccionesHistorico PROYECTOS_ELIMINAR_DOCUMENTACION = new AccionesHistorico( 6, "Proyectos - eliminar la documentación asociada al proyecto");
    public static final AccionesHistorico PROYECTOS_NUEVA_VERSION = new AccionesHistorico( 7, "Proyectos - nueva versión de documentación");
    public static final AccionesHistorico PROYECTOS_ELIMINAR_VERSION = new AccionesHistorico( 8, "Proyectos - eliminar una versión de una documentación asociada al proyecto");
    
    
    public static final AccionesHistorico TAREAS_NUEVA = new AccionesHistorico( 10, "Tareas - nueva tarea");
    public static final AccionesHistorico TAREAS_CONSULTAR = new AccionesHistorico( 11, "Tareas - tarea consultada");
    public static final AccionesHistorico TAREAS_EDITAR = new AccionesHistorico( 12, "Tareas - tarea modificada");
    public static final AccionesHistorico TAREAS_NUEVA_DOCUMENTACION = new AccionesHistorico( 13, "Tareas - nueva documentación asociada a la tarea");
    public static final AccionesHistorico TAREAS_ELIMINAR_DOCUMENTACION = new AccionesHistorico( 14, "Tareas - eliminar documentación asociada a la tarea");
    public static final AccionesHistorico TAREAS_NUEVA_VERSION = new AccionesHistorico( 15, "Tareas - nueva versión de documentación");
    public static final AccionesHistorico TAREAS_ELIMINAR_VERSION = new AccionesHistorico( 16, "Tareas - eliminar versión de doucumentación asociada a la tarea");
}

