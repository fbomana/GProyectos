/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.jsf;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Copnverter que trunca un string a la longitud indicada. Solo se puede usar para salida de datos.
 * @author aitkiar
 */
@FacesConverter("es.ait.gp.web.util.jsf.TruncatedStringConverter")
public class TruncatedStringConverter extends HTMLTextConverter
{
    private final Logger logger = Logger.getLogger( "TruncatedStringConverter" );
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if ( "".equals( value ))
        {
            return null;
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if ( value != null )
        {
            String retorno = super.getAsString(context, component, value);
            int longitud = 100;
            try
            {
                longitud = Integer.parseInt( component.getAttributes().get("truncatedLength").toString() );
            }
            catch ( Exception e )
            {
                logger.warning("Llamada a TruncatedStringConverter sin especificar el atributo truncatedLength. Usando longitud por defecto: 100");
            }
            if ( retorno.length() > longitud )
            {
                retorno = retorno.substring( 0, longitud ) + "...";
                if ( retorno.lastIndexOf("<") != -1 )
                {
                    retorno = retorno.substring( 0, retorno.lastIndexOf("<")) + "...";
                }
            }
            return retorno;
        }
        return "";
    }    
}
