/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import java.io.Serializable;
import java.util.Date;

/**
 * Filtro para buscar en la tabla de proyectos.
 *
 * @author aitkiar
 */
public class ProyectosFiltro implements Serializable
{
    private Integer proyId;
    private String proyNombre;
    private String proyDescripcion;
    private Date proyFxAlta;
    private String proyEstado;
    private Date proyFxFin;
    private Proyectos proyPadre;
    private Integer usuaIdAlta;
    private Boolean primerNivel;

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
     * @return the proyNombre
     */
    public String getProyNombre()
    {
        return proyNombre;
    }

    /**
     * @param proyNombre the proyNombre to set
     */
    public void setProyNombre(String proyNombre)
    {
        this.proyNombre = proyNombre;
    }

    /**
     * @return the proyDescripcion
     */
    public String getProyDescripcion()
    {
        return proyDescripcion;
    }

    /**
     * @param proyDescripcion the proyDescripcion to set
     */
    public void setProyDescripcion(String proyDescripcion)
    {
        this.proyDescripcion = proyDescripcion;
    }

    /**
     * @return the proyFxAlta
     */
    public Date getProyFxAlta()
    {
        return proyFxAlta;
    }

    /**
     * @param proyFxAlta the proyFxAlta to set
     */
    public void setProyFxAlta(Date proyFxAlta)
    {
        this.proyFxAlta = proyFxAlta;
    }

    /**
     * @return the proyEstado
     */
    public String getProyEstado()
    {
        return proyEstado;
    }

    /**
     * @param proyEstado the proyEstado to set
     */
    public void setProyEstado(String proyEstado)
    {
        this.proyEstado = proyEstado;
    }

    /**
     * @return the proyFxFin
     */
    public Date getProyFxFin()
    {
        return proyFxFin;
    }

    /**
     * @param proyFxFin the proyFxFin to set
     */
    public void setProyFxFin(Date proyFxFin)
    {
        this.proyFxFin = proyFxFin;
    }

    /**
     * @return the proyPadre
     */
    public Proyectos getProyPadre()
    {
        return proyPadre;
    }

    /**
     * @param proyPadre the proyPadre to set
     */
    public void setProyPadre(Proyectos proyPadre)
    {
        this.proyPadre = proyPadre;
    }

    /**
     * @return the usuaIdAlta
     */
    public Integer getUsuaIdAlta()
    {
        return usuaIdAlta;
    }

    /**
     * @param usuaIdAlta the usuaIdAlta to set
     */
    public void setUsuaIdAlta(Integer usuaIdAlta)
    {
        this.usuaIdAlta = usuaIdAlta;
    }

    /**
     * @return the primerNivel
     */
    public Boolean getPrimerNivel()
    {
        return primerNivel;
    }

    /**
     * @param primerNivel the primerNivel to set
     */
    public void setPrimerNivel(Boolean primerNivel)
    {
        this.primerNivel = primerNivel;
    }
    
}
