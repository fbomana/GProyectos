/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.documentacion.tiposDocumentacion;

import es.ait.gp.core.documentacion.TiposDocumentacion;
import es.ait.gp.core.documentacion.TiposDocumentacionDAO;
import es.ait.gp.core.documentacion.Versiones;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

/**
 * Bean para la lista de tipos de documentación
 * @author aitkiar
 */
@Named
@RequestScoped
public class TiposDocumentacionListaBean
{
    private List<TiposDocumentacion> tipos;
    
    @EJB
    private TiposDocumentacionDAO dao;
    
    public TiposDocumentacionListaBean()
    {
    }
    
    /**
     * Carga la lista de tipos de documentacion de BBDD y la devuelve. La búsqueda se
     * realiza solo una vez por request, y se devuelve la misma lista en sucesivos accesos,
     * por lo que la lista se puede ver afectada si se modifica.
     * 
     * @return
     * @throws Exception 
     */
    public List<TiposDocumentacion> getTipos() throws Exception
    {
        if ( tipos == null )
        {
            tipos = dao.findAll();
        }
        return tipos;
    }
    
    public String editarTipo( Integer tipo )
    {
        return "/protegido/documentacion/tiposDocumentacion/tiposDocumentacionForm.xhtml?faces-redirect=true&tdocId=" + tipo;
    }
    
    public String nuevoTipo()
    {
        return "/protegido/documentacion/tiposDocumentacion/tiposDocumentacionForm.xhtml?faces-redirect=true";
    }
    
    public void descargar( Integer tdocId ) throws Exception
    {
        TiposDocumentacion tdoc = null;

        for ( int i = 0; i < getTipos().size(); i ++ )
        {
            if ( Objects.equals(getTipos().get( i ).getTdocId(), tdocId) )
            {
                tdoc = getTipos().get( i );
            }
        }

        if ( tdoc != null )
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            // Get HTTP response
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            // Set response headers
            response.reset();   // Reset the response in the first place
            response.setHeader("Content-Type", tdoc.getTdocPlantillaMime());
            response.setHeader("Content-Disposition",  "attachment; filename*=utf-8''" + new URI( tdoc.getTdocPlantillaNombre()).toASCIIString());

            // Open response output stream
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(tdoc.getTdocPlantilla(), 0, tdoc.getTdocPlantilla().length );

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
