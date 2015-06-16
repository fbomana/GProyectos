/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.RequiredValidator;

/**
 *
 * @author aitkiar
 */
public class SkipRequiredValidator extends RequiredValidator
{

    @Override
    public void validate(final FacesContext context, final UIComponent component, final Object value)
    {
        if (ValidatorUtil.check(context))
        {
            super.validate(context, component, value);
        }
    }
}
