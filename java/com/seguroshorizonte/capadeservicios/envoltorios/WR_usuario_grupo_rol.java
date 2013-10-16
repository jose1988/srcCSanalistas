/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.envoltorios;

import com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol;
import java.util.ArrayList;

/**
 * Esta clase posee los metodos y atributos para servir de envoltorio a los
 * objetos de la clase usuario_grupo_rol retornados por las operaciones web. Las
 * instancias de esta clase almacenan tanto el resultado de la operacion
 * ejecutada como los objetos solicitados.
 *
 * @author pangea technologies c.a.
 */
public class WR_usuario_grupo_rol {

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
     * Lista de objetos de la clase usuario_grupo_proceso que son retornados por
     * la operacion web.
     */
    private ArrayList<usuario_grupo_rol> usuario_Grupo_Rols;

    /**
     * Constructor
     */
    public WR_usuario_grupo_rol() {
        usuario_Grupo_Rols = new ArrayList<usuario_grupo_rol>();
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
     * Obtiene la lista de usuario_grupo_rol que posee el envoltorio.
     * Dependiendo de la operacion que llene el envoltorio, esta lista podria
     * tener de 0 a N elementos
     *
     * @return Lista de los usuario_grupo_rol obtenidos
     */
    public ArrayList<usuario_grupo_rol> getUsuario_Grupo_Rols() {
        return usuario_Grupo_Rols;
    }

    /**
     * Asigna una lista de usuario_grupo_rol al envoltorio.
     *
     * @param usuario_Grupo_Rols Lista de objetos de la clase usuario_grupo_rol
     */
    public void setUsuario_Grupo_Rols(ArrayList<usuario_grupo_rol> usuario_Grupo_Rols) {
        this.usuario_Grupo_Rols = usuario_Grupo_Rols;
    }

    public void ingresarUsuarioGrupoRol(usuario_grupo_rol Nuevo) {
        usuario_Grupo_Rols.add(Nuevo);
    }
}
