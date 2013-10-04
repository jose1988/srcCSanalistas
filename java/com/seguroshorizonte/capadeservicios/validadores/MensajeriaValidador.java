/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.bandeja;
import com.seguroshorizonte.capadeservicios.entidades.destinatario;
import com.seguroshorizonte.capadeservicios.entidades.mensaje;
import com.seguroshorizonte.capadeservicios.entidades.post;
import com.seguroshorizonte.capadeservicios.entidades.post_en_bandeja;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_bandeja;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_mensaje;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_post;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Esta clase posee los metodos necesarios para validar la estructura de los
 * objetos introducidos como parametros a las operaciones web del servicio
 * Mensajeria. operaciones web del servicio Gestionar_Interface. el objetivo de
 * esta clase solo es verificar que los atributos necesarios de los objetos
 * ingresados existen y tienen un formato correcto, no se verificara la
 * coerencia de los datos con el sistema de base de datos ni la congruencia
 * entre ellos.
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class MensajeriaValidador {

    /**
     * Valida que el id del usuario introducido sea una cadena no vacia de
     * caracteres. El objetivo de este metodo es validar que los objetos
     * introducidos como parametros a la operacion consultarBandejas posean el
     * formato deseado.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @return objeto de la clase WR_bandeja que informa sobre el resultado de
     * la validacion
     * @see WR_bandeja
     */
    public WR_bandeja validarConsultarBandejas(usuario usuarioActual) {
        WR_bandeja Resultado = new WR_bandeja();
        if (usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo 'id' del objeto de la clase 'usuario' ingresado es invalido");
        } else {
            if (usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo 'id' del objeto de la clase 'usuario' ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del post introducido como parametro sea un numero
     * natural. El objetivo de este metodo es validar que los objetos
     * introducidos como parametros a la operacion consultarMensaje tengan el
     * formato correcto.
     *
     * @param mensajeActual objeto de la clase post cuyo id debe ser un numero
     * natural
     * @return objeto de la clase WR_post que informa sobre el resultado de la
     * validacion
     * @see WR_post
     */
    public WR_post validarConsultarMensaje(post mensajeActual) {
        WR_post Resultado = new WR_post();
        if (mensajeActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'mensaje' introducido es invalido");
        } else {
            if (mensajeActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase 'mensaje' introducido es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que tanto el id del usuario introducido como el de la bandeja
     * tengan el formato deseado. El objetivo de este metodo es validar que los
     * objetos ingresados como parametros a la operacion consultaMensajes tengan
     * la estructura correcta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @param bandejaActual objeto de la clase bandeja cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_post que informa sobre el resultado de la
     * validacion
     * @see WR_post
     */
    public WR_post validarConsultarMensajes(usuario usuarioActual, bandeja bandejaActual) {
        WR_post Resultado = new WR_post();
        if (usuarioActual.getId() == null || bandejaActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los valores de los atributos 'id' de los objetos ingresados son invalidos");
        } else {
            if (usuarioActual.getId().isEmpty() || bandejaActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los valores de los atributos 'id' de los objetos ingresados son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que tanto el id como el nombre de la bandeja introducida posean el
     * formato adecuado. EL objetivo de este metodo es validar que los objetos
     * introducidos como parametros a la operacion crearBandeja tengan la
     * estructura adecuada.
     *
     * @param bandejaActual objeto de la clase bandeja cuyo id debe ser un
     * numero natural y nombre debe ser una cadena no vacia de caracteres
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarCrearBandeja(bandeja bandejaActual) {
        WR_resultado Resultado = new WR_resultado();
        if (bandejaActual.getIdUsuario().getId() == null || bandejaActual.getNombre() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los valores de los atributos 'id' de los objetos ingresados son invalidos");
        } else {
            if (bandejaActual.getIdUsuario().getId().isEmpty() || bandejaActual.getNombre().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los valores de los atributos 'id' de los objetos ingresados son invalidos");
            } else {
                if (bandejaActual.getNombre().compareTo("Recibidos") == 0 || bandejaActual.getNombre().compareTo("Enviados") == 0 || bandejaActual.getNombre().compareTo("Papelera") == 0) {
                    Resultado.setEstatus("FAIL");
                    Resultado.setObservacion("Los nombres 'Recibidos','Enviados' y 'Papelera' son reservados y no pueden usarse para crear una nueva bandeja");
                } else {
                }
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id de la bandeja introducida sea un numero natural mayor a
     * 3. El objetivo de este metodo es validar que los objetos introducidos
     * como parametros a la operacion eliminarBandeja posean la estructura
     * adecuada.
     *
     * <p> en este caso no basta con que el identificador de la bandeja sea un
     * numero natural. ademas debe ser mayor a tres puesto que las primeras tres
     * bandejas son obligatorias para todos los usuarios y por tanto no deberian
     * poderse eliminar.
     *
     * @param bandejaActual objeto de la clase bandeja cuyo id debe ser un
     * numero natural mayor a 3
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarEliminarBandeja(bandeja bandejaActual) {
        WR_resultado Resultado = new WR_resultado();
        if (bandejaActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase bandeja ingresado es invalido");
        } else {
            if (bandejaActual.getId() < 4) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El valor del atributo 'id' del objeto de la clase bandeja ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del post introoducido sea un numero natural. El objetivo
     * de este metodo es validar que los objetos ingresados como parametros a la
     * operacion eliminarMensaje posean la estructura correcta.
     *
     * @param mensajeActual objeto de la clase post cuyo id debe ser un numero
     * natural
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarEliminarMensaje(post mensajeActual, usuario usuarioActual) {
        WR_resultado Resultado = new WR_resultado();
        if (mensajeActual.getId() == null || usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo 'id' de los objetos de la clases post y usuario ingresados son invalidos");
        } else {
            if (mensajeActual.getId() < 0 || usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo 'id' de los objetos de la clases post y usuario ingresados son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que tanto el id como el nombre de la bandeja introducida tengan el
     * formato deseado. El objetivo de este metoodo es validar que los objetos
     * ingresados como parametros a la operacion modificarBandeja posean la
     * estructura adecuada.
     *
     * @param bandejaActual objeto de la clase bandeja cuyo id debe ser un
     * numero natural y nombre debe ser una cadena no vacia de caracteres
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    public WR_resultado validarModificarBandeja(bandeja bandejaActual) {
        WR_resultado Resultado = new WR_resultado();
        if (bandejaActual.getId() == null || bandejaActual.getNombre() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El nombre o el id de la bandeja introducida parece ser invalido");

        } else {
            if (bandejaActual.getId() < 4 || bandejaActual.getNombre().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El nombre o el id de la bandeja introducida parece ser invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que tanto el id del post_en_bandeja ingresado como el de la badeja
     * asociada sean numeros naturales. El objetivo de este metodo es validar
     * que los objetos introducidos como parametro a la operacion moverMensaje
     * posean la estructura correcta.
     *
     * @param postActual objeto de la clase post_en_bandeja cuyo id y el de su
     * bandeja asociada deben ser numeros naturales
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_resultado validarMoverMensaje(post_en_bandeja postActual) {
        WR_resultado Resultado = new WR_resultado();
        if (postActual.getId() == null || postActual.getIdBandeja().getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los valores de los atributos identificadores del objeto de la clase post_en_bandeja introducidos son invalidos");
        } else {
            if (postActual.getId() < 0 || postActual.getIdBandeja().getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los valores de los atributos identificadores del objeto de la clase post_en_bandeja introducidos son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que los atributos del mensaje tengan el formato correcto,
     * incluyendo las direcciones de los destinatarios. El objetivo de este
     * metodo es validar que los objetos introducidos como parametro a la
     * operacion EnviarPost posean la estructura correcta.
     *
     * @param mensajeActual objeto de la clase post que debe poseer una cadena
     * de caracteres de la forma [nombre]@[tipo de destinatario]; debe poseer
     * ademas el cuerpo del mensaje, un asunto y un usuario remitente asociado
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_resultado validarEnviarPost(post mensajeActual) {
        WR_resultado Resultado = new WR_resultado();

        if (mensajeActual.getPara().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Debe especificar al menos un destinatario para el mensaje");
            return Resultado;
        }

        if (!Pattern.matches("([a-zA-Z1-9]+@((usuario)|(rol)|(grupo));)*", mensajeActual.getPara())) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Las direcciones de los destinatarios ingresadas no poseen el formato deseado");
            return Resultado;
        }

        if (mensajeActual.getTexto().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("No se ha ingresado el cuerpo al mensaje");
            return Resultado;
        }
        if (mensajeActual.getAsunto().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("No se ha ingresado asunto al mensaje");
            return Resultado;
        }
        if (mensajeActual.getDe().getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El mensaje no posee remitente");
            return Resultado;
        }
        if (mensajeActual.getDe().getId().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El mensaje no posee remitente");
            return Resultado;
        }
        Resultado.setEstatus("OK");

        return Resultado;
    }

    /**
     * Valida que el mensaje, el usuario y los destinatarios introducidos tengan
     * el formato deseado. El objetivo de este metodo es validar que los objetos
     * introducidos como parametro a la operacion EnviarMensaje posean la
     * estructura correcta.
     *
     * @param destinatariosActual objeto de la clase ArrayList<destinatario> que
     * debe poseer al menos un objeto destinatario valido asociado
     * @param mensajeActual objeto de la clase mensaje que debe poseer el texto
     * correspondiente al cuerpo del mensaje a enviar
     * @param usuarioActual objeto de la clase usuario que debera contener el
     * identificador del usuario remitente del mensaje
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_resultado validarEnviarMensaje(ArrayList destinatarios, mensaje mensajeActual, usuario usuarioActual) {
        WR_resultado Resultado = new WR_resultado();
        if (destinatarios == null || mensajeActual == null || usuarioActual == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("No se ingresaron todos los parametros necesarios");
            return Resultado;
        }
        if (mensajeActual.getTexto().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El mensaje no posee un cuerpo");
            return Resultado;
        }
        if (destinatarios.isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("No se han encontrado destinatarios");
            return Resultado;
        }
        if (usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del usuario ingresado es invalido");
            return Resultado;
        }
        if (usuarioActual.getId().isEmpty()) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del usuario ingresado es invalido");
            return Resultado;
        }
        Iterator iterador = destinatarios.iterator();
        destinatario destinatarioAuxiliar;
        int ban = 0;
        while (iterador.hasNext()) {
            destinatarioAuxiliar = (destinatario) iterador.next();
            if ((destinatarioAuxiliar.getIdGrupo() == null && destinatarioAuxiliar.getIdRol() == null && destinatarioAuxiliar.getIdUsuario() == null) || (destinatarioAuxiliar.getFiltro().compareTo("usuario") != 0 && destinatarioAuxiliar.getFiltro().compareTo("grupo") != 0 && destinatarioAuxiliar.getFiltro().compareTo("rol") != 0)) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los destinatarios no poseen el formato correcto");
                return Resultado;
            }
            if (destinatarioAuxiliar.getIdGrupo().getId() != null) {
                ban++;
            }
            if (destinatarioAuxiliar.getIdRol().getId() != null) {
                ban++;
            }
            if (!destinatarioAuxiliar.getIdUsuario().getId().isEmpty()) {
                ban++;
            }
            if (ban != 1) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Solo uno de los identificadores de destinatario debe ser ingresado");
                return Resultado;
            }
            ban = 0;
        }
        Resultado.setEstatus("OK");
        return Resultado;
    }

    /**
     * Valida que el identificador del usuario introducido tenga el formato
     * correcto. El objetivo de este metodo es validar que los objetos
     * introducidos como parametro a la operacion ConsultarioMensajeTemporales
     * posean la estructura correcta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo identificador debera
     * ser una cadena no vacia de caracteres
     * @return objeto de la clase WR_mensaje que informa del resultado de la
     * validacion
     * @see WR_mensaje
     */
    public WR_mensaje validarConsultarMensajesTemporales(usuario usuarioActual) {
        WR_mensaje Resultado = new WR_mensaje();
        if (usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del usuario ingresado es invalido");
        } else {
            if (usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador del usuario ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que los identificadores del usuario y del mensaje introducidos
     * tengan el formato correcto. El objetivo de este metodo es validar que los
     * objetos introducidos como parametro a la operacion
     * EliminarMensajeTemporal posean la estructura correcta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo identificador debera
     * ser una cadena no vacia de caracteres
     * @param mensajeActual objeto de la clase mensaje cuyo identificador debera
     * ser un nuero natural
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_resultado validarEliminarMensajeTemporal(usuario usuarioActual, mensaje mensajeActual) {
        WR_resultado Resultado = new WR_resultado();
        if (usuarioActual.getId() == null || mensajeActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Los campos identificadores del usuario o el mensaje introducidos son invalidos");
        } else {
            if (usuarioActual.getId().isEmpty() || mensajeActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Los campos identificadores del usuario o el mensaje introducidos son invalidos");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el identificador del usuario introducido tenga el formato
     * correcto. El objetivo de este metodo es validar que los objetos
     * introducidos como parametro a la operacion
     * ConsultarioMensajeTemporalesEnviados posean la estructura correcta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo identificador debera
     * ser una cadena no vacia de caracteres
     * @return objeto de la clase WR_mensaje que informa del resultado de la
     * validacion
     * @see WR_mensaje
     */
    public WR_mensaje validarConsultarMensajesTemporalesEnviados(usuario usuarioActual) {
        WR_mensaje Resultado = new WR_mensaje();
        if (usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del usuario ingresado es invalido");
        } else {
            if (usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador del usuario ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
