/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

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
import javax.persistence.Lob;
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
@Table(name = "versiones")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Versiones.findAll", query = "SELECT v FROM Versiones v order by v.docuId.docuId, v.versFxAlta"),
    @NamedQuery(name = "Versiones.findByVersId", query = "SELECT v FROM Versiones v WHERE v.versId = :versId"),
    @NamedQuery(name = "Versiones.findByVersFxAlta", query = "SELECT v FROM Versiones v WHERE v.versFxAlta = :versFxAlta")
})
public class Versiones implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @TableGenerator( name="Versiones.generator", table="GENERATOR_TABLE", 
        pkColumnName="tabla", 
        pkColumnValue = "versiones", 
        valueColumnName = "id")
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "Versiones.generator")
    @Basic(optional = false)
    @Column(name = "vers_id")
    private Integer versId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vers_fx_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date versFxAlta;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    @ManyToOne(optional = false)
    private Documentacion docuId;
    @JoinColumn(name = "usua_id_version", referencedColumnName = "usua_id")
    @ManyToOne(optional = false)
    private Usuarios usuaIdVersion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "vers_documento")
    private byte[] versDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "vers_documento_nombre")
    private String versDocumentoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vers_documento_mime")
    private String versDocumentoMime;    

    public Versiones()
    {
    }

    public Versiones(Integer versId)
    {
        this.versId = versId;
    }

    public Versiones(Integer versId, Date versFxAlta)
    {
        this.versId = versId;
        this.versFxAlta = versFxAlta;
    }

    public Integer getVersId()
    {
        return versId;
    }

    public void setVersId(Integer versId)
    {
        this.versId = versId;
    }

    public Date getVersFxAlta()
    {
        return versFxAlta;
    }

    public void setVersFxAlta(Date versFxAlta)
    {
        this.versFxAlta = versFxAlta;
    }

    public Documentacion getDocuId()
    {
        return docuId;
    }

    public void setDocuId(Documentacion docuId)
    {
        this.docuId = docuId;
    }

    public Usuarios getUsuaIdVersion()
    {
        return usuaIdVersion;
    }

    public void setUsuaIdVersion(Usuarios usuaIdVersion)
    {
        this.usuaIdVersion = usuaIdVersion;
    }

    public byte[] getVersDocumento()
    {
        return versDocumento;
    }

    public void setVersDocumento(byte[] versDocumento)
    {
        this.versDocumento = versDocumento;
    }

    public String getVersDocumentoNombre()
    {
        return versDocumentoNombre;
    }

    public void setVersDocumentoNombre(String versDocumentoNombre)
    {
        this.versDocumentoNombre = versDocumentoNombre;
    }

    public String getVersDocumentoMime()
    {
        return versDocumentoMime;
    }

    public void setVersDocumentoMime(String versDocumentoMime)
    {
        this.versDocumentoMime = versDocumentoMime;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (versId != null ? versId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Versiones))
        {
            return false;
        }
        Versiones other = (Versiones) object;
        if ((this.versId == null && other.versId != null) || (this.versId != null && !this.versId.equals(other.versId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.documentacion.Versiones[ versId=" + versId + " ]";
    }
}
 