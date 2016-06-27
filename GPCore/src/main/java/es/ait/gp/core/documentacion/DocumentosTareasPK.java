/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aitkiar
 */
@Embeddable
public class DocumentosTareasPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "tare_id")
    private int tareId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docu_id")
    private int docuId;

    public DocumentosTareasPK()
    {
    }

    public DocumentosTareasPK(int tareId, int docuId)
    {
        this.tareId = tareId;
        this.docuId = docuId;
    }

    public int getTareId()
    {
        return tareId;
    }

    public void setTareId(int tareId)
    {
        this.tareId = tareId;
    }

    public int getDocuId()
    {
        return docuId;
    }

    public void setDocuId(int docuId)
    {
        this.docuId = docuId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) tareId;
        hash += (int) docuId;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosTareasPK))
        {
            return false;
        }
        DocumentosTareasPK other = (DocumentosTareasPK) object;
        if (this.tareId != other.tareId)
        {
            return false;
        }
        if (this.docuId != other.docuId)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.documentacion.DocumentosTareasPK[ tareId=" + tareId + ", docuId=" + docuId + " ]";
    }
    
}
