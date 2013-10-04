/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.prioridad_mensaje_en_linea;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pangea
 */
@Stateless
public class prioridad_mensaje_en_lineaFacade extends AbstractFacade<prioridad_mensaje_en_linea> {
    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public prioridad_mensaje_en_lineaFacade() {
        super(prioridad_mensaje_en_linea.class);
    }
    
}
