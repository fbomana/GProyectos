/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.documentacion.DocumentosTareas;
import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.usuarios.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tareas")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Tareas.findAll", query = "SELECT t FROM Tareas t"),
    @NamedQuery(name = "Tareas.findByTareId", query = "SELECT t FROM Tareas t WHERE t.tareId = :tareId"),
    @NamedQuery(name = "Tareas.findByTareNombre", query = "SELECT t FROM Tareas t WHERE t.tareNombre = :tareNombre"),
    @NamedQuery(name = "Tareas.findByTareDescripcion", query = "SELECT t FROM Tareas t WHERE t.tareDescripcion = :tareDescripcion")
})
public class Tareas implements Serializable
{   
    private static final long serialVersionUID = 1L;
    
    @TableGenerator( name="Tareas.generador", table="GENERATOR_TABLE",
        pkColumnName="tabla", valueColumnName="id",pkColumnValue = "tareas")
        
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Tareas.generador")
    @Basic(optional = false)
    @NotNull
    @Column(name = "tare_id")
    private Integer tareId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tare_nombre")
    private String tareNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "tare_descripcion")
    private String tareDescripcion;
    @JoinColumn(name = "usua_id_alta", referencedColumnName = "usua_id")
    @ManyToOne(optional = false)
    private Usuarios usuaIdAlta;
    @JoinColumn(name = "proy_id", referencedColumnName = "proy_id")
    @ManyToOne(optional = false)
    private Proyectos proyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tareas")
    private List<UsuariosTareas> usuariosTareas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tareas")
    private List<DocumentosTareas> documentos;
    
    public Tareas()
    {
    }

    public Tareas(Integer tareId)
    {
        this.tareId = tareId;
    }

    public Tareas(Integer tareId, String tareNombre, String tareDescripcion)
    {
        this.tareId = tareId;
        this.tareNombre = tareNombre;
        this.tareDescripcion = tareDescripcion;
    }

    public Integer getTareId()
    {
        return tareId;
    }

    public void setTareId(Integer tareId)
    {
        this.tareId = tareId;
    }

    public String getTareNombre()
    {
        return tareNombre;
    }

    public void setTareNombre(String tareNombre)
    {
        this.tareNombre = tareNombre;
    }

    public String getTareDescripcion()
    {
        return tareDescripcion;
    }

    public void setTareDescripcion(String tareDescripcion)
    {
        this.tareDescripcion = tareDescripcion;
    }

    public Usuarios getUsuaIdAlta()
    {
        return usuaIdAlta;
    }

    public void setUsuaIdAlta(Usuarios usuaIdAlta)
    {
        this.usuaIdAlta = usuaIdAlta;
    }

    public Proyectos getProyId()
    {
        return proyId;
    }

    public void setProyId(Proyectos proyId)
    {
        this.proyId = proyId;
    }

    @XmlTransient
    public List<UsuariosTareas> getUsuariosTareas()
    {
        return usuariosTareas;
    }

    public void setUsuariosTareas(List<UsuariosTareas> usuariosTareas)
    {
        this.usuariosTareas = usuariosTareas;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (tareId != null ? tareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareas))
        {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.tareId == null && other.tareId != null) || (this.tareId != null && !this.tareId.equals(other.tareId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.Tareas[ tareId=" + tareId + " ]";
    }

    @XmlTransient
    public List<DocumentosTareas> getDocumentos()
    {
        return documentos;
    }

    public void setDocumentos(List<DocumentosTareas> documentos)
    {
        this.documentos = documentos;
    }
    
}
