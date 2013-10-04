/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;


import com.seguroshorizonte.capadeservicios.beans.skinFacade;
import com.seguroshorizonte.capadeservicios.entidades.skin;
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
@WebService(serviceName = "GestionarSkin")
public class GestionarSkin {

    /**
     * This is a sample web service operation
     */
    
    @EJB
skinFacade skinFacade;
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
        
   
    
       @WebMethod(operationName = "contarSkin")
    public int contarSkin() {

        return skinFacade.count();
    }
}
