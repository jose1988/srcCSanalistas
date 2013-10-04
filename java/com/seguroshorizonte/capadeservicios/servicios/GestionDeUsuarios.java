/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Pangea
 */
@WebService(serviceName = "GestionDeUsuarios")
public class GestionDeUsuarios {

    @EJB
    usuarioFacade usuarioFacade;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Método creado para guardar la información de un usuario por medio del identificador(Id)
     */
    @WebMethod(operationName = "buscarUsuario")
    public usuario buscarUsuario(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        return usuarioFacade.find(usuarioActual.getId());
    }

    @WebMethod(operationName = "contarUsuario")
    public int contarUsuario() {

        return usuarioFacade.count();
    }

    @WebMethod(operationName = "listarUsuarios")
    public List<usuario> listarUsuarios(@WebParam(name = "borrado") boolean borradoo) {
        return usuarioFacade.listarUsuarios(borradoo);
    }
}
