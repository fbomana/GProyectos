/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.proyectos;

import es.ait.gp.core.historico.ConstantesAccionesHistorico;
import es.ait.gp.core.historico.HistoricoProyectos;
import es.ait.gp.core.historico.HistoricoProyectosDAO;
import es.ait.gp.core.usuarios.Usuarios;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aitkiar
 */
@Stateless
public class ProyectosGestor implements ProyectosGestorRemote
{
    @EJB
    private ProyectosDAO dao;
    
    @EJB
    private HistoricoProyectosDAO daoHistorico;
    
            
    @Override
    public void nuevoProyecto(Proyectos proyecto, Usuarios usuario) throws Exception
    {
        HistoricoProyectos historico = new HistoricoProyectos();
        historico.setAchiId( ConstantesAccionesHistorico.PROYECTOS_NUEVO);
        historico.setHiprDescripcion("Nuevo Proyecto: " + proyecto.getProyNombre());
        historico.setHiprFxAccion( proyecto.getProyFxAlta());
        historico.setProyDescripcionNew( proyecto.getProyDescripcion());
        historico.setProyEstadoNew( proyecto.getProyEstado().getEsprEstado());
        historico.setProyFxFinNew( proyecto.getProyFxFin());
        historico.setProyNombreNew( proyecto.getProyNombre());
        historico.setProyPadreNew( proyecto.getProyPadre() != null ? proyecto.getProyPadre().getProyId() : null );
        historico.setUsuaIdAccion( usuario );
        
        dao.create( proyecto );
        historico.setProyId( proyecto );
        daoHistorico.create( historico );
    }

    @Override
    public void modificarProyecto(Proyectos proyecto, Usuarios usuario) throws Exception
    {
        HistoricoProyectos historico = new HistoricoProyectos();
        historico.setAchiId( ConstantesAccionesHistorico.PROYECTOS_EDITAR);
        historico.setHiprDescripcion("Proyecto Modificado");
        historico.setHiprFxAccion( new Date());
        historico.setProyDescripcionNew( proyecto.getProyDescripcion());
        historico.setProyEstadoNew( proyecto.getProyEstado().getEsprEstado());
        historico.setProyFxFinNew( proyecto.getProyFxFin());
        historico.setProyNombreNew( proyecto.getProyNombre());
        historico.setProyPadreNew( proyecto.getProyPadre() != null ? proyecto.getProyPadre().getProyId() : null );
        historico.setUsuaIdAccion( usuario );
        
        dao.edit( proyecto );
        historico.setProyId(proyecto);
        daoHistorico.create( historico );        
    }
    
}
