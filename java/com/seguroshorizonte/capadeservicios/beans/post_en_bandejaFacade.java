/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.post_en_bandeja;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pangea
 */
@Stateless
public class post_en_bandejaFacade extends AbstractFacade<post_en_bandeja> {
    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public post_en_bandejaFacade() {
        super(post_en_bandeja.class);
    }
    
}
