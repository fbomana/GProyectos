/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.permisos;

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
@Table(name = "permisos")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p ORDER BY p.permNombre"),
    @NamedQuery(name = "Permisos.findByPermId", query = "SELECT p FROM Permisos p WHERE p.permId = :permId"),
    @NamedQuery(name = "Permisos.findByPermDescripcion", query = "SELECT p FROM Permisos p WHERE p.permDescripcion = :permDescripcion"),
    @NamedQuery(name = "Permisos.findByPermNombre", query = "SELECT p FROM Permisos p WHERE p.permNombre = :permNombre")
})
public class Permisos implements Serializable
{
    private static final long serialVersionUID = 1L;
    
        @TableGenerator( name="Permisos.generador",
        table="GENERATOR_TABLE",
        pkColumnName="tabla",
        valueColumnName="id",
        pkColumnValue = "permisos")
        
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Permisos.generador")
    @NotNull
    @Column(name = "perm_id")
    private Integer permId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "perm_descripcion")
    private String permDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "perm_nombre")
    private String permNombre;
    @JoinTable(name = "permisos_roles", joinColumns =
    {
        @JoinColumn(name = "perm_id", referencedColumnName = "perm_id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    })
    @ManyToMany
    private List<Roles> rolesList;

    public Permisos()
    {
    }

    public Permisos(Integer permId)
    {
        this.permId = permId;
    }

    public Permisos(Integer permId, String permDescripcion, String permNombre)
    {
        this.permId = permId;
        this.permDescripcion = permDescripcion;
        this.permNombre = permNombre;
    }

    public Integer getPermId()
    {
        return permId;
    }

    public void setPermId(Integer permId)
    {
        this.permId = permId;
    }

    public String getPermDescripcion()
    {
        return permDescripcion;
    }

    public void setPermDescripcion(String permDescripcion)
    {
        this.permDescripcion = permDescripcion;
    }

    public String getPermNombre()
    {
        return permNombre;
    }

    public void setPermNombre(String permNombre)
    {
        this.permNombre = permNombre;
    }

    @XmlTransient
    public List<Roles> getRolesList()
    {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList)
    {
        this.rolesList = rolesList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (permId != null ? permId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos))
        {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.permId == null && other.permId != null) || (this.permId != null && !this.permId.equals(other.permId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.permisos.Permisos[ permId=" + permId + " ]";
    }
    
}
