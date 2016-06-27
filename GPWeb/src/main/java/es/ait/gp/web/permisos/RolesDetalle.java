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
import java.util.List;
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

            role = dao.find( roleId );
            permisos = daoPermisos.findInRole( role, false );
        }
        catch ( Exception e )
        {
        }
    }

    public void anadir() throws Exception
    {
        String[] permisosAnadir = RequestUtils.getRequestParameterValues("form:permisosPosibles");
        if ( permisosAnadir != null )
        {
            for ( String permId : permisosAnadir )
            {
                Permisos permiso = daoPermisos.find( new Integer( permId ));
                role.anadirPermiso(permiso );
                permisos.remove( permiso );
            }
        }
    }
    
    public void quitar() throws Exception
    {
        String[] permisosQuitar = RequestUtils.getRequestParameterValues("form:permisos");
        if ( permisosQuitar != null )
        {
            for ( String permId : permisosQuitar )
            {
                Permisos permiso = daoPermisos.find( new Integer( permId ));
                role.quitarPermiso( permiso );
                permisos.add( permiso );
            }
        }
    }
    
    public String guardarCambios() throws Exception
    {
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
