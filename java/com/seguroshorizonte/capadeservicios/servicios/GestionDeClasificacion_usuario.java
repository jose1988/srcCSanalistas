/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.clasificacion_usuarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_usuario;

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
@WebService(serviceName = "GestionDeClasificacion_usuario")
public class GestionDeClasificacion_usuario {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    @EJB
    clasificacion_usuarioFacade clasificacion_usuarioFacade;
    
    
    
     
     
     @WebMethod(operationName = "contarClasificacion_usuario")
    public int contarClasificacion_usuario() {

        return  clasificacion_usuarioFacade.count();
    }
}
