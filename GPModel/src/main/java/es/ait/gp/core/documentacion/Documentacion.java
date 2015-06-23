/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
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
@Table(name = "documentacion")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Documentacion.findAll", query = "SELECT d FROM Documentacion d"),
    @NamedQuery(name = "Documentacion.findByDocuId", query = "SELECT d FROM Documentacion d WHERE d.docuId = :docuId"),
    @NamedQuery(name = "Documentacion.findByDocuNombre", query = "SELECT d FROM Documentacion d WHERE d.docuNombre = :docuNombre")
})
public class Documentacion implements Serializable
{
    private static final long serialVersionUID = 1L;
    
        
    @TableGenerator( name="Documentacion.generador",
        table="GENERATOR_TABLE",
        pkColumnName="tabla",
        valueColumnName="id",
        pkColumnValue = "documentacion")
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Documentacion.generador")
    @Basic(optional = false)
    @Column(name = "docu_id")
    private Integer docuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "docu_nombre")
    private String docuNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docuId")
    @OrderBy("versFxAlta desc")
    private List<Versiones> versiones;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "documentacion")
    private DocumentosTareas documentosTareas;
    @JoinColumn(name = "tdoc_id", referencedColumnName = "tdoc_id")
    @ManyToOne(optional = false)
    private TiposDocumentacion tdocId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "documentacion")
    private DocumentosProyectos documentosProyectos;

    public Documentacion()
    {
    }

    public Documentacion(Integer docuId)
    {
        this.docuId = docuId;
    }

    public Documentacion(Integer docuId, String docuNombre)
    {
        this.docuId = docuId;
        this.docuNombre = docuNombre;
    }

    public Integer getDocuId()
    {
        return docuId;
    }

    public void setDocuId(Integer docuId)
    {
        this.docuId = docuId;
    }

    public String getDocuNombre()
    {
        return docuNombre;
    }

    public void setDocuNombre(String docuNombre)
    {
        this.docuNombre = docuNombre;
    }

    @XmlTransient
    public List<Versiones> getVersiones()
    {
        return versiones;
    }
    
    public List<Versiones> getVersionesOrdenada()
    {
        if ( versiones != null )
        {
            // Ñapa para arreglar el error de eclipseLink con el método sort de las listas.
            List<Versiones> aux = new ArrayList<Versiones>( versiones );
            Collections.sort( aux, new VersionesFxComparator() );
            return aux;
        }
        return versiones;
    }

    public void setVersiones(List<Versiones> versiones)
    {
        this.versiones = versiones;
    }

    public DocumentosTareas getDocumentosTareas()
    {
        return documentosTareas;
    }

    public void setDocumentosTareas(DocumentosTareas documentosTareas)
    {
        this.documentosTareas = documentosTareas;
    }

    public TiposDocumentacion getTdocId()
    {
        return tdocId;
    }

    public void setTdocId(TiposDocumentacion tdocId)
    {
        this.tdocId = tdocId;
    }

    public DocumentosProyectos getDocumentosProyectos()
    {
        return documentosProyectos;
    }

    public void setDocumentosProyectos(DocumentosProyectos documentosProyectos)
    {
        this.documentosProyectos = documentosProyectos;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (docuId != null ? docuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentacion))
        {
            return false;
        }
        Documentacion other = (Documentacion) object;
        if ((this.docuId == null && other.docuId != null) || (this.docuId != null && !this.docuId.equals(other.docuId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "es.ait.gp.core.documentacion.Documentacion[ docuId=" + docuId + " ]";
    }    
}
