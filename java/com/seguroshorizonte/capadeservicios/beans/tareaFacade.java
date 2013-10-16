/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.tarea;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pangea
 */
@Stateless
public class tareaFacade extends AbstractFacade<tarea> {
    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public tareaFacade() {
        super(tarea.class);
    }
    
    /**
     * Método que obtiene el id de la tarea inicial del proceso
     */
    @WebMethod(operationName = "TareaInicial")
    public long TareaInicial() {
       long idtarea=0;
       int ban=0,i=0;
       List<tarea> tareas=this.findAll();
       while(ban==0){
          if(tareas.get(i).getBorrado()){
              idtarea=tareas.get(i).getId();
              ban=1;
          }
       }
        return idtarea;

    }
}
