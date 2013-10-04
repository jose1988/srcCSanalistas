/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

import com.seguroshorizonte.capadeservicios.entidades.destinatario;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase detinatario retornados por las operaciones web. Las
 * instancias de esta clase almacenan tanto el resultado de la operacion
 * ejecutada como los objetos solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_destinatario {

    /**
     * Resultado de la ejecucion de la operacion web que retorno el wrapper.
     */
    private String estatus;
    /**
     * Notas sobre errores ocurridos durante la ejecucion de la operacion web y
     * notas sobre los objetos obtenidos.
     */
    private String observacion;
    /**
     * Lista de objetos de la clase destinatario que son retornados por la
     * operacion web.
     */
    private ArrayList<destinatario> destinatarios;

    /**
     * Constructor
     */
    public WR_destinatario() {
        destinatarios = new ArrayList<destinatario>();
    }

    /**
     * Obtiene el resultado de la operacion. Este campo generalmente solo
     * retorna "OK" si la operacion se ejecuto con exito o "Fail" si hubo algun
     * problema durante el proceso.
     *
     * @return String con el resultado de la operacion
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Asigna un valor al resultado de la operacion que origino el wrapper. Solo
     * deberian asignarse "OK" o "FAIL" a este campo.
     *
     * @param Estatus String con el resultado de la operacion web que origino el
     * wrapper
     */
    public void setEstatus(String Estatus) {
        if (Estatus.compareTo("OK") == 0 || Estatus.compareTo("FAIL") == 0) {
            this.estatus = Estatus;
        }
    }

    /**
     * Retorna mas informacion sobre el resultado de la operacion que origino el
     * wrapper Este metodo deberia devolver informacion sobre errores de
     * ejecucion de la operacion o comentarios sobre los resultados obtenidos.
     *
     * @return String con notas sobre errores o resultados de la operacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Asigna un valor al campo observacion. Aqui se deben asignar notas sobre
     * errores o sobre los resultados de la ejecucion de la operacion que genero
     * el wrapper.
     *
     * @param observacion String con el valor que se desea asignar como
     * observacion.
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Obtiene la lista de destinatarios que posee el envoltorio. Dependiendo de
     * la operacion que llene el envoltorio, esta lista podria tener de 0 a N
     * elementos
     *
     * @return Lista de los destinatarios obtenidos
     */
    public ArrayList<destinatario> getDestinatarios() {
        return destinatarios;
    }

    /**
     * Asigna una lista de destinatarios al envoltorio.
     *
     * @param destinatarios Lista de objetos de la clase destinatario
     */
    public void setDestinatarios(ArrayList<destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * Ingresa un nuevo objeto de la clase destinatario a la lista de
     * destinatarios del envoltorio.
     *
     * @param nuevoDestinatario objeto de la clase destinatario que se desea
     * añadir a la lista
     */
    public void ingresarDocumento(destinatario nuevoDocumento) {
        destinatarios.add(nuevoDocumento);
    }
}
