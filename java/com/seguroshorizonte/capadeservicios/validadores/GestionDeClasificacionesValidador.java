/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.clasificacion_proceso;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_rol;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_tarea;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_proceso_detalle;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_rol_detalle;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_tarea_detalle;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_usuario_detalle;

/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio Gestion_De_Clasificaciones.
 * el objetivo de esta clase solo es verificar que los atributos necesarios de
 * los objetos ingresados existen y tienen un formato correcto, no se verificara
 * la coerencia de los datos con el sistema de base de datos ni la congruencia
 * entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class GestionDeClasificacionesValidador {

    /**
     * Valida que el atributo id de la clasificacion_proceso introducida se un
     * numero natural. El objetivo de este metodo es validar que los objetos
     * introducidos a la operacion consultarDetallesClasificacionProceso posean
     * el formato correcto.
     *
     * @param clasificacion_procesoActual objeto de la clase
     * clasificacion_proceso cuyo id debe ser un numero natural
     * @return objeto de la clase WR_clasificacion_proceso_detalle que informa
     * sobre el resultado de la validacion
     * @see WR_clasificacion_proceso_detalle
     */
    public WR_clasificacion_proceso_detalle validarConsultarDetallesClasificacionProceso(clasificacion_proceso clasificacion_procesoActual) {
        WR_clasificacion_proceso_detalle Resultado = new WR_clasificacion_proceso_detalle();
        if (clasificacion_procesoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_proceso' es invalido");
        } else {
            if (clasificacion_procesoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_proceso' es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el atributo id de la clasificacion_usuario introducida sea un
     * numero natural. El objetivo de este metodo es validar que los objetos
     * introducidos a la operacion consultarDetallesClasificacionUsuario posean
     * el formato correcto.
     *
     * @param clasificacion_usuarioActual objeto de la clase
     * clasificacion_usuario cuyo id debe ser un numero natural
     * @return objeto de la clase WR_clasificacion_usuario_detalle que informa
     * sobre el resultado de la validacion
     * @see WR_clasificacion_usuario_detalle
     */
    public WR_clasificacion_usuario_detalle validarConsultarDetallesClasificacionUsuario(clasificacion_usuario clasificacion_usuarioActual) {
        WR_clasificacion_usuario_detalle Resultado = new WR_clasificacion_usuario_detalle();
        if (clasificacion_usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_usuario' es invalido");
        } else {
            if (clasificacion_usuarioActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_usuario' es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el atributo id de la clasificacion_tarea inntroducida sea un
     * numero natural. El objetivo de este metodo es validar que los objetos
     * introducidos a la operacion consultarDetallesClasificacionTarea posean el
     * formatoo correcto.
     *
     * @param clasificacion_tareaActual oobjeto de la clase clasificacion_tarea
     * cuyo id debe ser un numero natural
     * @return objeto de la clase WR_clasificacion_tarea_detalle que informa el
     * resultado de la validacion
     * @see WR_clasificacion_tarea_detalle
     */
    public WR_clasificacion_tarea_detalle validarConsultarDetallesClasificacionTarea(clasificacion_tarea clasificacion_tareaActual) {
        WR_clasificacion_tarea_detalle Resultado = new WR_clasificacion_tarea_detalle();
        if (clasificacion_tareaActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_tarea' es invalido");
        } else {
            if (clasificacion_tareaActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_tarea' es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el atributo id de la clasificacion_rol introducida sea un
     * numero natural. El objetivo de este metodo es validar que los objetos
     * introducidos a la operacion consultarDetallesClasificacionRol posean el
     * formato correcto.
     *
     * @param clasificacion_rolActual objeto de la clase clasificacion_rol cuyo
     * id debe ser un numero natural
     * @return objeto de la clase WR_clasificacion_rol_detalle que informa sobre
     * el resultado de la validacion
     * @see WR_clasificacion_rol_detalle
     */
    public WR_clasificacion_rol_detalle validarConsultarDetallesClasificacionRol(clasificacion_rol clasificacion_rolActual) {
        WR_clasificacion_rol_detalle Resultado = new WR_clasificacion_rol_detalle();
        if (clasificacion_rolActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_rol' es invalido");
        } else {
            if (clasificacion_rolActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'clasificacion_rol' es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
