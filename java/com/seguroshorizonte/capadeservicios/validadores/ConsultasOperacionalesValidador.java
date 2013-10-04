/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.equivalencia_tiempo;
import com.seguroshorizonte.capadeservicios.entidades.prioridad;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_equivalencia_tiempo;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_grupo;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_organizacion;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_prioridad;

/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio Consultas_Operacionales. el
 * objetivo de esta clase solo es verificar que los atributos necesarios de los
 * objetos ingresados existen y tienen un formato correcto, no se verificara la
 * coerencia de los datos con el sistema de base de datos ni la congruencia
 * entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class ConsultasOperacionalesValidador {

    /**
     * Valida que el atributo id del usuario ingresado sea un numero natural. 
     * el objetivo de este metodo es validar que los objetos ingresados como
     * parametro a la operacion ConsultarOrganizacion tengan el formato
     * necesario
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_organizacion que informa sobre el resultado
     * de la validacion
     * @see WR_organizacion
     */
    public WR_organizacion validarConsultarOrganizacion(usuario usuarioActual) {
        WR_organizacion resultado = new WR_organizacion();
        if (usuarioActual.getId() == null) {
            resultado.setEstatus("FAIL");
            resultado.setObservacion("El identificador del usuario introducido es invalido");
        } else {
            if (usuarioActual.getId().isEmpty()) {
                resultado.setEstatus("FAIL");
                resultado.setObservacion("El identificador del usuario introducido es invalido");
            } else {
                resultado.setEstatus("OK");
            }
        }
        return resultado;
    }

    /**
     * Valida que el atributo id del usuario ingresado sea una cadena no vacia
     * de caracteres. el objetivo de este metodo es validar los objetoos
     * ingresados como parametro a la operacion ConsultarGrupos
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @return objeto de la clase WR_grupo que informa sobre el resultado de la
     * operacion
     * @see WR_grupo
     */
    public WR_grupo validarConsultarGrupos(usuario usuarioActual) {
        WR_grupo Resultado = new WR_grupo();
        if (usuarioActual.getId().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valor invalido del atributo 'id' correspondiente al 'usuario' ingresado");
        } else {
            Resultado.setEstatus("OK");
        }
        return Resultado;
    }

    /**
     * Valida que el atributo id de la equivalencia_tiempo introducida sea un
     * numero natural. El objetivo de este metodo es validar que los objetos
     * introducidos como parametros a la operacion ConsultarEquivalencia tengan
     * el formato adecuado.
     *
     * @param equivalencia_tiempoActual objeto de la clase equivalencia_tiempo
     * cuyo atributo id debe ser un numero natural
     * @return objeto de la clase WR_equivalencia_tiempo que informa sobre el
     * resultado de la operacion
     * @see WR_equivalencia_tiempo
     */
    public WR_equivalencia_tiempo validarConsultarEquivalencia(equivalencia_tiempo equivalencia_tiempoActual) {
        WR_equivalencia_tiempo Resultado = new WR_equivalencia_tiempo();
        if (equivalencia_tiempoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valor invalido para el atributo 'id' del objeto de la clase 'equivalencia_tiempo' ingresado");
        } else {
            if (equivalencia_tiempoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valor invalido para el atributo 'id' del objeto de la clase 'equivalencia_tiempo' ingresado");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del objeto prioridadActual sea un numero natural. El
     * objetivo de este metodo es validar que los atributos de los objetos
     * ingresados a la operacion ConsultarPrioridad tengan el formmato adecuado
     *
     * @param prioridadActual objeto de la clase prioridad cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_prioridad que informara sobre el resultado
     * de la validacion
     * @see WR_prioridad
     */
    public WR_prioridad validarConsultarPrioridad(prioridad prioridadActual) {
        WR_prioridad Resultado = new WR_prioridad();
        if (prioridadActual.getId()==null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valor invalido del atributo 'id' del objeto de la clase 'prioridad' ingresado");
           
        } 
        else {
            if (prioridadActual.getId() == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valor invalido del atributo 'id' del objeto de la clase 'prioridad' ingresado");
            } else {
                    Resultado.setEstatus("OK");
            }

        }
        return Resultado;
    }
}
