/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.proyectos;

import es.ait.gp.core.proyectos.EstadoProyectos;
import es.ait.gp.core.proyectos.EstadoProyectosMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author aitkiar
 */
@FacesConverter("es.ait.gp.web.proyectos.EstadoProyectosConverter")
public class EstadoProyectosConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return EstadoProyectosMap.getInstance().get( value );
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if ( value != null )
        {
            return ((EstadoProyectos)value ).getEsprEstado();
        }
        return null;
    }
    
}
