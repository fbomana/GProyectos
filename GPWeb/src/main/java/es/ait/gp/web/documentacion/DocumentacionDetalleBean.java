/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.documentacion;

import es.ait.gp.core.documentacion.Documentacion;
import es.ait.gp.core.documentacion.DocumentacionDAO;
import es.ait.gp.core.documentacion.Versiones;
import es.ait.gp.web.util.RequestUtils;
import es.ait.gp.web.util.navegacion.PilaNavegacionInterface;
import java.io.OutputStream;
import java.net.URI;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aitkiar
 */
@Named
@RequestScoped
public class DocumentacionDetalleBean
{
    private Documentacion documentacion;
    
    @EJB
    private DocumentacionDAO dao;
    
    public DocumentacionDetalleBean()
    {
    }
    
    @PostConstruct
    private void init()
    {
        String aux = RequestUtils.getRequestParameter("docuId");
        if ( aux == null || "".equals ( aux.trim()))
        {
            aux = RequestUtils.getRequestParameter("form:docuId");
        }
        try
        {
            documentacion = dao.find( new Integer( aux ));
        }
        catch ( Exception e )
        {
            documentacion = new Documentacion();
        }
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
    
    public String editar( )
    {
        return "documentacionDetalleForm.xhtml?faces-redirect=true&docuId=" + documentacion.getDocuId();
    }

    public String cancelar()
    {
        PilaNavegacionInterface pila = ( PilaNavegacionInterface )RequestUtils.getSessionAttribute("navegacion");
        String url = pila.anterior().getUrl();
        return url;
    }
    
    public void descargarVersion( Integer versId ) throws Exception
    {
        Versiones version = null;

        for ( int i = 0; i < documentacion.getVersiones().size(); i ++ )
        {
            if ( Objects.equals(documentacion.getVersiones().get( i ).getVersId(), versId) )
            {
                version = documentacion.getVersiones().get( i );
            }
        }

        if ( version != null )
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            // Get HTTP response
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            // Set response headers
            response.reset();   // Reset the response in the first place
            response.setHeader("Content-Type", version.getVersDocumentoMime());
            response.setHeader("Content-Disposition",  "attachment; filename*=utf-8''" + new URI( version.getVersDocumentoNombre().replaceAll(" ", "_")).toASCIIString());

            // Open response output stream
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(version.getVersDocumento(), 0, version.getVersDocumento().length );

            responseOutputStream.flush();

            responseOutputStream.close();

        // JSF doc:
            // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated
            // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
            // as soon as the current phase is completed.
            facesContext.responseComplete();
        }
    }
}
