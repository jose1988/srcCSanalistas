/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.rolFacade;
import com.seguroshorizonte.capadeservicios.beans.usuario_grupo_rolFacade;
import com.seguroshorizonte.capadeservicios.entidades.rol;
import com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol;
import java.util.ArrayList;
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
@WebService(serviceName = "GestionDeRol")
public class GestionDeRol {

    @EJB
    rolFacade rolFacade;
    @EJB
    usuario_grupo_rolFacade usuarioGrupoRolFacade;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "editarRol")
    public void editarRol(@WebParam(name = "edtiRol") rol registro) {
        rolFacade.edit(registro);
    }

    @WebMethod(operationName = "contarRol")
    public int contarRol() {

        return rolFacade.count();
    }

  
}
