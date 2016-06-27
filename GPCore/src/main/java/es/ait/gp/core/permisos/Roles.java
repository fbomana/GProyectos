/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

import es.ait.gp.core.usuarios.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByRoleId", query = "SELECT r FROM Roles r WHERE r.roleId = :roleId"),
    @NamedQuery(name = "Roles.findByRoleDescripcion", query = "SELECT r FROM Roles r WHERE r.roleDescripcion = :roleDescripcion")
})
public class Roles implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @TableGenerator(name="Roles.generator", 
        table = "GENERATOR_TABLE", 
        pkColumnName = "tabla", 
        pkColumnValue = "roles", 
        valueColumnName = "id")
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Roles.generator")
    @NotNull
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "role_descripcion")
    private String roleDescripcion;
    @ManyToMany(mappedBy = "rolesList")
    private List<Permisos> permisosList;
    @JoinTable(name = "roles_usuario", joinColumns =
    {
        @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "usua_id", referencedColumnName = "usua_id")
    })
    @ManyToMany
    private List<Usuarios> usuariosList;

    public Roles()
    {
    }

    public Roles(Integer roleId)
    {
        this.roleId = roleId;
    }

    public Roles(Integer roleId, String roleDescripcion)
    {
        this.roleId = roleId;
        this.roleDescripcion = roleDescripcion;
    }

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleDescripcion()
    {
        return roleDescripcion;
    }

    public void setRoleDescripcion(String roleDescripcion)
    {
        this.roleDescripcion = roleDescripcion;
    }

    @XmlTransient
    public List<Permisos> getPermisosList()
    {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList)
    {
        this.permisosList = permisosList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList()
    {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList)
    {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Roles))
        {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.permisos.Roles[ roleId=" + roleId + " ]";
    }
    
    public void quitarPermiso( Permisos permiso )
    {
        if ( permisosList.contains( permiso ) )
        {
            permiso.getRolesList().remove( this );
            permisosList.remove( permiso );
        }
    }
    
    public void anadirPermiso( Permisos permiso )
    {
        if ( !permisosList.contains( permiso ))
        {
            permisosList.add( permiso );
        }
        if ( !permiso.getRolesList().contains( this ))
        {
            permiso.getRolesList().add( this );
        }
    }
}
