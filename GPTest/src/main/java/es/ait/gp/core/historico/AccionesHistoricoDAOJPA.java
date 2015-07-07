/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.historico;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class AccionesHistoricoDAOJPA implements AccionesHistoricoDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;
    
    @Override
    public void create(AccionesHistorico accion) throws Exception
    {
        em.persist( accion );
    }

    @Override
    public AccionesHistorico find(int id) throws Exception
    {
        return em.find( AccionesHistorico.class, id );
    }
    
}
