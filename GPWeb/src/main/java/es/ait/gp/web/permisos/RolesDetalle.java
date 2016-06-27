/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.permisos;

import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.permisos.RolesDAO;
import es.ait.gp.web.util.RequestUtils;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    
    private Roles role;
    
    @PostConstruct
    private void init()
    {
        try
        {
            Integer roleId;
            try
            {
                roleId = new Integer( RequestUtils.getRequestParameter("roleId"));
            }
            catch ( Exception e )
            {
                roleId = new Integer( RequestUtils.getRequestParameter("form:roleId"));
            }

            role = dao.find( roleId );
        }
        catch ( Exception e )
        {
        }
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
}
