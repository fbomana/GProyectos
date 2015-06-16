/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.login;

import es.ait.gp.core.criptografia.CodificarCadenas;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import es.ait.gp.web.util.navegacion.PilaNavegacionInterface;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Bean que gestiona la pantalla de login.
 *
 * @author aitkiar
 */
@Named
@RequestScoped
public class LoginBean implements Serializable
{
    private String usuaLogin = "";
    private String usuaPassword = "";
    
    @EJB
    private UsuariosDAO dao;
    
    @EJB
    private PilaNavegacionInterface pilaNavegacion;

    /**
     * @return the usuaLogin
     */
    public String getUsuaLogin()
    {
        return usuaLogin;
    }

    /**
     * @param usuaLogin the usuaLogin to set
     */
    public void setUsuaLogin(String usuaLogin)
    {
        this.usuaLogin = usuaLogin;
    }

    /**
     * @return the usuaPassword
     */
    public String getUsuaPassword()
    {
        return usuaPassword;
    }

    /**
     * @param usuaPassword the usuaPassword to set
     */
    public void setUsuaPassword(String usuaPassword)
    {
        this.usuaPassword = usuaPassword;
    }
    
    /**
     * Método que comprueba si ha funcionado el proceso de login y devuelve 
     * "principal" o "login" enfunción de si ha funcionado o no.
     * @return 
     */
    public String login()
    {
        try
        {
            pilaNavegacion.reset();
            Usuarios usuario = dao.findByLogin( usuaLogin );
            String passwordCodificada = CodificarCadenas.codificar(usuaPassword, (( usuario.getUsuaFxAlta().getTime() / 1000 ) * 1000 )+ "");
            if ( usuario.getUsuaPassword().equals( passwordCodificada ))
            {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "usuario", usuario );
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "navegacion", pilaNavegacion );
                return "/protegido/principal?faces-redirect=true";
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage( null, new FacesMessage("Usuario o password no válidos."));
        return "login";
    }
    
}
