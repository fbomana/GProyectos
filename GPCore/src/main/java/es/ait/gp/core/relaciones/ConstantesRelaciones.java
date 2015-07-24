/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.relaciones;

/**
 *
 * @author aitkiar
 */
public class ConstantesRelaciones
{
    public static final Relaciones ASIGNADA = new Relaciones( 1, "ASIGNADA", "Tarea asignada al usuario");
    public static final Relaciones CONTROL = new Relaciones( 2, "CONTROL", "El usuario recibe información sobre los cambios en la tarea");
}
