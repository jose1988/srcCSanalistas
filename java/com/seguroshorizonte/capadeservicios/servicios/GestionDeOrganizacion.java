/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.organizacionFacade;
import com.seguroshorizonte.capadeservicios.entidades.organizacion;
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
@WebService(serviceName = "GestionDeOrganizacion")
public class GestionDeOrganizacion {

    @EJB
    organizacionFacade organizacionFacade;
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    
    
   
        
    
   
    
    
    @WebMethod(operationName = "contarOrganizacion")
    public int contarOrganizacion() {
 
     return organizacionFacade.count();
    } 
}
