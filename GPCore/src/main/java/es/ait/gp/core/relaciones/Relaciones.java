/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.relaciones;

import es.ait.gp.core.tareas.UsuariosTareas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "relaciones")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Relaciones.findAll", query = "SELECT r FROM Relaciones r"),
    @NamedQuery(name = "Relaciones.findByRelaId", query = "SELECT r FROM Relaciones r WHERE r.relaId = :relaId"),
    @NamedQuery(name = "Relaciones.findByRelaNombre", query = "SELECT r FROM Relaciones r WHERE r.relaNombre = :relaNombre"),
    @NamedQuery(name = "Relaciones.findByRelaDescripcion", query = "SELECT r FROM Relaciones r WHERE r.relaDescripcion = :relaDescripcion")
})
public class Relaciones implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rela_id")
    private Integer relaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rela_nombre")
    private String relaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rela_descripcion")
    private String relaDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relaciones")
    private List<UsuariosTareas> usuariosTareasList;

    public Relaciones()
    {
    }

    public Relaciones(Integer relaId)
    {
        this.relaId = relaId;
    }

    public Relaciones(Integer relaId, String relaNombre, String relaDescripcion)
    {
        this.relaId = relaId;
        this.relaNombre = relaNombre;
        this.relaDescripcion = relaDescripcion;
    }

    public Integer getRelaId()
    {
        return relaId;
    }

    public void setRelaId(Integer relaId)
    {
        this.relaId = relaId;
    }

    public String getRelaNombre()
    {
        return relaNombre;
    }

    public void setRelaNombre(String relaNombre)
    {
        this.relaNombre = relaNombre;
    }

    public String getRelaDescripcion()
    {
        return relaDescripcion;
    }

    public void setRelaDescripcion(String relaDescripcion)
    {
        this.relaDescripcion = relaDescripcion;
    }

    @XmlTransient
    public List<UsuariosTareas> getUsuariosTareasList()
    {
        return usuariosTareasList;
    }

    public void setUsuariosTareasList(List<UsuariosTareas> usuariosTareasList)
    {
        this.usuariosTareasList = usuariosTareasList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (relaId != null ? relaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relaciones))
        {
            return false;
        }
        Relaciones other = (Relaciones) object;
        if ((this.relaId == null && other.relaId != null) || (this.relaId != null && !this.relaId.equals(other.relaId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.Relaciones[ relaId=" + relaId + " ]";
    }
    
}
