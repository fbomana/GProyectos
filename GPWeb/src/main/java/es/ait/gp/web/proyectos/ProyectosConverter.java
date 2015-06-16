/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.proyectos;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.proyectos.ProyectosDAO;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Clase para poder incluir los proytectos como parámetros en los formularios
 * de jsf.
 * 
 * @author aitkiar
 */
@FacesConverter("es.ait.gp.web.proyectos.ProyectosConverter")
public class ProyectosConverter implements Converter
{
    @EJB
    ProyectosDAO dao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if ( value != null && !"".equals( value.trim()))
        {
            try
            {
                return dao.find( new Integer( value ));
            }
            catch ( Exception e )
            {
                context.addMessage( component.getClientId(), new FacesMessage("No existe el proyecto padre: " + value));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if ( value != null && value instanceof Proyectos )
        {
            return (( Proyectos )value).getProyId().toString();
        }
        return null;
    }
    
}
