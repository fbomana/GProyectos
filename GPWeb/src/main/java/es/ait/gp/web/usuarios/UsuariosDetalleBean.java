/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.usuarios;

import es.ait.gp.core.criptografia.CodificarCadenas;
import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Clase que muestra el detalle de usuario, para el alta o la modificacion
 * @author aitkiar
 */
@Named
@ManagedBean
public class UsuariosDetalleBean
{
    private Usuarios usuario;
    private Integer usuaId;
    private Boolean passwordCambiada;
    private String filtro;
    private List<Roles> roles;
    
    @EJB
    private UsuariosDAO dao;
    
    @EJB
    private RolesDAO rolesDao;
    
    public UsuariosDetalleBean()
    {
        passwordCambiada = false;
    }
    
    @PostConstruct
    private void ObtenerUsuario()
    {
        System.out.println("Entro en el constructor");
        try
        {
            if ( FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get( "filtro" ) != null )
            {
                filtro = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get( "filtro" );
            }
            else if ( FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get( "form:filtro" ) != null )
            {
                filtro = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get( "form:filtro" );
            }
            if ( usuaId == null )
            {
                String aux = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get( "usuaId" );
                if ( aux == null )
                {
                    aux = FacesContext.getCurrentInstance().getExternalContext().
                        getRequestParameterMap().get( "form:usuaId" );
                    
                }
                usuaId = Integer.parseInt( aux );
            }
            System.out.println( usuaId );
            usuario = dao.find( usuaId );
            roles = rolesDao.findAll();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("No se encontró en BBDD");
            usuario = new Usuarios();
            usuario.setUsuaActivo( false );
        }
    }

    /**
     * @return the usuario
     */
    public Usuarios getUsuario()
    {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuarios usuario)
    {
        this.usuario = usuario;
    }

    /**
     * @return the usuaId
     */
    public Integer getUsuaId()
    {
        return usuaId;
    }

    /**
     * @param usuaId the usuaId to set
     */
    public void setUsuaId(Integer usuaId)
    {
        this.usuaId = usuaId;
    }

    /**
     * @return the roles
     */
    public List<Roles> getRoles()
    {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Roles> roles)
    {
        this.roles = roles;
    }
    
    /**
     * @return the filtro
     */
    public String getFiltro()
    {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro)
    {
        this.filtro = filtro;
    }
    
    /**
     * Listener que se actualiza cuando se cambia la password para que sepamos 
     * que hay que recodificarla.
     * @param event 
     */
    public void passwordCambiada( ValueChangeEvent event )
    {
        passwordCambiada = true;
    }
    
    /**
     * Si se ha cambiado la password recodifica el nuevo valor, y a continuacion
     * graba el usuario en BBDD.
     * @return 
     * @throws java.lang.Exception 
     */
    public String guardar() throws Exception
    {
        if ( usuario.getUsuaId() == null )
        {
            usuario.setUsuaFxAlta( new Date( System.currentTimeMillis()));
            dao.create( usuario );
        }
        else
        {
            if ( passwordCambiada )
            {
                usuario.setUsuaId(usuaId);
                usuario.setUsuaPassword( CodificarCadenas.codificar(usuario.getUsuaPassword(),
                    ( usuario.getUsuaFxAlta().getTime() / 1000 ) * 1000 + ""));
            }
            dao.edit( usuario );
        }
        return "usuariosBuscarListado";
    }

    /**
     * Devuelve el control al listado.
     * @return 
     */
    public String cancelar() throws Exception
    {
        return "usuariosBuscarListado";
    }
}
