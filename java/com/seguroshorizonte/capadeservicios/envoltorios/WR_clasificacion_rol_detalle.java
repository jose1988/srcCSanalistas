/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

import com.seguroshorizonte.capadeservicios.entidades.rol;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase clasificacion_rol_detalle retornados por las operaciones
 * web. Las instancias de esta clase almacenan tanto el resultado de la
 * operacion ejecutada como los objetos solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_clasificacion_rol_detalle {

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
     * Identificador de la clasificacion de roles
     */
    private Long idClasificacionRol;
    /**
     * Nombre de la clasificacion de roles
     */
    private String nombre;
    /**
     * Descripcion de la clasificacion de roles
     */
    private String descripcion;
    /**
     * Fecha en la que se creo el registro.
     */
    private Date fechaCreacion;
    /**
     * Fecha en la que se realizo la ultima modificacion.
     */
    private Date fechaModificacion;
    /**
     * Lista de objetos de la clase rol asociados a la clasificacion.
     */
    private ArrayList<rol> rols;

    /**
     * Constructor
     */
    public WR_clasificacion_rol_detalle() {
        rols = new ArrayList<rol>();
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
     * Obtiene el identificador de la clasificacion de roles.
     *
     * @return Long con el valor del identificador
     */
    public Long getIdClasificacionRol() {
        return idClasificacionRol;
    }

    /**
     * Modifica el identificador de la clasificacion de roles.
     *
     * @param idClasificacionRol Numero Natural con el nuevo valor del
     * identificador
     */
    public void setIdClasificacionRol(Long idClasificacionRol) {
        this.idClasificacionRol = idClasificacionRol;
    }

    /**
     * Obtiene el nombre de la clasificacion de roles.
     *
     * @return String con el nombre de la clasificacion de roles
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la clasificacion de roles.
     *
     * @param nombre String con el nuevo nombre para la clasificacion de roles
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripcion de la clasificacion de roles.
     *
     * @return String con la descripcion de la clasificacion de roles
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion de la clasificacion de roles.
     *
     * @param descripcion String con el nuevo nombre para la clasificacion de
     * roles
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha en la que se creo la clasificacion de roles.
     *
     * @return Date con la fecha solicitada
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Modifica la fecha en la que se creo la clasificacion de roles.
     *
     * @param fechaCreacion Date con el nuevo valor para la fecha de creacion de
     * la clasificacion de roles
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene la fecha en la que se realizo la ultima modificacion a la
     * clsificacion de roles.
     *
     * @return Date con el valor de la fecha solicitada
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Modifica la fecha en que se realizo la ultima modificacion a la
     * clasificacion de roles.
     *
     * @param fechaModificacion Date con la fecha solicitada
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Obtiene la lista de roles asociados a la clasificacion que posee el
     * envoltorio. Dependiendo de la operacion que llene el envoltorio, esta
     * lista podria tener de 0 a N elementos
     *
     * @return Lista de los roles asociados a la clasificacion
     */
    public ArrayList<rol> getRols() {
        return rols;
    }

    /**
     * Asigna una lista de roles al envoltorio.
     *
     * @param rols Lista de objetos de la clase rol
     */
    public void setRols(ArrayList<rol> rols) {
        this.rols = rols;
    }
}
