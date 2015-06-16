/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util;

import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



/**
 *
 * @author aitkiar
 */
public class ValidationUtils
{

    public static void printValidationErrors(Object entity)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0)
        {
            Iterator iterator = constraintViolations.iterator();
            while (iterator.hasNext())
            {
                ConstraintViolation cv = (ConstraintViolation)iterator.next();
                System.out.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

            }
        }
    }
}