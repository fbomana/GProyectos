/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Convierte un texto plano a html para la correcta presentación de tags, comillas, ...
 * 
 * @author aitkiar
 */
@FacesConverter("es.ait.gp.web.util.jsf.HTMLTextConverter")
public class HTMLTextConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return StringEscapeUtils.unescapeHtml4( value.replaceAll("<br/>", "\n"));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if ( value == null )
        {
            return "";
        }
        
        return StringEscapeUtils.escapeHtml4( value.toString()).replaceAll("\n", "<br/>");
    }
    
}
