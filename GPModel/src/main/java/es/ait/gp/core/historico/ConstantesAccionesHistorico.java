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
    public static final AccionesHistorico PROYECTOS_NUEVA_DOCUMENTACION = new AccionesHistorico( 5, "Proyectos - nueva documentacion asociada al proyecto");
    public static final AccionesHistorico PROYECTOS_NUEVA_VERSION = new AccionesHistorico( 6, "Proyectos - nueva versión de documentacion");

}
