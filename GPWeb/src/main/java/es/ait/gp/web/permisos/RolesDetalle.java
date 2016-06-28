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
import es.ait.gp.web.util.RequestUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 * Backing bean para la página de detalle de roles.
 * 
 * @author aitkiar
 */
@Named
@RequestScoped
public class RolesDetalle
{
    @EJB
    private RolesDAO dao;
    
    @EJB
    private PermisosDAO daoPermisos;
    
    private Roles role;
    private List<Permisos> permisos;
    
    @PostConstruct
    private void init()
    {
        try
        {
            Integer roleId;
            RequestUtils.pintarParametrosRequest();
            try
            {
                roleId = new Integer( RequestUtils.getRequestParameter("roleId"));
            }
            catch ( Exception e )
            {
                roleId = new Integer( RequestUtils.getRequestParameter("form:roleId"));
            }
            if ( roleId == null )
            {
                role = new Roles();
                role.setPermisosList( new ArrayList<Permisos>());
            }
            else
            {
                role = dao.find( roleId );
            }
                            
            permisos = daoPermisos.findAll();
        }
        catch ( Exception e )
        {
        }
    }
    
    public String guardarCambios() throws Exception
    {
        for ( Permisos permiso : permisos )
        {
            if ( !role.getPermisosList().contains( permiso ))
            {
                permiso.getRolesList().remove( role );
            }
        }
        
        for ( Permisos permiso : role.getPermisosList())
        {
            if ( !permiso.getRolesList().contains( role ))
            {
                  permiso.getRolesList().add( role );
            }
        }
        dao.edit( role );
        return cancelar();
    }
    
    /**
     * @return the role
     */
    public Roles getRole()
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Roles role)
    {
        this.role = role;
    }
    
    public String cancelar()
    {
        return "/protegido/permisos/rolesLista.xhtml?faces-redirect=true";
    }

    /**
     * @return the permisos
     */
    public List<Permisos> getPermisos()
    {
        return permisos;
    }

    /**
     * @param permisos the permisos to set
     */
    public void setPermisos(List<Permisos> permisos)
    {
        this.permisos = permisos;
    }
}
