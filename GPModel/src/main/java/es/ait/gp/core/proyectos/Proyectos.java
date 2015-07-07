/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.documentacion.DocumentosProyectos;
import es.ait.gp.core.historico.HistoricoProyectos;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.usuarios.Usuarios;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p ORDER BY p.proyDescripcion"),
    @NamedQuery(name = "Proyectos.findByProyId", query = "SELECT p FROM Proyectos p WHERE p.proyId = :proyId"),
    @NamedQuery(name = "Proyectos.findByProyNombre", query = "SELECT p FROM Proyectos p WHERE p.proyNombre = :proyNombre"),
    @NamedQuery(name = "Proyectos.findByProyDescripcion", query = "SELECT p FROM Proyectos p WHERE p.proyDescripcion = :proyDescripcion"),
    @NamedQuery(name = "Proyectos.findByProyFxAlta", query = "SELECT p FROM Proyectos p WHERE p.proyFxAlta = :proyFxAlta"),
    @NamedQuery(name = "Proyectos.findByProyEstado", query = "SELECT p FROM Proyectos p WHERE p.proyEstado = :proyEstado"),
    @NamedQuery(name = "Proyectos.findByProyFxFin", query = "SELECT p FROM Proyectos p WHERE p.proyFxFin = :proyFxFin")
})
public class Proyectos implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @TableGenerator( name="Proyectos.generador",
        table="GENERATOR_TABLE",
        pkColumnName="tabla",
        valueColumnName="id",
        pkColumnValue = "proyectos")
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Proyectos.generador")
    @Basic(optional = false)
    @Column(name = "proy_id")
    private Integer proyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "proy_nombre")
    private String proyNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "proy_descripcion")
    private String proyDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proy_fx_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proyFxAlta;
    @Column(name = "proy_fx_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proyFxFin;
    @OneToMany(mappedBy = "proyPadre")
    private List<Proyectos> proyectosList;
    @JoinColumn(name = "proy_padre", referencedColumnName = "proy_id")
    @ManyToOne
    private Proyectos proyPadre;
    @JoinColumn(name = "usua_id_alta", referencedColumnName = "usua_id")
    @ManyToOne(optional = false)
    private Usuarios usuaIdAlta;
    @JoinColumn(name = "proy_estado", referencedColumnName = "espr_estado")
    @ManyToOne(optional = false)
    private EstadoProyectos proyEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyId")
    private List<Tareas> tareas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyectos")
    private List<DocumentosProyectos> documentos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyId")
    private List<HistoricoProyectos> historico;

    public Proyectos()
    {
    }

    public Proyectos(Integer proyId)
    {
        this.proyId = proyId;
    }

    public Proyectos(Integer proyId, String proyNombre, String proyDescripcion, Date proyFxAlta, EstadoProyectos proyEstado)
    {
        this.proyId = proyId;
        this.proyNombre = proyNombre;
        this.proyDescripcion = proyDescripcion;
        this.proyFxAlta = proyFxAlta;
        this.proyEstado = proyEstado;
    }

    public Integer getProyId()
    {
        return proyId;
    }

    public void setProyId(Integer proyId)
    {
        this.proyId = proyId;
    }

    public String getProyNombre()
    {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre)
    {
        this.proyNombre = proyNombre;
    }

    public String getProyDescripcion()
    {
        return proyDescripcion;
    }

    public void setProyDescripcion(String proyDescripcion)
    {
        this.proyDescripcion = proyDescripcion;
    }

    public Date getProyFxAlta()
    {
        return proyFxAlta;
    }

    public void setProyFxAlta(Date proyFxAlta)
    {
        this.proyFxAlta = proyFxAlta;
    }

    public Date getProyFxFin()
    {
        return proyFxFin;
    }

    public void setProyFxFin(Date proyFxFin)
    {
        this.proyFxFin = proyFxFin;
    }

    @XmlTransient
    public List<Proyectos> getProyectosList()
    {
        return proyectosList;
    }

    public void setProyectosList(List<Proyectos> proyectosList)
    {
        this.proyectosList = proyectosList;
    }

    public Proyectos getProyPadre()
    {
        return proyPadre;
    }

    public void setProyPadre(Proyectos proyPadre)
    {
        this.proyPadre = proyPadre;
    }

    public Usuarios getUsuaIdAlta()
    {
        return usuaIdAlta;
    }

    public void setUsuaIdAlta(Usuarios usuaIdAlta)
    {
        this.usuaIdAlta = usuaIdAlta;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (proyId != null ? proyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos))
        {
            return false;
        }
        Proyectos other = (Proyectos) object;
        return !((this.proyId == null && other.proyId != null) || (this.proyId != null && !this.proyId.equals(other.proyId)));
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.proyectos.Proyectos[ proyId=" + proyId + " ]";
    }

    @XmlTransient
    public List<Tareas> getTareas()
    {
        return tareas;
    }

    public void setTareas(List<Tareas> tareas)
    {
        this.tareas = tareas;
    }

    public EstadoProyectos getProyEstado()
    {
        return proyEstado;
    }

    public void setProyEstado(EstadoProyectos proyEstado)
    {
        this.proyEstado = proyEstado;
    }

    @XmlTransient
    public List<DocumentosProyectos> getDocumentos()
    {
        return documentos;
    }

    public void setDocumentos(List<DocumentosProyectos> documentos )
    {
        this.documentos = documentos;
    }

    @XmlTransient
    public List<HistoricoProyectos> getHistorico()
    {
        return historico;
    }

    public void setHistorico(List<HistoricoProyectos> historico)
    {
        this.historico = historico;
    }
    
}
