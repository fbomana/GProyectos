/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.tareas;

import es.ait.gp.core.relaciones.Relaciones;
import es.ait.gp.core.usuarios.Usuarios;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "usuarios_tareas")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UsuariosTareas.findAll", query = "SELECT u FROM UsuariosTareas u"),
    @NamedQuery(name = "UsuariosTareas.findByUsuaId", query = "SELECT u FROM UsuariosTareas u WHERE u.usuariosTareasPK.usuaId = :usuaId"),
    @NamedQuery(name = "UsuariosTareas.findByTareId", query = "SELECT u FROM UsuariosTareas u WHERE u.usuariosTareasPK.tareId = :tareId"),
    @NamedQuery(name = "UsuariosTareas.findByRelaId", query = "SELECT u FROM UsuariosTareas u WHERE u.usuariosTareasPK.relaId = :relaId")
})
public class UsuariosTareas implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariosTareasPK usuariosTareasPK;
    @JoinColumn(name = "usua_id", referencedColumnName = "usua_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;
    @JoinColumn(name = "tare_id", referencedColumnName = "tare_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tareas tareas;
    @JoinColumn(name = "rela_id", referencedColumnName = "rela_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Relaciones relaciones;

    public UsuariosTareas()
    {
    }

    public UsuariosTareas(UsuariosTareasPK usuariosTareasPK)
    {
        this.usuariosTareasPK = usuariosTareasPK;
    }

    public UsuariosTareas(int usuaId, int tareId, int relaId)
    {
        this.usuariosTareasPK = new UsuariosTareasPK(usuaId, tareId, relaId);
    }

    public UsuariosTareasPK getUsuariosTareasPK()
    {
        return usuariosTareasPK;
    }

    public void setUsuariosTareasPK(UsuariosTareasPK usuariosTareasPK)
    {
        this.usuariosTareasPK = usuariosTareasPK;
    }

    public Usuarios getUsuarios()
    {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios)
    {
        this.usuarios = usuarios;
    }

    public Tareas getTareas()
    {
        return tareas;
    }

    public void setTareas(Tareas tareas)
    {
        this.tareas = tareas;
    }

    public Relaciones getRelaciones()
    {
        return relaciones;
    }

    public void setRelaciones(Relaciones relaciones)
    {
        this.relaciones = relaciones;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (usuariosTareasPK != null ? usuariosTareasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosTareas))
        {
            return false;
        }
        UsuariosTareas other = (UsuariosTareas) object;
        if ((this.usuariosTareasPK == null && other.usuariosTareasPK != null) || (this.usuariosTareasPK != null && !this.usuariosTareasPK.equals(other.usuariosTareasPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.UsuariosTareas[ usuariosTareasPK=" + usuariosTareasPK + " ]";
    }
    
}
