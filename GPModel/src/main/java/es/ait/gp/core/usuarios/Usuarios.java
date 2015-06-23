/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.usuarios;

import es.ait.gp.core.documentacion.Versiones;
import es.ait.gp.core.historico.HistoricoProyectos;
import es.ait.gp.core.permisos.Permisos;
import es.ait.gp.core.permisos.Roles;
import es.ait.gp.core.tareas.Tareas;
import es.ait.gp.core.tareas.UsuariosTareas;
import es.ait.gp.core.proyectos.Proyectos;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "usuarios", catalog = "proyectos", schema = "")
@NamedQueries(
{
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByUsuaId", query = "SELECT u FROM Usuarios u WHERE u.usuaId = :usuaId"),
    @NamedQuery(name = "Usuarios.findByUsuaLogin", query = "SELECT u FROM Usuarios u WHERE u.usuaLogin = :usuaLogin"),
    @NamedQuery(name = "Usuarios.findByUsuaPassword", query = "SELECT u FROM Usuarios u WHERE u.usuaPassword = :usuaPassword"),
    @NamedQuery(name = "Usuarios.findByUsuaActivo", query = "SELECT u FROM Usuarios u WHERE u.usuaActivo = :usuaActivo"),
    @NamedQuery(name = "Usuarios.findByUsuaFxAlta", query = "SELECT u FROM Usuarios u WHERE u.usuaFxAlta = :usuaFxAlta"),
    @NamedQuery(name = "Usuarios.findByUsuaNombre", query = "SELECT u FROM Usuarios u WHERE u.usuaNombre = :usuaNombre"),
    @NamedQuery(name = "Usuarios.findByUsuaEmail", query = "SELECT u FROM Usuarios u WHERE u.usuaEmail = :usuaEmail")
})
public class Usuarios implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaIdAccion")
    private List<HistoricoProyectos> historicoProyectosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaIdVersion")
    private List<Versiones> versionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaIdAlta")
    private List<Tareas> tareasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<UsuariosTareas> usuariosTareasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaIdAlta")
    private List<Proyectos> proyectosList;
    @ManyToMany(mappedBy = "usuariosList")
    private List<Roles> rolesList;

    private static final long serialVersionUID = 1L;
    
    @TableGenerator( name="Usuarios.generador",
        table="GENERATOR_TABLE",
        pkColumnName="tabla",
        valueColumnName="id",
        pkColumnValue = "usuarios")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Usuarios.generador")
    @Basic(optional = false)
    @NotNull
    @Column(name = "usua_id")
    private Integer usuaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usua_login")
    private String usuaLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "usua_password")
    private String usuaPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usua_activo")
    private String usuaActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usua_fx_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuaFxAlta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "usua_nombre")
    private String usuaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "usua_email")
    private String usuaEmail;
    
    @Transient
    private Map<String, String> permisosEfectivos;

    public Usuarios()
    {
    }

    public Usuarios(Integer usuaId)
    {
        this.usuaId = usuaId;
    }

    public Usuarios(Integer usuaId, String usuaLogin, String usuaPassword, String usuaActivo, Date usuaFxAlta, String usuaNombre, String usuaEmail)
    {
        this.usuaId = usuaId;
        this.usuaLogin = usuaLogin;
        this.usuaPassword = usuaPassword;
        this.usuaActivo = usuaActivo;
        this.usuaFxAlta = usuaFxAlta;
        this.usuaNombre = usuaNombre;
        this.usuaEmail = usuaEmail;
    }

    public Integer getUsuaId()
    {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId)
    {
        this.usuaId = usuaId;
    }

    public String getUsuaLogin()
    {
        return usuaLogin;
    }

    public void setUsuaLogin(String usuaLogin)
    {
        this.usuaLogin = usuaLogin;
    }

    public String getUsuaPassword()
    {
        return usuaPassword;
    }

    public void setUsuaPassword(String usuaPassword)
    {
        this.usuaPassword = usuaPassword;
    }

    public Boolean getUsuaActivo()
    {
        return "S".equals( usuaActivo );
    }

    public void setUsuaActivo(Boolean usuaActivo)
    {
        this.usuaActivo = usuaActivo ? "S" : "N" ;
    }

    public Date getUsuaFxAlta()
    {
        return usuaFxAlta;
    }

    public void setUsuaFxAlta(Date usuaFxAlta)
    {
        this.usuaFxAlta = usuaFxAlta;
    }

    public String getUsuaNombre()
    {
        return usuaNombre;
    }

    public void setUsuaNombre(String usuaNombre)
    {
        this.usuaNombre = usuaNombre;
    }

    public String getUsuaEmail()
    {
        return usuaEmail;
    }

    public void setUsuaEmail(String usuaEmail)
    {
        this.usuaEmail = usuaEmail;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (usuaId != null ? usuaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios))
        {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuaId == null && other.usuaId != null) || (this.usuaId != null && !this.usuaId.equals(other.usuaId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.usuarios.Usuarios[ usuaId=" + usuaId + " ]";
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

    @XmlTransient
    public List<Tareas> getTareasList()
    {
        return tareasList;
    }

    public void setTareasList(List<Tareas> tareasList)
    {
        this.tareasList = tareasList;
    }

    @XmlTransient
    public List<UsuariosTareas> getUsuariosTareasList()
    {
        return usuariosTareasList;
    }

    public void setUsuariosTareasList(List<UsuariosTareas> usuariosTareasList)
    {
        this.usuariosTareasList = usuariosTareasList;
    }

    @XmlTransient
    public List<Versiones> getVersionesList()
    {
        return versionesList;
    }

    public void setVersionesList(List<Versiones> versionesList)
    {
        this.versionesList = versionesList;
    }

    @XmlTransient
    public List<Roles> getRolesList()
    {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList)
    {
        this.rolesList = rolesList;
    }

    /**
     * Comprueba si un usuario tiene asignado un permiso en función de sus roles.
     * @return 
     */
    public boolean tienePermiso( String nombrePermiso )
    {
        if ( permisosEfectivos == null )
        {
            permisosEfectivos = new HashMap<String,String>();
            for ( Roles role : rolesList )
            {
                for ( Permisos permiso : role.getPermisosList())
                {
                    if (!permisosEfectivos.containsKey( permiso.getPermNombre()))
                    {
                        permisosEfectivos.put( permiso.getPermNombre(), permiso.getPermNombre() );
                    }
                }
            }
        }
        return permisosEfectivos.containsKey( nombrePermiso );
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
}
