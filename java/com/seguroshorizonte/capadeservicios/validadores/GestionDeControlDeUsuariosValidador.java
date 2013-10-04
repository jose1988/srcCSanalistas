/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.sesion;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_sesion;

/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio
 * Gestion_De_Control_De_Usuarios. el objetivo de esta clase solo es verificar
 * que los atributos necesarios de los objetos ingresados existen y tienen un
 * formato correcto, no se verificara la coerencia de los datos con el sistema
 * de base de datos ni la congruencia entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class GestionDeControlDeUsuariosValidador {

    /**
     * Valida que los atributos id y clave del usuario introducido tengan un
     * formato valido. El objetivo de este metodo es validar que los atributos
     * del usuario introducido como parametro a la operacion LogIn existan y
     * tengan un foormato valido.
     *
     * @param usuarioActual objeto de la clase usuario cuyos atributos id y
     * clave deben ser un numero natural y una cadena no vacia de caracteres
     * respectivamente
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_sesion validarLogIn(sesion sesionActual) {
        WR_sesion Resultado = new WR_sesion();
        if (sesionActual.getIdUsuario().getId() == null || sesionActual.getIdUsuario().getClave() == null) {

            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los valores de los atributos 'id' y clave del objeto de la clase usuario son invalidos");
        } else {
            if (sesionActual.getIdUsuario().getId().isEmpty() || sesionActual.getIdUsuario().getClave().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los valores de los atributos 'id' y clave del objeto de la clase usuario son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el atributo id del usuario introducido sea un numero natural.
     * El objetivo de este metodo es validar que el usuario ingresado a la
     * operacion LogOut tenga un formato valido.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarLogOut(sesion sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        if (sesionActual.getId() == null) {

            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo id de la sesión ingresado es invalido");
        } else {
            if (sesionActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo id de la sesión ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
