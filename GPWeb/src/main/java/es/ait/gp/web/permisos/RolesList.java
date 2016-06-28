/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.permisos;

import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author aitkiar
 */
@Named
@RequestScoped
public class RolesList
{
    private List<Roles> roles;
    
    @EJB
    private RolesDAO rolesDao;
    
    @PostConstruct
    private void constructor()
    {
        try
        {
            roles = rolesDao.findAll();
        }
        catch ( Exception e )
        {
        }
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
    
    public String editarRole( Integer roleId )
    {
        return "/protegido/permisos/rolesDetalle.xhtml?faces-redirect=true&roleId=" + roleId;   
    }
    
    public String nuevoRole()
    {
        return "/protegido/permisos/rolesDetalle.xhtml?faces-redirect=true";   
    }
}