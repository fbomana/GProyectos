/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import es.ait.gp.core.proyectos.Proyectos;
import es.ait.gp.core.usuarios.Usuarios;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "historico_proyectos")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "HistoricoProyectos.findAll", query = "SELECT h FROM HistoricoProyectos h"),
    @NamedQuery(name = "HistoricoProyectos.findByHiprId", query = "SELECT h FROM HistoricoProyectos h WHERE h.hiprId = :hiprId"),
    @NamedQuery(name = "HistoricoProyectos.findByHiprFxAccion", query = "SELECT h FROM HistoricoProyectos h WHERE h.hiprFxAccion = :hiprFxAccion"),
    @NamedQuery(name = "HistoricoProyectos.findByHiprDescripcion", query = "SELECT h FROM HistoricoProyectos h WHERE h.hiprDescripcion = :hiprDescripcion"),
    @NamedQuery(name = "HistoricoProyectos.findByProyPadreNew", query = "SELECT h FROM HistoricoProyectos h WHERE h.proyPadreNew = :proyPadreNew"),
    @NamedQuery(name = "HistoricoProyectos.findByProyNombreNew", query = "SELECT h FROM HistoricoProyectos h WHERE h.proyNombreNew = :proyNombreNew"),
    @NamedQuery(name = "HistoricoProyectos.findByProyDescripcionNew", query = "SELECT h FROM HistoricoProyectos h WHERE h.proyDescripcionNew = :proyDescripcionNew"),
    @NamedQuery(name = "HistoricoProyectos.findByProyEstadoNew", query = "SELECT h FROM HistoricoProyectos h WHERE h.proyEstadoNew = :proyEstadoNew"),
    @NamedQuery(name = "HistoricoProyectos.findByProyFxFinNew", query = "SELECT h FROM HistoricoProyectos h WHERE h.proyFxFinNew = :proyFxFinNew")
})
public class HistoricoProyectos implements Serializable
{
    private static final long serialVersionUID = 1L;
        
    @TableGenerator( name="HistoricoProyectos.generator",
        table="GENERATOR_TABLE",
        pkColumnName="tabla",
        valueColumnName="id",
        pkColumnValue = "historico_proyectos")
    @Id
    @Basic(optional = false)
    @Column(name = "hipr_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HistoricoProyectos.generator")
    private Integer hiprId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hipr_fx_accion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiprFxAccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "hipr_descripcion")
    private String hiprDescripcion;
    @Column(name = "proy_padre_new")
    private Integer proyPadreNew;
    @Size(max = 300)
    @Column(name = "proy_nombre_new")
    private String proyNombreNew;
    @Size(max = 4000)
    @Column(name = "proy_descripcion_new")
    private String proyDescripcionNew;
    @Size(max = 45)
    @Column(name = "proy_estado_new")
    private String proyEstadoNew;
    @Column(name = "proy_fx_fin_new")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proyFxFinNew;
    @JoinColumn(name = "proy_id", referencedColumnName = "proy_id")
    @ManyToOne(optional = false)
    private Proyectos proyId;
    @JoinColumn(name = "usua_id_accion", referencedColumnName = "usua_id")
    @ManyToOne(optional = false)
    private Usuarios usuaIdAccion;
    @JoinColumn(name = "achi_id", referencedColumnName = "achi_id")
    @ManyToOne(optional = false)
    private AccionesHistorico achiId;

    public HistoricoProyectos()
    {
    }

    public HistoricoProyectos( Proyectos proyecto, Usuarios usuario )
    {
        this.proyId = proyecto;
        this.usuaIdAccion = usuario;
        this.proyDescripcionNew = proyecto.getProyDescripcion();
        this.proyEstadoNew = proyecto.getProyEstado().getEsprEstado();
        this.proyNombreNew = proyecto.getProyNombre();
        this.proyFxFinNew = proyecto.getProyFxFin();
        this.proyPadreNew = proyecto.getProyPadre() != null ? proyecto.getProyPadre().getProyId() : null;
    }
    
    public HistoricoProyectos(Integer hiprId)
    {
        this.hiprId = hiprId;
    }

    public HistoricoProyectos(Integer hiprId, Date hiprFxAccion, String hiprDescripcion)
    {
        this.hiprId = hiprId;
        this.hiprFxAccion = hiprFxAccion;
        this.hiprDescripcion = hiprDescripcion;
    }

    public Integer getHiprId()
    {
        return hiprId;
    }

    public void setHiprId(Integer hiprId)
    {
        this.hiprId = hiprId;
    }

    public Date getHiprFxAccion()
    {
        return hiprFxAccion;
    }

    public void setHiprFxAccion(Date hiprFxAccion)
    {
        this.hiprFxAccion = hiprFxAccion;
    }

    public String getHiprDescripcion()
    {
        return hiprDescripcion;
    }

    public void setHiprDescripcion(String hiprDescripcion)
    {
        this.hiprDescripcion = hiprDescripcion;
    }

    public Integer getProyPadreNew()
    {
        return proyPadreNew;
    }

    public void setProyPadreNew(Integer proyPadreNew)
    {
        this.proyPadreNew = proyPadreNew;
    }

    public String getProyNombreNew()
    {
        return proyNombreNew;
    }

    public void setProyNombreNew(String proyNombreNew)
    {
        this.proyNombreNew = proyNombreNew;
    }

    public String getProyDescripcionNew()
    {
        return proyDescripcionNew;
    }

    public void setProyDescripcionNew(String proyDescripcionNew)
    {
        this.proyDescripcionNew = proyDescripcionNew;
    }

    public String getProyEstadoNew()
    {
        return proyEstadoNew;
    }

    public void setProyEstadoNew(String proyEstadoNew)
    {
        this.proyEstadoNew = proyEstadoNew;
    }

    public Date getProyFxFinNew()
    {
        return proyFxFinNew;
    }

    public void setProyFxFinNew(Date proyFxFinNew)
    {
        this.proyFxFinNew = proyFxFinNew;
    }

    public Proyectos getProyId()
    {
        return proyId;
    }

    public void setProyId(Proyectos proyId)
    {
        this.proyId = proyId;
    }

    public Usuarios getUsuaIdAccion()
    {
        return usuaIdAccion;
    }

    public void setUsuaIdAccion(Usuarios usuaIdAccion)
    {
        this.usuaIdAccion = usuaIdAccion;
    }

    public AccionesHistorico getAchiId()
    {
        return achiId;
    }

    public void setAchiId(AccionesHistorico achiId)
    {
        this.achiId = achiId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (hiprId != null ? hiprId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoProyectos))
        {
            return false;
        }
        HistoricoProyectos other = (HistoricoProyectos) object;
        if ((this.hiprId == null && other.hiprId != null) || (this.hiprId != null && !this.hiprId.equals(other.hiprId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.historico.HistoricoProyectos[ hiprId=" + hiprId + " ]";
    }
    
}
