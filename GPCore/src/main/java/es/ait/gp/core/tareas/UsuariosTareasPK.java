/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

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
public class UsuariosTareasPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "usua_id")
    private int usuaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tare_id")
    private int tareId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rela_id")
    private int relaId;

    public UsuariosTareasPK()
    {
    }

    public UsuariosTareasPK(int usuaId, int tareId, int relaId)
    {
        this.usuaId = usuaId;
        this.tareId = tareId;
        this.relaId = relaId;
    }

    public int getUsuaId()
    {
        return usuaId;
    }

    public void setUsuaId(int usuaId)
    {
        this.usuaId = usuaId;
    }

    public int getTareId()
    {
        return tareId;
    }

    public void setTareId(int tareId)
    {
        this.tareId = tareId;
    }

    public int getRelaId()
    {
        return relaId;
    }

    public void setRelaId(int relaId)
    {
        this.relaId = relaId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) usuaId;
        hash += (int) tareId;
        hash += (int) relaId;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosTareasPK))
        {
            return false;
        }
        UsuariosTareasPK other = (UsuariosTareasPK) object;
        if (this.usuaId != other.usuaId)
        {
            return false;
        }
        if (this.tareId != other.tareId)
        {
            return false;
        }
        if (this.relaId != other.relaId)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.UsuariosTareasPK[ usuaId=" + usuaId + ", tareId=" + tareId + ", relaId=" + relaId + " ]";
    }
    
}
