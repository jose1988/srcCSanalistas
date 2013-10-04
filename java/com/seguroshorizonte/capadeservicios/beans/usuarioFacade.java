/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pangea
 */
@Stateless
public class usuarioFacade extends AbstractFacade<usuario> {

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public usuarioFacade() {
        super(usuario.class);
    }

    public List<usuario> listarUsuarios(boolean borrado) {
        List<usuario> lista;
        lista = em.createNamedQuery("usuario.findByBorrado").setParameter("borrado", borrado).getResultList();
        return lista;
    }
   
    
}
