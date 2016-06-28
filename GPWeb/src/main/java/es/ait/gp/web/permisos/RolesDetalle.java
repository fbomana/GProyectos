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
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.transaction.UserTransaction;

/**
 * Backing bean para la página de detalle de roles.
 * 
 * @author aitkiar
 */
@Named
@RequestScoped
@TransactionManagement(value=TransactionManagementType.BEAN)
public class RolesDetalle
{
    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private RolesDAO dao;
    
    @EJB
    private PermisosDAO daoPermisos;
    
    private Roles role;
    private List<Permisos> permisos;
    
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
    
    @PostConstruct
    private void init()
    {
        try
        {
            Integer roleId = null;
            RequestUtils.pintarParametrosRequest();
            try
            {
                roleId = new Integer( RequestUtils.getRequestParameter("roleId"));
            }
            catch ( Exception e )
            {
                try
                {
                    roleId = new Integer( RequestUtils.getRequestParameter("form:roleId"));
                }
                catch ( Exception ex )
                {
                }
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
        try
        {
            userTransaction.begin();
            if ( role.getRoleId() != null )
            {
                for ( Permisos permiso : permisos )
                {
                    if ( !role.getPermisosList().contains( permiso ))
                    {
                        permiso.getRolesList().remove( role );
                        daoPermisos.edit( permiso );
                    }
                }

                for ( Permisos permiso : role.getPermisosList())
                {
                    if ( !permiso.getRolesList().contains( role ))
                    {
                        permiso.getRolesList().add( role );
                        daoPermisos.edit( permiso );
                    }
                }
                dao.edit( role );

            }
            else
            {
                dao.create( role );
            }
            userTransaction.commit();
               
        }
        catch ( Exception e )
        {
            userTransaction.rollback();
            throw e;
        }
        return cancelar();
    }
    
    public String cancelar()
    {
        return "/protegido/permisos/rolesLista.xhtml?faces-redirect=true";
    }

}
