/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;



import com.seguroshorizonte.capadeservicios.beans.politicaFacade;
import com.seguroshorizonte.capadeservicios.entidades.politica;
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


@WebService(serviceName = "GestionDepolitica")
public class GestionDepolitica {
@EJB
politicaFacade politicaFacade;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    
     
   
       @WebMethod(operationName = "contarPolitica")
    public int contarPolitica() {

        return  politicaFacade.count();
    }
    
          @WebMethod(operationName = "buscarPolitica")
    public politica  buscarPolitica(@WebParam(name = "buscarPolitica") Long ID)  {

        return  politicaFacade.find(ID);
    }
    
}
