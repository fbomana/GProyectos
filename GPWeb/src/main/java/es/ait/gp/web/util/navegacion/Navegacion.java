/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.web.util.navegacion;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase que encapsula un par URL y texto a mostrar de la url. Se usa como clase
 * auxiliar a la clase PilaNavegacion que permite desandar la lista de sitios por
 * los que hemos navegado.
 * 
 * @author aitkiar
 */
public abstract class Navegacion
{
    private String url;
    private String texto;
    private Object objeto;
    
    public Navegacion( Object objeto )
    {
        this.objeto = objeto;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return the texto
     */
    public String getTexto()
    {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    /**
     * @return the objeto
     */
    public Object getObjeto()
    {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Object objeto)
    {
        this.objeto = objeto;
    }

    public abstract boolean mismoObjeto( HttpServletRequest request );
    
    public abstract boolean mismoObjeto( Object objeto );

}
