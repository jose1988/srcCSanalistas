/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.grupoFacade;
import com.seguroshorizonte.capadeservicios.beans.usuario_grupo_rolFacade;
import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.rol;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Pangea
 */
@WebService(serviceName = "GestionDeGrupo")
public class GestionDeGrupo {

    @EJB
    grupoFacade grupoFacade;
    @EJB
    usuario_grupo_rolFacade ugrFacade;

    /**
     * This is a sample web service operation
     *
     * @param txt
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     *
     * @return
     */
    @WebMethod(operationName = "contarGrupo")
    public int contarGrupo() {

        return grupoFacade.count();
    }

    /**
     * Método que lista la información de la tabla usuario_grupo_rol
     * especificamente por el usuario ingresado cuando se inicia sesión,
     * listando la información de los grupos que posee dicho usuario
     *
     * @param usr
     * @return
     */
    @WebMethod(operationName = "gruposUsuario")
    public Collection<grupo> gruposUsuario(@WebParam(name = "user") usuario usr) {

        return ugrFacade.gruposUsuario(usr);
    }

    /**
     * Método que lista los grupos almacenados y los ordena por nombre
     *
     * @return
     */
    @WebMethod(operationName = "listarGrupos")
    public List<grupo> listarGrupos() {

        return grupoFacade.listarGrupos();
    }

    /**
     * Método que lista la información de la tabla usuario_grupo_rol
     * especificamente los usuarios dependiendo del grupo que se selecciona en
     * la página y se verifica que dicha información no este borrada,
     * verificando que el dato borrado sea false
     *
     * @param idGrupo
     * @param borradoo
     * @return
     */
    @WebMethod(operationName = "listarUsuariosGrupo")
    public Collection<usuario_grupo_rol> listarUsuariosGrupo(@WebParam(name = "grupousuarios") grupo idGrupo, @WebParam(name = "borrado") boolean borradoo) {

        return ugrFacade.listarUsuariosGrupo(idGrupo, borradoo);
    }

    /**
     * Método que devuelve una lista de los roles que existen por un grupo
     * determinado
     *
     * @param idGrupo objeto de la clase Grupo que contiene el identificador(id)
     * del grupo por el que se buscara los roles
     * @param borradoo booleano con el cual identifica si esta como no borrado
     * @return lista de la clase Rol
     */
    @WebMethod(operationName = "listarRolesPorGrupo")
    public Collection<rol> listarRolesPorGrupo(@WebParam(name = "grupousuarios") grupo idGrupo, @WebParam(name = "borrado") boolean borradoo) {
        Collection<rol> lista = ugrFacade.listarRolesPorGrupos(idGrupo, borradoo);
        return lista;
    }

    /**
     * Método que devuelve una lista de la clase usuario_grupo_rol con los
     * usuarios que existen por un grupo y un por rol determinado
     *
     * @param idGrupo objeto de la clase Grupo que contiene el identificador(id)
     * del grupo por el que se buscara los usuarios
     * @param idRol objeto de la clase Rol que contiene el identificador(id) del
     * rol por el que se buscara los usuarios
     * @return lista de la clase usuario_grupo_rol con los usuarios
     */
    @WebMethod(operationName = "listarUsuariosPorGrupoYRol")
    public Collection<usuario_grupo_rol> listarUsuariosPorGrupoYRol(@WebParam(name = "grupousuarios") grupo idGrupo, @WebParam(name = "roles") rol idRol) {
        return ugrFacade.listarUsuariosporGrupoyRol(idGrupo, idRol);
    }
}
