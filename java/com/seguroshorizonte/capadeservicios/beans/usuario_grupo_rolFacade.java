/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.beans;

import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.rol;
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
public class usuario_grupo_rolFacade extends AbstractFacade<usuario_grupo_rol> {

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public usuario_grupo_rolFacade() {
        super(usuario_grupo_rol.class);
    }

    /**
     * Método que lista la información de la tabla usuario_grupo_rol
     * especificamente por el usuario ingresado cuando se inicia sesión,
     * listando la información de los grupos que posee dicho usuario
     * @param usr 
     * @return 
     */
    public Collection<grupo> gruposUsuario(usuario usr) {

        Collection<grupo> usrgr = new ArrayList<grupo>();

        usrgr = em.createNamedQuery("usuario_grupo_rol.findByUser").setParameter("idusr", usr).getResultList();

        return usrgr;
    }

    /**
     * Método que lista la información de la tabla usuario_grupo_rol
     * especificamente los usuarios dependiendo del grupo que se selecciona en
     * la página y se revisa que la información no este borrada, verificando que
     * sea false la columna borrado
     * @param idGrupo
     * @param borrado 
     * @return  
     */
    public Collection<usuario_grupo_rol> listarUsuariosGrupo(grupo idGrupo, boolean borrado) {

        Collection<usuario_grupo_rol> lista = new ArrayList<usuario_grupo_rol>();

        lista = em.createNamedQuery("usuario_grupo_rol.findByGrupo").setParameter("idGrupo", idGrupo).setParameter("borrado", borrado).getResultList();

        return lista;
    }

    /**
     *
     * @param idGrupo
     * @param borrado
     * @return
     */
    public Collection<rol> listarRolesPorGrupos(grupo idGrupo, boolean borrado) {
        return em.createNamedQuery("usuario_grupo_rol.findByRol").setParameter("idGrupo", idGrupo).setParameter("borrado", borrado).getResultList();
    }

    /**
     *
     * @param idGrupo
     * @param idRol
     * @return
     */
    public Collection<usuario_grupo_rol> listarUsuariosporGrupoyRol(grupo idGrupo, rol idRol) {
        return em.createNamedQuery("usuario_grupo_rol.findByRolGrupo").setParameter("idGrupo", idGrupo).setParameter("idRol", idRol).getResultList();
    }

}
