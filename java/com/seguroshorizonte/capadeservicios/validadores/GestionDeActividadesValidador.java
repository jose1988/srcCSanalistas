/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.actividad;
import com.seguroshorizonte.capadeservicios.entidades.cola_de_tarea;
import com.seguroshorizonte.capadeservicios.entidades.condicion;
import com.seguroshorizonte.capadeservicios.entidades.documento;
import com.seguroshorizonte.capadeservicios.entidades.sesion;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_actividad;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_documento;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_usuario;

/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio Gestion_De_Actividades. el
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
public class GestionDeActividadesValidador {

    /**
     * Valida que el documento introducido posea las caracteristicas necesarias
     * para la construccion de un nuevo documento y que ademas estas posean el
     * formato adecuado. El objetivo de este metodo es asegurar que los
     * atributos de los objetos introducidos como parametros a la operacion 
     * agregarDocumento existen y poseen el formate adecuado
     *
     * @param documentoActual objeto de la clase documento a con los campos
     * necesarios para crear un nuevo registro
     * @param actividadActual objeto de la clase actividad cuyo id debe ser un 
     * numero natural
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarAgregarDocumento(documento documentoActual, actividad actividadActual) {
        WR_resultado Resultado = new WR_resultado();
        if (actividadActual.getId() == null || documentoActual.getNombre() == null || documentoActual.getProposito() == null || documentoActual.getRuta() == null || documentoActual.getFechaDocumento() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("los siguientes campos son obligatorios: nombre,proposito,observacion,ruta,fecha_documento por favor introducir los valores correspondientes");
        } else {
            if (actividadActual.getId() < 0 || documentoActual.getNombre().isEmpty() || documentoActual.getProposito().isEmpty() || documentoActual.getRuta().isEmpty() || documentoActual.getFechaDocumento().getYear()<0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("los siguientes campos son obligatorios: nombre,proposito,observacion,ruta. Verifique ademas la fecha de documento ingresada");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el atributo id de la actividad ingresada sea un numero
     * natural. el objetivo de este metodo es asegurar que el objeto introducido
     * como parametro a la operacion ConsultarActividad tenga el formato
     * adecuado.
     *
     * @param actividadActual objeto de la clase actividad a analizar
     * @return objeto de la clase WR_actividad que informara sobre el resultado
     * de la validacion
     * @see WR_actividad
     */
    public WR_actividad validarConsultarActividad(actividad actividadActual) {
        WR_actividad Resultado = new WR_actividad();
        if (actividadActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valor invalido del atributo 'id' del objeto de la clase actividad ingresado");
        } else {
            if (actividadActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valor invalido del atributo 'id' del objeto de la clase actividad ingresado");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Validar que tanto el atributo id del usuario introducido como el atributo
     * estado de la actividad ingresada posean el formato adecuado. El objetivo
     * de este metodo es asegurar que los objetos ingresados como parametros a
     * la operacion consultarActividads tengan los formatos adecuados.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser un
     * numero natural
     * @param actividadActual objeto de la clase actividad cuyo estado debe ser
     * una cadena no vacia de caracteres
     * @return objeto de la clase WR_actividad que informa sobre el resultado de
     * la validacion
     * @see WR_actividad
     */
    public WR_actividad validarConsultarActividads(usuario usuarioActual, actividad actividadActual) {
        WR_actividad Resultado = new WR_actividad();
        if (usuarioActual.getId() == null || actividadActual.getEstado() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valor invalido en los atributos 'id' de los objetos ingresados");
        } else {
            if (usuarioActual.getId().isEmpty() || actividadActual.getEstado().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valor invalido en los atributos 'id' de los objetos ingresados");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Validar que el atributo id del doocumento ingresado posee el formato
     * adecuado. El objetivo de este metodo es validar que los objetos
     * introducidos a la operacion consultarDocumento tengan el formato
     * adecuado.
     *
     * @param documentoActual objeto de la clase documento cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_documento que informa sobre eel resultado
     * de la validacion
     * @see WR_documento
     */
    public WR_documento validarConsultarDocumento(documento documentoActual) {
        WR_documento Resultado = new WR_documento();
        if (documentoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' de objeto de la clase documento es invalido");
        } else {
            if (documentoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' de objeto de la clase documento es invalido");
            } else {
                Resultado.setEstatus("OK");
            }

        }
        return Resultado;
    }

    /**
     * Valida que el atributo id de la actividad ingresada sea un numero
     * natural. El objetivo de este metodo es validar que el objeto de la clase
     * actividad ingresado a la operacion consultarDocumentos tiene el formato
     * adecuado.
     *
     * @param actividadActual objeto de la clase actividad cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_documento que informa sobre el resultado de
     * la validacion
     * @see WR_documento
     */
    public WR_documento validarConsultarDocumentos(actividad actividadActual) {
        WR_documento Resultado = new WR_documento();
        if (actividadActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase actividad ingresado es invalido");
        } else {
            if (actividadActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase actividad ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id de la actividad introducida sea uun numero natural. El
     * objetivo de este metodo es validar que las actividades ingresadas como
     * parametro a la operacion IniciarActividad posean el formato adecuado.
     *
     * @param actividadActual objeto de la clase actividad cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarIniciarActividad(actividad actividadActual,sesion sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        if (actividadActual.getId() == null || sesionActual.getId() ==null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo 'id' de la actividad o de la sesion introducido es invalido");
        } else {
            if (actividadActual.getId() < 0 || sesionActual.getId()<0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo 'id' de la actividad o de la sesion introducido es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que la actividad y el usuario ingresados posean atributos id
     * validos. El objetivo de este metodo es validar que los objetos ingresados
     * como parametros a la operacion asignarActividad tengan el formato
     * adecuado.
     *
     * @param actividadActual objeto de la clase actividad cuyo id debe ser un
     * numero natural
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_resultado validarAsignarActividad(actividad actividadActual, usuario usuarioActual) {
        WR_resultado Resultado = new WR_resultado();
        if (actividadActual.getId() == null || usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valores invalidos en los atributos 'id' asociados a los objetos ingresados");
        } else {
            if (actividadActual.getId() < 0 || usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valores invalidos en los atributos 'id' asociados a los objetos ingresados");
            } else {
                Resultado.setEstatus("OK");
            }

        }
        return Resultado;
    }

    /**
     * Valida que la actividad ingresada posea un identificador valido. El
     * objetivo de este metodo es validar que los objetos ingresados como
     * parametros a la operacion consultarCandidatos tengan el formato adecuado.
     *
     * @param actividadActual objeto de la clase actividad cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_usuario que informa del resultado de la
     * validacion
     * @see WR_usuario
     */
    public WR_usuario validarConsultarCandidatos(actividad actividadActual) {
        WR_usuario Resultado = new WR_usuario();
        if (actividadActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del identificador de la actividad introducida es invalido");
        } else {
            if (actividadActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del identificador de la actividad introducida es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    public WR_resultado validarIngresarAuditoria(sesion sesionActual, actividad actividaActual) {
        WR_resultado Resultado = new WR_resultado();
        if (sesionActual.getId() == null || actividaActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los identificadores de la actividad o la sesion igresados son invallidos");

        } else {
            if (sesionActual.getId() < 0 || actividaActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los identificadores de la actividad o la sesion igresados son invallidos");

            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    public WR_resultado validarFinalizarActividad(actividad actividadActual, sesion sesionActual,condicion condicionActual) {
        WR_resultado Resultado = new WR_resultado();
        if (actividadActual.getId() == null || sesionActual.getId() == null || condicionActual.getId() ==null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los atributos identificadores de la actividad o la sesion introducidos son invallidos");
        } else {
            if (actividadActual.getId() < 0 || sesionActual.getId() < 0 || condicionActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los atributos identificadores de la actividad o la sesion introducidos son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
    
    public WR_resultado validarEliminarDocumento(documento documentoActual){
        WR_resultado Resultado = new WR_resultado();
        if(documentoActual.getId()==null){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del documento introducido es invalido");
        }else{
            if(documentoActual.getId()<0){
                 Resultado.setEstatus("FAIL");
                 Resultado.setObservacion("El identificador del documento introducido es invalido");
            }else{
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
    
    public WR_actividad validarConsultarActividadesCola(usuario usuarioActual){
        WR_actividad Resultado = new WR_actividad();
        if(usuarioActual.getId() == null){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del usuario ingresado es invalido");
        }else{
            if(usuarioActual.getId().isEmpty()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador del usuario ingresado es invalido");
            }else{
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
    
    public WR_resultado validarConsumirCola(actividad actividadActual, usuario usuarioActual){
        WR_resultado Resultado = new WR_resultado();
        if(actividadActual.getId() ==null || usuarioActual.getId()==null){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los identificadores de la actividad y el usuario introducidos son invalidos");
        }else{
            if(actividadActual.getId() < 0 || usuarioActual.getId().isEmpty()){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los identificadores de la actividad y el usuario introducidos son invalidos");            
            }else{
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
    
    public WR_actividad validarPrimeroEnEntrarPrimeroEnSalirDeCola(cola_de_tarea Cola) {
        WR_actividad Resultado = new WR_actividad();
        if (Cola.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador de Cola de Tarea introducido es invalido");
        } else {
            if (Cola.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador de Cola de Tarea introducido es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
