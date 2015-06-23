/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aitkiar
 */
@Entity
@Table(name = "tipos_documentacion")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TiposDocumentacion.findAll", query = "SELECT t FROM TiposDocumentacion t order by t.tdocNombre"),
    @NamedQuery(name = "TiposDocumentacion.findByTdocId", query = "SELECT t FROM TiposDocumentacion t WHERE t.tdocId = :tdocId"),
    @NamedQuery(name = "TiposDocumentacion.findByTdocNombre", query = "SELECT t FROM TiposDocumentacion t WHERE t.tdocNombre = :tdocNombre"),
    @NamedQuery(name = "TiposDocumentacion.findByTdocDescripcion", query = "SELECT t FROM TiposDocumentacion t WHERE t.tdocDescripcion = :tdocDescripcion")
})
public class TiposDocumentacion implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @TableGenerator( name="TiposDocumentacion.generador",
        table="GENERATOR_TABLE",
        pkColumnName="tabla",
        valueColumnName="id",
        pkColumnValue = "tipos_documentacion")
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TiposDocumentacion.generador")
    @Basic(optional = false)
    @Column(name = "tdoc_id")
    private Integer tdocId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tdoc_nombre")
    private String tdocNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "tdoc_descripcion")
    private String tdocDescripcion;
    @Lob
    @Column(name = "tdoc_plantilla")
    private byte[] tdocPlantilla;
    @Size(max = 400)
    @Column(name = "tdoc_plantilla_nombre")
    private String tdocPlantillaNombre;
    @Size(max = 45)
    @Column(name = "tdoc_plantilla_mime")
    private String tdocPlantillaMime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tdocId")
    private List<Documentacion> documentacionList;
    

    public TiposDocumentacion()
    {
    }

    public TiposDocumentacion(Integer tdocId)
    {
        this.tdocId = tdocId;
    }

    public TiposDocumentacion(Integer tdocId, String tdocNombre, String tdocDescripcion)
    {
        this.tdocId = tdocId;
        this.tdocNombre = tdocNombre;
        this.tdocDescripcion = tdocDescripcion;
    }

    public Integer getTdocId()
    {
        return tdocId;
    }

    public void setTdocId(Integer tdocId)
    {
        this.tdocId = tdocId;
    }

    public String getTdocNombre()
    {
        return tdocNombre;
    }

    public void setTdocNombre(String tdocNombre)
    {
        this.tdocNombre = tdocNombre;
    }

    public String getTdocDescripcion()
    {
        return tdocDescripcion;
    }

    public void setTdocDescripcion(String tdocDescripcion)
    {
        this.tdocDescripcion = tdocDescripcion;
    }

    public byte[] getTdocPlantilla()
    {
        return tdocPlantilla;
    }

    public void setTdocPlantilla(byte[] tdocPlantilla)
    {
        this.tdocPlantilla = tdocPlantilla;
    }

    public String getTdocPlantillaNombre()
    {
        return tdocPlantillaNombre;
    }

    public void setTdocPlantillaNombre(String tdocPlantillaNombre)
    {
        this.tdocPlantillaNombre = tdocPlantillaNombre;
        this.tdocPlantillaNombre = this.tdocPlantillaNombre.replaceAll(" ", "_");
    }

    public String getTdocPlantillaMime()
    {
        return tdocPlantillaMime;
    }

    public void setTdocPlantillaMime(String tdocPlantillaMime)
    {
        this.tdocPlantillaMime = tdocPlantillaMime;
    }

    @XmlTransient
    public List<Documentacion> getDocumentacionList()
    {
        return documentacionList;
    }

    public void setDocumentacionList(List<Documentacion> documentacionList)
    {
        this.documentacionList = documentacionList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (tdocId != null ? tdocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposDocumentacion))
        {
            return false;
        }
        TiposDocumentacion other = (TiposDocumentacion) object;
        if ((this.tdocId == null && other.tdocId != null) || (this.tdocId != null && !this.tdocId.equals(other.tdocId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.documentacion.TiposDocumentacion[ tdocId=" + tdocId + " ]";
    }
}
