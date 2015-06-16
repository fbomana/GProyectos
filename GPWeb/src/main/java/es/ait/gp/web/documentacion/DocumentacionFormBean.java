/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.documentacion;

import es.ait.gp.core.documentacion.Documentacion;
import es.ait.gp.core.documentacion.DocumentacionDAO;
import es.ait.gp.core.documentacion.DocumentosProyectos;
import es.ait.gp.core.documentacion.TiposDocumentacion;
import es.ait.gp.core.documentacion.TiposDocumentacionMap;
import es.ait.gp.core.documentacion.Versiones;
import es.ait.gp.core.documentacion.VersionesDAO;
import es.ait.gp.core.usuarios.Usuarios;
import es.ait.gp.web.util.RequestUtils;
import es.ait.gp.web.util.navegacion.PilaNavegacionInterface;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 * Esta clase se encarga de la lógica de negocio de la página de alta de documentación.
 * Desde ella se podrán dar de alta nuevos documentos, así como crear nuevas versiones de un
 * documento ya existente.
 * 
 * Admite como parámetros o bien el proyId o bien el tareId para indicar a que nivel se
 * encuentra una nueva documentación, pero nunca los dos a la vez. Estos parámetros solo
 * tienen interes en el caso del alta.
 * 
 * @author aitkiar
 */
@Named
@RequestScoped
public class DocumentacionFormBean
{
    
    @EJB
    private DocumentacionDAO dao;
    @EJB
    private VersionesDAO daoVersiones;
    
    private Integer proyId;
    private Integer tareId;
    private Integer docuId;
    private Documentacion documentacion;
    private Part part;
    
    public DocumentacionFormBean()
    {
    }
    
    @PostConstruct
    private void inicicializacion()
    {
        try
        {
            String aux = RequestUtils.getRequestParameter("docuId");
            if ( aux == null || "".equals( aux ) )
            {
                aux = RequestUtils.getRequestParameter("form:docuId");
            }
            if ( aux != null && !"".equals( aux ))
            {
                documentacion = dao.find( Integer.parseInt( aux ));
                docuId = documentacion.getDocuId();
            }
            else
            {
                documentacion = new Documentacion();
            }
            
            if ( RequestUtils.getRequestParameter("proyId") != null )
            {
                proyId = Integer.parseInt(RequestUtils.getRequestParameter("proyId"));
            }
            
            if ( RequestUtils.getRequestParameter("tareId") != null )
            {
                tareId = Integer.parseInt(RequestUtils.getRequestParameter("tareId"));
            }            
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * @return the proyId
     */
    public Integer getProyId()
    {
        return proyId;
    }

    /**
     * @param proyId the proyId to set
     */
    public void setProyId(Integer proyId)
    {
        this.proyId = proyId;
    }

    /**
     * @return the tareId
     */
    public Integer getTareId()
    {
        return tareId;
    }

    /**
     * @param tareId the tareId to set
     */
    public void setTareId(Integer tareId)
    {
        this.tareId = tareId;
    }

    /**
     * @return the docuId
     */
    public Integer getDocuId()
    {
        return docuId;
    }

    /**
     * @param docuId the docuId to set
     */
    public void setDocuId(Integer docuId)
    {
        this.docuId = docuId;
    }

    /**
     * @return the documentacion
     */
    public Documentacion getDocumentacion()
    {
        return documentacion;
    }

    /**
     * @param documentacion the documentacion to set
     */
    public void setDocumentacion(Documentacion documentacion)
    {
        this.documentacion = documentacion;
    }

    /**
     * @return the part
     */
    public Part getPart()
    {
        return part;
    }

    /**
     * @param part the part to set
     */
    public void setPart(Part part)
    {
        this.part = part;
    }
    
    public List<TiposDocumentacion> getTiposDocumentacion()
    {
        return TiposDocumentacionMap.getInstance().getAll();
    }

    public String cancelar()
    {
        PilaNavegacionInterface pila = ( PilaNavegacionInterface )RequestUtils.getSessionAttribute("navegacion");
        return pila.head().getUrl();
    }
    
    public String guardar() throws Exception
    {
        if ( proyId != null )
        {
            dao.guardarDocumentoProyecto( documentacion, proyId );
        }
        else if ( tareId != null )
        {
            dao.guardarDocumentoTarea( documentacion, tareId );
        }
        if ( part != null )
        {
            Versiones version = new Versiones();
            version.setDocuId( documentacion );
            version.setUsuaIdVersion( (Usuarios)RequestUtils.getSessionAttribute("usuario"));
            version.setVersFxAlta( new Date());
            version.setVersDocumentoNombre( part.getSubmittedFileName());
            version.setVersDocumentoMime( part.getContentType());
            byte[] buffer = new byte[(int)part.getSize()];
            part.getInputStream().read(buffer);
            version.setVersDocumento( buffer );
            daoVersiones.create(version);
        }
        return cancelar();
    }
}
