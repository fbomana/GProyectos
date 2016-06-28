/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.permisos;

import es.ait.gp.core.permisos.Permisos;
import es.ait.gp.core.permisos.PermisosDAO;
import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author aitkiar
 */
@FacesConverter("es.ait.gp.web.permisos.RolesConverter")
public class RolesConverter implements Converter
{

    @EJB
    RolesDAO dao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        try
        {
            return dao.find( new Integer( value ));
        }
        catch ( Exception e )
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return ((Roles) value).getRoleId().toString();
    }

}
