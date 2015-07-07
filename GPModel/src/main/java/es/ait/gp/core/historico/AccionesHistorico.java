/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "acciones_historico")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "AccionesHistorico.findAll", query = "SELECT a FROM AccionesHistorico a"),
    @NamedQuery(name = "AccionesHistorico.findByAchiId", query = "SELECT a FROM AccionesHistorico a WHERE a.achiId = :achiId"),
    @NamedQuery(name = "AccionesHistorico.findByAchiDescripcion", query = "SELECT a FROM AccionesHistorico a WHERE a.achiDescripcion = :achiDescripcion")
})
public class AccionesHistorico implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "achi_id")
    private Integer achiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "achi_descripcion")
    private String achiDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achiId")
    private List<HistoricoProyectos> historicoProyectosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achiId")
    private List<HistoricoTareas> historicoTareasCollection;

    public AccionesHistorico()
    {
    }

    public AccionesHistorico(Integer achiId)
    {
        this.achiId = achiId;
    }

    public AccionesHistorico(Integer achiId, String achiDescripcion)
    {
        this.achiId = achiId;
        this.achiDescripcion = achiDescripcion;
    }

    public Integer getAchiId()
    {
        return achiId;
    }

    public void setAchiId(Integer achiId)
    {
        this.achiId = achiId;
    }

    public String getAchiDescripcion()
    {
        return achiDescripcion;
    }

    public void setAchiDescripcion(String achiDescripcion)
    {
        this.achiDescripcion = achiDescripcion;
    }

    @XmlTransient
    public List<HistoricoProyectos> getHistoricoProyectosList()
    {
        return historicoProyectosList;
    }

    public void setHistoricoProyectosList(List<HistoricoProyectos> historicoProyectosList)
    {
        this.historicoProyectosList = historicoProyectosList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (achiId != null ? achiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionesHistorico))
        {
            return false;
        }
        AccionesHistorico other = (AccionesHistorico) object;
        if ((this.achiId == null && other.achiId != null) || (this.achiId != null && !this.achiId.equals(other.achiId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.historico.AccionesHistorico[ achiId=" + achiId + " ]";
    }

    @XmlTransient
    public List<HistoricoTareas> getHistoricoTareasCollection()
    {
        return historicoTareasCollection;
    }

    public void setHistoricoTareasCollection(List<HistoricoTareas> historicoTareasCollection)
    {
        this.historicoTareasCollection = historicoTareasCollection;
    }
    
}
