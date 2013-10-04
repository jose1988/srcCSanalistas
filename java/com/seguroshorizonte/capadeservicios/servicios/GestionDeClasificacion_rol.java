/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.clasificacion_rolFacade;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_rol;
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
@WebService(serviceName = "GestionDeClasificacion_rol")
public class GestionDeClasificacion_rol {

    @EJB
    clasificacion_rolFacade clasificacion_rol_Facadee;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

   
    

   
    @WebMethod(operationName = "editar_cla_Rol")
    public void editar_cla_Rol(@WebParam(name = "editar_cla_Rol") clasificacion_rol registro) {
        clasificacion_rol_Facadee.edit(registro);
    }

    
    @WebMethod(operationName = "contarClasificacion")
    public int contarClasificacion() {
 
     return clasificacion_rol_Facadee.count();
    } 
}
