/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.reporte;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_reporte;

/**
 * Esta clase posee los metodos necesarios para validar que los objetos
 * introducidos a las operaciones web del servicio Reportes tengann la
 * estructura correcta. operaciones web del servicio Gestionar_Interface. el
 * objetivo de esta clase solo es verificar que los atributos necesarios de los
 * objetos ingresados existen y tienen un formato correcto, no se verificara la
 * coerencia de los datos con el sistema de base de datos ni la congruencia
 * entre ellos.
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class ReportesValidador {

    /**
     * Valida quue el id del reporte ingresado sea un numero natural. El
     * objetivo de este metodo es validar que los objetos introducidos a la
     * operacion consultarReporte tengan la estructura correcta.
     *
     * @param reporteActual objeto de la clase reporte cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_reporte que informa sobre el resultado de
     * la validacion
     * @see WR_reporte
     */
    public WR_reporte validarConsultarReporte(reporte reporteActual) {
        WR_reporte Resultado = new WR_reporte();
        if (reporteActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase reporte ingresado es invalido");
        } else {
            if (reporteActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase reporte ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del usuario ingresado sea una cadena no vacia de
     * caracteres. El objetivo de este metodo es validar que los objetos
     * introducidos a la operacion consultarReportesDisponibles tengan la
     * estructura correcta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser un
     * numero natural
     * @return objetoo de la clase WR_reporte que iinforma sobre el resultado de
     * la validacion
     * @see WR_reporte
     */
    public WR_reporte validarConsultarReportesDisponibles(usuario usuarioActual) {
        WR_reporte Resultado = new WR_reporte();
        if (usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase usuario ingresado es invalido");
        } else {
            if (usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase usuario ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
