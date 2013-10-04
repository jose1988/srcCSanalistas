/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.destinatarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.destinatario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pangea
 */
@WebService(serviceName = "ConsultarDestinatarios")
public class ConsultarDestinatarios {

    @EJB
    destinatarioFacade myDestinatarioFacade = new destinatarioFacade();

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public ArrayList<destinatario> hello() {
        ArrayList Resultado = new ArrayList();
        Collection<destinatario> intermedio = myDestinatarioFacade.findAll();
        Iterator iterador = intermedio.iterator();
        while (iterador.hasNext()) {
            Resultado.add((destinatario) iterador.next());
        }
        return Resultado;
    }
}
