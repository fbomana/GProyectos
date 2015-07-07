/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ait.gp.core.documentacion;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitkiar
 */
@Stateless
public class TiposDocumentacionDAOJPA implements TiposDocumentacionDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    private EntityManager em;

    @Override
    public void create(TiposDocumentacion tipo) throws Exception
    {
        em.persist( tipo );
    }

    @Override
    public void edit(TiposDocumentacion tipo) throws Exception
    {
        em.merge( tipo );
    }

    @Override
    public void remove(TiposDocumentacion tipo) throws Exception
    {
        tipo = em.find(TiposDocumentacion.class, tipo.getTdocId() );
        if ( tipo != null )
        {
            em.remove(tipo);
        }
    }

    @Override
    public TiposDocumentacion find(Object id) throws Exception
    {
        return em.find(TiposDocumentacion.class, id );
    }

    @Override
    public List<TiposDocumentacion> findAll() throws Exception
    {
        return em.createNamedQuery("TiposDocumentacion.findAll").getResultList();
    }
}
