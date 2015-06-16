/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.documentacion;

import es.ait.gp.core.documentacion.TiposDocumentacion;
import es.ait.gp.core.documentacion.TiposDocumentacionMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author aitkiar
 */
@FacesConverter("es.ait.gp.web.documentacion.TiposDocumentacionConverter")
public class TiposDocumentacionConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if ( value != null && !"".equals( value.trim()))
        {
            return TiposDocumentacionMap.getInstance().get( new Integer( value ));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if ( value != null && value instanceof TiposDocumentacion )
        {
            return (( TiposDocumentacion )value).getTdocId().toString();
        }
        return null;
    }
    
}
