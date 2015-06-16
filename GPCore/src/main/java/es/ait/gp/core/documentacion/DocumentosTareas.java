/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import es.ait.gp.core.tareas.Tareas;
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
@Table(name = "documentos_tareas")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DocumentosTareas.findAll", query = "SELECT d FROM DocumentosTareas d"),
    @NamedQuery(name = "DocumentosTareas.findByTareId", query = "SELECT d FROM DocumentosTareas d WHERE d.documentosTareasPK.tareId = :tareId"),
    @NamedQuery(name = "DocumentosTareas.findByDocuId", query = "SELECT d FROM DocumentosTareas d WHERE d.documentosTareasPK.docuId = :docuId")
})
public class DocumentosTareas implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentosTareasPK documentosTareasPK;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Documentacion documentacion;
    @JoinColumn(name = "tare_id", referencedColumnName = "tare_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tareas tareas;

    public DocumentosTareas()
    {
    }

    public DocumentosTareas(DocumentosTareasPK documentosTareasPK)
    {
        this.documentosTareasPK = documentosTareasPK;
    }

    public DocumentosTareas(int tareId, int docuId)
    {
        this.documentosTareasPK = new DocumentosTareasPK(tareId, docuId);
    }

    public DocumentosTareasPK getDocumentosTareasPK()
    {
        return documentosTareasPK;
    }

    public void setDocumentosTareasPK(DocumentosTareasPK documentosTareasPK)
    {
        this.documentosTareasPK = documentosTareasPK;
    }

    public Documentacion getDocumentacion()
    {
        return documentacion;
    }

    public void setDocumentacion(Documentacion documentacion)
    {
        this.documentacion = documentacion;
    }

    public Tareas getTareas()
    {
        return tareas;
    }

    public void setTareas(Tareas tareas)
    {
        this.tareas = tareas;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (documentosTareasPK != null ? documentosTareasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosTareas))
        {
            return false;
        }
        DocumentosTareas other = (DocumentosTareas) object;
        if ((this.documentosTareasPK == null && other.documentosTareasPK != null) || (this.documentosTareasPK != null && !this.documentosTareasPK.equals(other.documentosTareasPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.documentacion.DocumentosTareas[ documentosTareasPK=" + documentosTareasPK + " ]";
    }
    
}
