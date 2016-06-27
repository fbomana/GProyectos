/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "estado_proyectos")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstadoProyectos.findAll", query = "SELECT e FROM EstadoProyectos e"),
    @NamedQuery(name = "EstadoProyectos.findByEsprEstado", query = "SELECT e FROM EstadoProyectos e WHERE e.esprEstado = :esprEstado"),
    @NamedQuery(name = "EstadoProyectos.findByEsprDescripcion", query = "SELECT e FROM EstadoProyectos e WHERE e.esprDescripcion = :esprDescripcion")
})
public class EstadoProyectos implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "espr_estado")
    private String esprEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "espr_descripcion")
    private String esprDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyEstado")
    private Collection<Proyectos> proyectosCollection;

    public EstadoProyectos()
    {
    }

    public EstadoProyectos(String esprEstado)
    {
        this.esprEstado = esprEstado;
    }

    public EstadoProyectos(String esprEstado, String esprDescripcion)
    {
        this.esprEstado = esprEstado;
        this.esprDescripcion = esprDescripcion;
    }

    public String getEsprEstado()
    {
        return esprEstado;
    }

    public void setEsprEstado(String esprEstado)
    {
        this.esprEstado = esprEstado;
    }

    public String getEsprDescripcion()
    {
        return esprDescripcion;
    }

    public void setEsprDescripcion(String esprDescripcion)
    {
        this.esprDescripcion = esprDescripcion;
    }

    @XmlTransient
    public Collection<Proyectos> getProyectosCollection()
    {
        return proyectosCollection;
    }

    public void setProyectosCollection(Collection<Proyectos> proyectosCollection)
    {
        this.proyectosCollection = proyectosCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (esprEstado != null ? esprEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProyectos))
        {
            return false;
        }
        EstadoProyectos other = (EstadoProyectos) object;
        if ((this.esprEstado == null && other.esprEstado != null) || (this.esprEstado != null && !this.esprEstado.equals(other.esprEstado)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.proyectos.EstadoProyectos[ esprEstado=" + esprEstado + " ]";
    }
    
}
