/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

import com.seguroshorizonte.capadeservicios.entidades.usuario;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase clasificacion_usuario_detalle retornados por las
 * operaciones web. Las instancias de esta clase almacenan tanto el resultado de
 * la operacion ejecutada como los objetos solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_clasificacion_usuario_detalle {

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
     * Identificador de la clasificacion de usuarios.
     */
    private Long idClasificacionUsuario;
    /**
     * Nombre de la clasificacion de usuarios.
     */
    private String nombre;
    /**
     * Descripcion de la clasificacion de usuarios.
     */
    private String descripcion;
    /**
     * Fecha en la que se creo la clasificacion de usuarios.
     */
    private Date fechaCreacion;
    /**
     * Fecha en la que se creo la ultima modificacion a la clasificacion de
     * usuarios.
     */
    private Date fechaModificacion;
    /**
     * Lista de objetos de la clase usuario asociados a la clasificacion.
     */
    private ArrayList<usuario> usuarios;

    /**
     * Constructor
     */
    public WR_clasificacion_usuario_detalle() {
        usuarios = new ArrayList<usuario>();
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
     * Obtiene el identificador de la clasificacion de usuarios.
     *
     * @return Long con el valor del identificador de la clasificacion
     */
    public Long getIdClasificacionUsuario() {
        return idClasificacionUsuario;
    }

    /**
     * Modifica el identificador de la clasificacion de usuarios.
     *
     * @param idClasificacionUsuario Long con el nuevo identificador para la
     * clasificacion
     */
    public void setIdClasificacionUsuario(Long idClasificacionUsuario) {
        this.idClasificacionUsuario = idClasificacionUsuario;
    }

    /**
     * Obtiene el nombre de la clasificacion de usuarios.
     *
     * @return String con el nombre de la clasificacion
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la clasificacion de usuarios.
     *
     * @param nombre String con el nombre que se desea asignar a la
     * clasificacion de usuarios
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripcion de la clasificacion de usuarios.
     *
     * @return String con la descripcion de la clasificacion de usuarios
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion de la clasificacion de usuarios.
     *
     * @param descripcion String con la nueva descripcion para la clasificacion
     * de usuarios
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha en la que se creo la clasificacion de usuarios.
     *
     * @return Date con la fecha solicitada
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Modifica la fecha de creacion de la clasificacion de usuarios.
     *
     * @param fechaCreacion Date con la nueva fecha de creacion de la
     * clasificacion de usuarios
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene la fecha en la que se realizo la ultima modificacion a la
     * clasificacion de usuarios.
     *
     * @return Date con la fecha solicitada
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Modifica la fecha en la que se realizo la ultima modificacion a la
     * clasificacion de usuarios.
     *
     * @param fechaModificacion Date con el valor de la nueva fecha que se desea
     * asignar
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Obtiene la lista de usuarios asociados a la clasificacion que posee el
     * envoltorio. Dependiendo de la operacion que llene el envoltorio, esta
     * lista podria tener de 0 a N elementos
     *
     * @return Lista de los usuarios asociados a la clasificacion
     */
    public ArrayList<usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Asigna una lista de usuarios al envoltorio.
     *
     * @param usuarios Lista de objetos de la clase usuario
     */
    public void setUsuarios(ArrayList<usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
