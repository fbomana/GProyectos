/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import es.ait.gp.core.tareas.Tareas;
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
@Table(name = "historico_tareas")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "HistoricoTareas.findAll", query = "SELECT h FROM HistoricoTareas h"),
    @NamedQuery(name = "HistoricoTareas.findByHitrId", query = "SELECT h FROM HistoricoTareas h WHERE h.hitrId = :hitrId"),
    @NamedQuery(name = "HistoricoTareas.findByHitrFxAccion", query = "SELECT h FROM HistoricoTareas h WHERE h.hitrFxAccion = :hitrFxAccion"),
    @NamedQuery(name = "HistoricoTareas.findByHitrDescripcion", query = "SELECT h FROM HistoricoTareas h WHERE h.hitrDescripcion = :hitrDescripcion"),
    @NamedQuery(name = "HistoricoTareas.findByTareNombreNew", query = "SELECT h FROM HistoricoTareas h WHERE h.tareNombreNew = :tareNombreNew"),
    @NamedQuery(name = "HistoricoTareas.findByTareDescripcionNew", query = "SELECT h FROM HistoricoTareas h WHERE h.tareDescripcionNew = :tareDescripcionNew")
})
public class HistoricoTareas implements Serializable
{   
    private static final long serialVersionUID = 1L;
    @TableGenerator(table = "GENERATOR_TABLE", pkColumnName = "tabla", 
            pkColumnValue = "historico_tareas", valueColumnName = "id", 
            name="HistoricoTareas.generator")
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HistoricoTareas.generator")
    @Column(name = "hitr_id")
    private Integer hitrId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hitr_fx_accion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hitrFxAccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "hitr_descripcion")
    private String hitrDescripcion;
    @Size(max = 200)
    @Column(name = "tare_nombre_new")
    private String tareNombreNew;
    @Size(max = 4000)
    @Column(name = "tare_descripcion_new")
    private String tareDescripcionNew;
    @JoinColumn(name = "achi_id", referencedColumnName = "achi_id")
    @ManyToOne(optional = false)
    private AccionesHistorico achiId;
    @JoinColumn(name = "tare_id", referencedColumnName = "tare_id")
    @ManyToOne(optional = false)
    private Tareas tareId;
    @JoinColumn(name = "usua_id_accion", referencedColumnName = "usua_id")
    @ManyToOne(optional = false)
    private Usuarios usuaIdAccion;

    public HistoricoTareas()
    {
    }

    public HistoricoTareas(Integer hitrId)
    {
        this.hitrId = hitrId;
    }

    public HistoricoTareas(Integer hitrId, Date hitrFxAccion, String hitrDescripcion)
    {
        this.hitrId = hitrId;
        this.hitrFxAccion = hitrFxAccion;
        this.hitrDescripcion = hitrDescripcion;
    }

    public HistoricoTareas( Tareas tarea, Usuarios usuario )
    {
        this.tareDescripcionNew = tarea.getTareDescripcion();
        this.tareNombreNew = tarea.getTareNombre();
        this.tareId = tarea;
        this.usuaIdAccion = usuario;
    }
    public Integer getHitrId()
    {
        return hitrId;
    }

    public void setHitrId(Integer hitrId)
    {
        this.hitrId = hitrId;
    }

    public Date getHitrFxAccion()
    {
        return hitrFxAccion;
    }

    public void setHitrFxAccion(Date hitrFxAccion)
    {
        this.hitrFxAccion = hitrFxAccion;
    }

    public String getHitrDescripcion()
    {
        return hitrDescripcion;
    }

    public void setHitrDescripcion(String hitrDescripcion)
    {
        this.hitrDescripcion = hitrDescripcion;
    }

    public String getTareNombreNew()
    {
        return tareNombreNew;
    }

    public void setTareNombreNew(String tareNombreNew)
    {
        this.tareNombreNew = tareNombreNew;
    }

    public String getTareDescripcionNew()
    {
        return tareDescripcionNew;
    }

    public void setTareDescripcionNew(String tareDescripcionNew)
    {
        this.tareDescripcionNew = tareDescripcionNew;
    }

    public AccionesHistorico getAchiId()
    {
        return achiId;
    }

    public void setAchiId(AccionesHistorico achiId)
    {
        this.achiId = achiId;
    }

    public Tareas getTareId()
    {
        return tareId;
    }

    public void setTareId(Tareas tareId)
    {
        this.tareId = tareId;
    }

    public Usuarios getUsuaIdAccion()
    {
        return usuaIdAccion;
    }

    public void setUsuaIdAccion(Usuarios usuaIdAccion)
    {
        this.usuaIdAccion = usuaIdAccion;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (hitrId != null ? hitrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoTareas))
        {
            return false;
        }
        HistoricoTareas other = (HistoricoTareas) object;
        if ((this.hitrId == null && other.hitrId != null) || (this.hitrId != null && !this.hitrId.equals(other.hitrId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.historico.HistoricoTareas[ hitrId=" + hitrId + " ]";
    }
    
}
