/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

import com.seguroshorizonte.capadeservicios.entidades.contador_round_robin;
import java.util.ArrayList;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase contador_round_robin retornados por las operaciones web.
 * Las instancias de esta clase almacenan tanto el resultado de la operacion
 * ejecutada como los objetos solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_contador_round_robin {

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
     * Lista de objetos de la clase contador_round_robin que son retornados por
     * la operacion web.
     */
    private ArrayList<contador_round_robin> contador_Round_Robins;

    /**
     * Constructor
     */
    public WR_contador_round_robin() {
        contador_Round_Robins = new ArrayList<contador_round_robin>();
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
     * Obtiene la lista de contador_round_robin que posee el envoltorio.
     * Dependiendo de la operacion que llene el envoltorio, esta lista podria
     * tener de 0 a N elementos
     *
     * @return Lista de los contador_round_robin obtenidos
     */
    public ArrayList<contador_round_robin> getContador_Round_Robins() {
        return contador_Round_Robins;
    }

    /**
     * Asigna una lista de contador_round_robin al envoltorio.
     *
     * @param contador_Round_Robins Lista de objetos de la clase
     * contador_round_robin
     */
    public void setContador_Round_Robins(ArrayList<contador_round_robin> contador_Round_Robins) {
        this.contador_Round_Robins = contador_Round_Robins;
    }
}
