/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aitkiar
 */
public interface PilaNavegacionInterface
{
    public void reset();
    public void navegar( Object objeto ) throws IllegalArgumentException;
    public Navegacion head();
    public Navegacion anterior();
    public List<Navegacion> getMenu();
    public void checkNavegacion( HttpServletRequest request ) throws Exception;
}
