/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pangea
 */
@Stateless
public class grupoFacade extends AbstractFacade<grupo> {
    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public grupoFacade() {
        super(grupo.class);
    }
    
     /** 
     * Método que lista los grupos almacenados y los ordena por nombre
     */
    public List<grupo> listarGrupos(){
        
        List<grupo> lista;
        lista=em.createNamedQuery("grupo.findAll").getResultList();
        return lista;
    }
   
}
