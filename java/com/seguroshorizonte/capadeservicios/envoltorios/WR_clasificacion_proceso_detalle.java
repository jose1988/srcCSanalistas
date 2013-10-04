/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

import com.seguroshorizonte.capadeservicios.entidades.proceso;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase clasificacion_proceso_detalle retornados por las
 * operaciones web. Las instancias de esta clase almacenan tanto el resultado de
 * la operacion ejecutada como los objetos solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_clasificacion_proceso_detalle {

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
     * Identificador de la clasificacion de procesos.
     */
    private Long idClasificacionProceso;
    /**
     * Nombre de la clasificacion de procesos.
     */
    private String nombre;
    /**
     * Descripcion puntual de la clasificacion de procesos.
     */
    private String descripcion;
    /**
     * fecha de creacion del registro.
     */
    private Date fechaCreacion;
    /**
     * fecha en la que se realizo la ultima modificacion al proceso.
     */
    private Date fechaModificacion;
    /**
     * Lista de objetos de la clase proceso asociados a la clasificacion.
     */
    private ArrayList<proceso> procesos;

    /**
     * Constructor
     */
    public WR_clasificacion_proceso_detalle() {
        procesos = new ArrayList<proceso>();
    }

    /**
     * Ingresa un nuevo objeto de la clase proceso a la lista de de procesos
     * asociados de la clasificacion.
     *
     * @param nuevoProceso objeto de la clase proceso que se desea añadir a la
     * lista
     */
    public void ingresarProceso(proceso nuevoProceso) {
        this.procesos.add(nuevoProceso);
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
     * Obtiene el identificador de la clasificacion de procesos.
     *
     * @return Long con el identificador
     */
    public Long getIdClasificacionProceso() {
        return idClasificacionProceso;
    }

    /**
     * Modifica el identificador del proceso.
     *
     * @param idClasificacionProceso nuevo valor para el identificador
     */
    public void setIdClasificacionProceso(Long idClasificacionProceso) {
        this.idClasificacionProceso = idClasificacionProceso;
    }

    /**
     * Obtiene el nombre del proceso
     *
     * @return String con el nombre del proceso
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del proceso.
     *
     * @param nombre String con el nuevo nombre del proceso
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripcion del proceso.
     *
     * @return String con la descripcion del proceso
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion del proceso.
     *
     * @param descripcion String con el nuevo valor de la descripcion del
     * proceso
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha en la que se creo el registro de la clasificacion de
     * procesos.
     *
     * @return Objeto de la clase Date con la fecha en que se realizo el
     * registro
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Modifica la fecha en que se creo el registro.
     *
     * @param fechaCreacion Date con la nueva fecha de creacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene la fecha de la ultima modificacion de la clasificacion de
     * procesos.
     *
     * @return Date con la fecha de la ultima modificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Modifica la fecha de modificacion de la clasificacion de procesos.
     *
     * @param fechaModificacion Date con la nueva fecha de modificacion de la
     * clasificacion de procesos
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Obtiene la lista de procesos asociados a la clasificacion que posee el
     * envoltorio. Dependiendo de la operacion que llene el envoltorio, esta
     * lista podria tener de 0 a N elementos
     *
     * @return Lista de los procesos asociados a la clasificacion
     */
    public ArrayList<proceso> getProcesos() {
        return procesos;
    }

    /**
     * Asigna una lista de procesos al envoltorio.
     *
     * @param procesos Lista de objetos de la clase proceso
     */
    public void setProcesos(ArrayList<proceso> procesos) {
        this.procesos = procesos;
    }
}
