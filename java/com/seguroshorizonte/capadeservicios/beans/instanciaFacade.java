/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.instancia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pangea
 */
@Stateless
public class instanciaFacade extends AbstractFacade<instancia> {
    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public instanciaFacade() {
        super(instancia.class);
    }
    
    /**
     * Método que lista el estado de la instancia
     */
    public  List<String> buscarestados(){
        List<String> c;
        Query q=em.createNamedQuery("instancia.findEstados");
        c= q.getResultList();
        return c; 
    }
    
}
