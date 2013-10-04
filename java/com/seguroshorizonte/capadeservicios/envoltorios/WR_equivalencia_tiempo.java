/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase equivalencia_tiempo retornados por las operaciones web
 * cuando no se trata de una lista de los mismos. Las instancias de esta clase
 * almacenan tanto el resultado de la operacion ejecutada como los objetos
 * solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_equivalencia_tiempo {

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
     * Identificador de la equivalencia de tiempo.
     */
    private Long id;
    /**
     * Nombre de la equivalencia de tiempo.
     */
    private String nombre;
    /**
     * Descripcion de la equivalencia de tiempo.
     */
    private String descripcion;
    /**
     * Valor en minutos que representa una unidad de esta equivalencia.
     */
    private Long minutos;

    /**
     * Obtiene el identificador de la equivalencia de tiempos.
     *
     * @return Long con el valor de identificador
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el identificador de la equivalencia de tiempo.
     *
     * @param id Long con el nuevo valor para el identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la equivalencia de tiempos.
     *
     * @return String con el nombre solicitado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la equivalencia de tiempos.
     *
     * @param nombre String con el nuevo nombre para la equivalencia de tiempos
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripcion de la equivalencia de tiempos.
     *
     * @return String con la descripcion solicitada
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion de la equivalencia de tiempos.
     *
     * @param descripcion String con el nuevo nombre para la equivalencia de
     * tiempos
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la cantidad de minutos que representa una unidad de esta
     * equivalencia.
     *
     * @return Long con la cantidad de minutos
     */
    public Long getMinutos() {
        return minutos;
    }

    /**
     * Modifica la cantidad de minutos que representa una unidad de esta
     * equivalencia.
     *
     * @param minutos Cantidad nueva de minutos para la equivalencia
     */
    public void setMinutos(Long minutos) {
        this.minutos = minutos;
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
}
