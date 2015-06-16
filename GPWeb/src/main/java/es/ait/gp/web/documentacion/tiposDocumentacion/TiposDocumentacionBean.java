/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.documentacion.tiposDocumentacion;

import es.ait.gp.core.documentacion.TiposDocumentacion;
import es.ait.gp.core.documentacion.TiposDocumentacionDAO;
import es.ait.gp.core.documentacion.TiposDocumentacionMap;
import es.ait.gp.web.util.RequestUtils;
import java.io.OutputStream;
import java.net.URI;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author aitkiar
 */
@Named
@RequestScoped
public class TiposDocumentacionBean
{

    private TiposDocumentacion tipo;
    private Part part;

    @EJB
    TiposDocumentacionDAO dao;

    public TiposDocumentacionBean()
    {
    }

    @PostConstruct
    private void init()
    {
        try
        {
            String aux = RequestUtils.getRequestParameter("tdocId");
            if (aux == null || "".equals(aux.trim()))
            {
                aux = RequestUtils.getRequestParameter("form:tdocId");
            }
            if (aux != null && !"".equals(aux.trim()))
            {
                tipo = dao.find(new Integer(aux));
            } else
            {
                tipo = new TiposDocumentacion();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return the tipo
     */
    public TiposDocumentacion getTipo()
    {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TiposDocumentacion tipo)
    {
        this.tipo = tipo;
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

    public String cancelar() throws Exception
    {
        return "/protegido/documentacion/tiposDocumentacion/tiposDocumentacionLista?faces-redirect=true";
    }

    public String guardar() throws Exception
    {
        if (part != null)
        {
            tipo.setTdocPlantillaNombre(part.getSubmittedFileName());
            tipo.setTdocPlantillaMime(part.getContentType());
            byte buffer[] = new byte[(int) part.getSize()];
            part.getInputStream().read(buffer);
            tipo.setTdocPlantilla(buffer);
        }
        if (tipo.getTdocId() != null)
        {
            dao.edit(tipo);
        } else
        {
            dao.create(tipo);
        }

        TiposDocumentacionMap.reset( dao.findAll() );
        
        return "/protegido/documentacion/tiposDocumentacion/tiposDocumentacionLista?faces-redirect=true";
    }

    public void descargarPlantilla() throws Exception
    {
        if (tipo.getTdocPlantilla() != null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            // Get HTTP response
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            // Set response headers
            response.reset();   // Reset the response in the first place
            response.setHeader("Content-Type", tipo.getTdocPlantillaMime());
            response.setHeader("Content-Disposition",  "attachment; filename*=utf-8''" + new URI( tipo.getTdocPlantillaNombre()).toASCIIString());

            // Open response output stream
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(tipo.getTdocPlantilla(), 0, tipo.getTdocPlantilla().length );

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
