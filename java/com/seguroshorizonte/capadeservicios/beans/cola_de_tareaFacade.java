/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.cola_de_tarea;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pangea
 */
@Stateless
public class cola_de_tareaFacade extends AbstractFacade<cola_de_tarea> {

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public cola_de_tareaFacade() {
        super(cola_de_tarea.class);
    }

    public long consultaIdMinimo() {
        return (Long) (em.createNamedQuery("cola_de_tarea.findByMinimoId").getSingleResult());//(Long) (em.createNativeQuery("SELECT Min(c.id) FROM cola_de_tarea c,actividad a,grupo g,tarea t where c.id_tarea=t.id and c.id_grupo=g.id and c.id_actividad=a.id and t.borrado=false and g.borrado=false and a.borrado=false and c.borrado=false").getSingleResult());
    }
}
