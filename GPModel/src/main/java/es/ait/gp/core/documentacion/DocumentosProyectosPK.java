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
public class DocumentosProyectosPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "proy_id")
    private int proyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docu_id")
    private int docuId;

    public DocumentosProyectosPK()
    {
    }

    public DocumentosProyectosPK(int proyId, int docuId)
    {
        this.proyId = proyId;
        this.docuId = docuId;
    }

    public int getProyId()
    {
        return proyId;
    }

    public void setProyId(int proyId)
    {
        this.proyId = proyId;
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
        hash += (int) proyId;
        hash += (int) docuId;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosProyectosPK))
        {
            return false;
        }
        DocumentosProyectosPK other = (DocumentosProyectosPK) object;
        if (this.proyId != other.proyId)
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
        return "es.ait.gp.core.documentacion.DocumentosProyectosPK[ proyId=" + proyId + ", docuId=" + docuId + " ]";
    }
    
}
