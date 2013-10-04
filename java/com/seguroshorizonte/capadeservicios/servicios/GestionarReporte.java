/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.reporteFacade;
import com.seguroshorizonte.capadeservicios.entidades.reporte;
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
@WebService(serviceName = "GestionarReporte")
public class GestionarReporte {

    @EJB
    reporteFacade reporteFacade;
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
   
    
     @WebMethod(operationName = "contarReporte")
    public int contarReporte() {
 
     return reporteFacade.count();
    }
}
