/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.skin;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;

/**
 * Esta clase posee los metodos necesarios para validar la estructura de los
 * objetos introducidos como parametros a las operaciones web del servicio
 * Gestionar_Interface. el objetivo de esta clase solo es verificar que los
 * atributos necesarios de los objetos ingresados existen y tienen un formato
 * correcto, no se verificara la coerencia de los datos con el sistema de base
 * de datos ni la congruencia entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class GestionarInterfaceValidador {

    /**
     * Valida que tanto el id del usuario como el id de la skin asociada a el
     * existan y tengan un formato valido. El objetivo de este metodo es validar
     * que el usuario ingresado a la operacion cambiarSkin tenga la estructura
     * correcta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres y que ademas debe poseer un objeto de la
     * clase skin asociado con un numero natural como id
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    public WR_resultado validarCambiarSkin(usuario usuarioActual, skin skinActual) {
        WR_resultado Resultado = new WR_resultado();
        if (usuarioActual.getId() == null || skinActual == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los valores de los atributos 'id' y 'idSkin' del objeto de la clase usuario son invalidos");

        } else {
            if (usuarioActual.getId().isEmpty() || skinActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los valores de los atributos 'id' y 'idSkin' del objeto de la clase usuario son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
