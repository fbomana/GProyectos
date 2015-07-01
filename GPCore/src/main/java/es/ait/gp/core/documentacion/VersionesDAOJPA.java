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
 * Implementación del DAO de versiones usando JPA.
 * @author aitkiar
 */
@Stateless
public class VersionesDAOJPA implements VersionesDAO
{
    @PersistenceContext(unitName = "GPCorePU")
    EntityManager em;

    @Override
    public void create(Versiones version) throws Exception
    {
        em.persist( version );
        
        Documentacion documentacion = version.getDocuId();
        documentacion.getVersiones().add( version );
        em.merge( documentacion );
    }

    @Override
    public void edit(Versiones version) throws Exception
    {
        em.merge( version );
    }

    @Override
    public void remove(Versiones version) throws Exception
    {
        if ( version.getVersId() != null )
        {
            version = em.find( Versiones.class, version.getVersId() );
            em.remove( version );
        }
    }

    @Override
    public Versiones find(Object id) throws Exception
    {
        if ( id != null )
        {
            return em.find( Versiones.class, id );
        }
        return null;
    }

    @Override
    public List<Versiones> findAll() throws Exception
    {
        return em.createNamedQuery( "Versiones.findAll" ).getResultList();
    }    
}
