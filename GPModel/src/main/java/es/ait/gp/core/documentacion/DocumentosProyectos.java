/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.proyectos.Proyectos;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "documentos_proyectos")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DocumentosProyectos.findAll", query = "SELECT d FROM DocumentosProyectos d"),
    @NamedQuery(name = "DocumentosProyectos.findByProyId", query = "SELECT d FROM DocumentosProyectos d WHERE d.documentosProyectosPK.proyId = :proyId"),
    @NamedQuery(name = "DocumentosProyectos.findByDocuId", query = "SELECT d FROM DocumentosProyectos d WHERE d.documentosProyectosPK.docuId = :docuId")
})
public class DocumentosProyectos implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentosProyectosPK documentosProyectosPK;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Documentacion documentacion;
    @JoinColumn(name = "proy_id", referencedColumnName = "proy_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyectos proyectos;

    public DocumentosProyectos()
    {
    }

    public DocumentosProyectos(DocumentosProyectosPK documentosProyectosPK)
    {
        this.documentosProyectosPK = documentosProyectosPK;
    }

    public DocumentosProyectos(int proyId, int docuId)
    {
        this.documentosProyectosPK = new DocumentosProyectosPK(proyId, docuId);
    }

    public DocumentosProyectosPK getDocumentosProyectosPK()
    {
        return documentosProyectosPK;
    }

    public void setDocumentosProyectosPK(DocumentosProyectosPK documentosProyectosPK)
    {
        this.documentosProyectosPK = documentosProyectosPK;
    }

    public Documentacion getDocumentacion()
    {
        return documentacion;
    }

    public void setDocumentacion(Documentacion documentacion)
    {
        this.documentacion = documentacion;
    }

    public Proyectos getProyectos()
    {
        return proyectos;
    }

    public void setProyectos(Proyectos proyectos)
    {
        this.proyectos = proyectos;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (documentosProyectosPK != null ? documentosProyectosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosProyectos))
        {
            return false;
        }
        DocumentosProyectos other = (DocumentosProyectos) object;
        if ((this.documentosProyectosPK == null && other.documentosProyectosPK != null) || (this.documentosProyectosPK != null && !this.documentosProyectosPK.equals(other.documentosProyectosPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.documentacion.DocumentosProyectos[ documentosProyectosPK=" + documentosProyectosPK + " ]";
    }
    
}
