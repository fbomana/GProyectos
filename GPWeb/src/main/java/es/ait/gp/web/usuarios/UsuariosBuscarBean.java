/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.usuarios;


import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.core.usuarios.UsuariosDAO;
import es.ait.gp.web.util.RequestUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Bean que contiene el negocio y los parámetros de la búsqueda de usuarios.
 * 
 * @author aitkiar
 */
@Named
@RequestScoped
public class UsuariosBuscarBean implements Serializable
{
    private static final long serialVersionUID = 1l;
    private String login;
    private String nombre;
    private String email;
    private Boolean activo = true;
    private int indiceListado = 0;
    private Integer indiceSeleccionado;
    private Usuarios filtro;
    private Integer roleId;
    private List<Roles> roles;
    
    private List<Usuarios> usuarios;
    
    @EJB
    UsuariosDAO dao;
    
    @EJB
    RolesDAO rolesDao;
    
    public UsuariosBuscarBean()
    {
        activo = true;
    }

    /**
     * Comprueba si existe el filtro, y si es así, lo descodifica y llama a buscar
     * para obtener la lista de usuarios.
     */
    @PostConstruct
    private void compruebaFiltro()
    {
        if ( FacesContext.getCurrentInstance().
            getExternalContext().getRequestParameterMap().get( "form:filtro" ) != null )
        {
            try
            {
                setFiltro( FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get( "form:filtro" ) );
                login = filtro.getUsuaLogin();
                nombre = filtro.getUsuaNombre();
                email = filtro.getUsuaEmail();
                activo = filtro.getUsuaActivo();
                if ( filtro.getRolesList() != null && !filtro.getRolesList().isEmpty())
                {
                    roleId = filtro.getRolesList().get(0).getRoleId();
                }

                buscar();
            }
            catch ( Exception e )
            {
                System.out.println("Error al recuperar el filtro");
                e.printStackTrace();
            }
        }
        try
        {
            roles = rolesDao.findAll();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the activo
     */
    public Boolean getActivo()
    {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo)
    {
        this.activo = activo;
    }
    
    public List<Usuarios> getUsuarios()
    {
        return usuarios;
    }
    
    /**
     * @return the filtro
     */
    public String getFiltro()
    {
        try
        {
            return RequestUtils.encodeObject( filtro );
        }
        catch ( Exception e )
        {
            return "";
        }
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro( String filtro )
    {
        try
        {
            this.filtro = (Usuarios)RequestUtils.decodeObject( filtro );
        }
        catch ( Exception e )
        {
            this.filtro = null;
        }
    }
    
    /**
     * @return the roleId
     */
    public Integer getRoleId()
    {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
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
     * Método para ejecutar cuando se pulse el commandButton de limpiar. 
     * Devuelve el formulario a su estado original.
     * @return 
     */
    public String limpiar()
    {
        login = null;
        nombre = null;
        email = null;
        activo = true;
        return null;
    }
    
    public int getIndiceListado()
    {
        return indiceListado++;
    }

    /**
     * Método que ejecuta la búsqueda de usuarios en función de los valores recogidos
     * del formulario y guarda el resultado en la variable usuarios que será utilizada en
     * la siguiente página para mostrar el listado.
     * @return 
     * @throws Exception 
     */
    public String buscar() throws Exception
    {
        filtro = new Usuarios();
        filtro.setUsuaActivo( activo );
        filtro.setUsuaLogin( login == null || login.trim().equals("") ? null : login.trim());
        filtro.setUsuaNombre( nombre == null || nombre.trim().equals("") ? null : nombre.trim());
        filtro.setUsuaEmail( email == null || email.trim().equals("") ? null : email.trim());
        filtro.setRolesList( new ArrayList<Roles>());
        if ( roleId != null )
        {
            for ( Roles role : roles )
            {
                if ( Objects.equals(role.getRoleId(), roleId) )
                {
                    filtro.getRolesList().add( role );
                    break;
                }
            }
        }
        
        usuarios = dao.findByFilter( filtro );
        indiceListado = 0;
        
        return "usuariosBuscarListado";
    }
}