/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.bandejaFacade;
import com.seguroshorizonte.capadeservicios.beans.destinatarioFacade;
import com.seguroshorizonte.capadeservicios.beans.grupoFacade;
import com.seguroshorizonte.capadeservicios.beans.mensajeFacade;
import com.seguroshorizonte.capadeservicios.beans.postFacade;
import com.seguroshorizonte.capadeservicios.beans.post_en_bandejaFacade;
import com.seguroshorizonte.capadeservicios.beans.prioridad_mensaje_en_lineaFacade;
import com.seguroshorizonte.capadeservicios.beans.rolFacade;
import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.bandeja;
import com.seguroshorizonte.capadeservicios.entidades.destinatario;
import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.mensaje;
import com.seguroshorizonte.capadeservicios.entidades.post;
import com.seguroshorizonte.capadeservicios.entidades.post_en_bandeja;
import com.seguroshorizonte.capadeservicios.entidades.prioridad_mensaje_en_linea;
import com.seguroshorizonte.capadeservicios.entidades.rol;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_bandeja;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_destinatario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_mensaje;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_post;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.validadores.MensajeriaValidador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.annotation.XmlAnyElement;

/**
 * Esta clase posee las operaciones necesarias para el funcionamiento del
 * sistema de mensajeria.
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones, informacion sobre errores y los
 * objetos consultados.
 *
 * <p>El resultado de cada operacion sera devuelta en el atributo estatus de
 * cada wrapper que tendra un valor de "OK" si la operacion se realizo con exito
 * y de "FAIL" en caso contrario y la informacion detallada de los errores podra
 * encontrarse en el atributo observacion que en las ocasiones en las que se
 * solicite una lista de objetos contendra el numero de objetos encontrados
 *
 * <p>Dentro de este servicio estaran las operaciones asociadas a dos sistemas
 * de mensajeria, uno que funciona como un correo electronico y cuyos objetos
 * seran de la clase post y tendran una bandeja asociada y otro sistema que se
 * encargara de gestionar lo que entenderemos como mensajes temporales, que
 * tendran como objetivo distribuir un mensaje a diferentes usuarios, grupos o
 * roles durante un periodo de tiempo determinado y cuyos objetos seran del tipo
 * mensaje, con destinatarios y prioridades asociadas
 *
 * @author pangea technologies c.a.
 */
@EJB(name = "Mensajeria", beanInterface = Local.class)
@WebService(serviceName = "Mensajeria")
public class Mensajeria {

    /**
     * Declaracion del objeto validador y los facade necesarios. El validador
     * declarado debera verificar que fueron introducidos los parametros
     * necesarios y que tienen un valor valido.
     *
     * <p>De esta manera se aisla el codigo de la validacion basica y se evita
     * incurrir en llamados innecesarios al sistema de base de datos.
     *
     * <p>Las clases asociadas a las facade fueron generadas de manera
     * automatica a partir de las entidades y su funcion es la de gestionar las
     * operaciones asociadas a la persistencia que puedan necesitarse para
     * lograr el objetivo de las operaciones aqui expuestas.
     */
    MensajeriaValidador myValidador = new MensajeriaValidador();
    @EJB
    bandejaFacade myBandejafacade = new bandejaFacade();
    @EJB
    usuarioFacade myUsuarioFacade = new usuarioFacade();
    @EJB
    postFacade myPostfacade = new postFacade();
    @EJB
    post_en_bandejaFacade myPost_En_BandejaFacade = new post_en_bandejaFacade();
    @EJB
    grupoFacade myGrupoFacade = new grupoFacade();
    @EJB
    rolFacade myRolFacade = new rolFacade();
    @EJB
    mensajeFacade myMensajeFacade = new mensajeFacade();
    @EJB
    prioridad_mensaje_en_lineaFacade myPrioridadMensajeEnLineaFacade = new prioridad_mensaje_en_lineaFacade();
    @EJB
    destinatarioFacade myDestinatarioFacade = new destinatarioFacade();
    private String resul;

    /**
     * Retorna una lista de las bandejas o carpetas de mensajes que posee el
     * usuario.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id servira para
     * ubicar las bandejas asociadas
     * @return objeto de la clase WR_bandeja que informa sobre el resultado de
     * la operacion y posee una lista con las bandejas encontradas
     * @see WR_bandeja
     */
    @WebMethod(operationName = "ConsultarBandejas")
    public WR_bandeja ConsultarBandejas(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_bandeja Resultado;
        Resultado = myValidador.validarConsultarBandejas(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            List<bandeja> intermedios = myBandejafacade.findAll();


            /*
             * Verificamos que se encontraron los registros
             */
            if (intermedios == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("No se han encontrado registros, puede que el usuario no este registrado");
                return Resultado;
            }

            /*
             * Eliminamos los que no pertenecen al usuario
             */
            Iterator iterador = intermedios.iterator();
            bandeja bandejaAuxiliar;
            while (iterador.hasNext()) {
                bandejaAuxiliar = (bandeja) iterador.next();
                if (bandejaAuxiliar.getIdUsuario() != null) {
                    if (bandejaAuxiliar.getIdUsuario().getId().compareTo(usuarioActual.getId()) != 0) {
                        iterador.remove();
                    } else {
                        bandejaAuxiliar.setIdUsuario(null);
                        Resultado.ingresarBandeja(bandejaAuxiliar);
                    }
                } else {
                    bandejaAuxiliar.setIdUsuario(null);
                    Resultado.ingresarBandeja(bandejaAuxiliar);
                }
            }

            /*
             * Preparamos el envoltorio
             */

            Resultado.setEstatus("OK");
            Resultado.setObservacion(intermedios.size() + " bandejas encontradas");


        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Obtiene informacion detallada sobre un mensaje determinado.
     *
     * @param mensajeActual objeto de la clase mensaje que al menos debera
     * poseer el id asociado al mensaje que se desea obtener
     * @param usuarioActual objeto de la clase usuario que contendra el
     * identificador del usuario que desea leer el mensaje
     * @return objeto de la clase WR_post que informa sobre el resultado de la
     * operacion y posee en el primer elemento de su lista de mensajes el objeto
     * solicitado
     * @see WR_post
     */
    @WebMethod(operationName = "ConsultarMensaje")
    public WR_post ConsultarMensaje(@WebParam(name = "mensajeActual") post mensajeActual, @WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_post Resultado = new WR_post();
        Resultado = myValidador.validarConsultarMensaje(mensajeActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            mensajeActual = myPostfacade.find(mensajeActual.getId());
            if (mensajeActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Mensaje no encontrado");
                return Resultado;
            }
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }


            /*
             * Marcamos el mensaje como leido
             */
            Collection<post_en_bandeja> mensajes = mensajeActual.getPostenbandejaCollection();
            Iterator iterador = mensajes.iterator();
            post_en_bandeja post_en_bandejaAuxiliar;
            boolean bandera = false;
            while (iterador.hasNext()) {
                post_en_bandejaAuxiliar = (post_en_bandeja) iterador.next();
                if (post_en_bandejaAuxiliar.getIdUsuario().getId().compareTo(usuarioActual.getId()) == 0) {
                    if (!post_en_bandejaAuxiliar.getLeido()) {
                        post_en_bandejaAuxiliar.setLeido(true);
                        myPost_En_BandejaFacade.edit(post_en_bandejaAuxiliar);
                    }
                    bandera = true;
                    break;
                }
            }
            if (!bandera) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Parece que no existe un registro del mensaje asociado al usuario ingresado");
                return Resultado;
            }
            /*
             * limitamos la informacion que es retornada
             */
            mensajeActual.setPostenbandejaCollection(null);
            mensajeActual.setDe(new usuario(mensajeActual.getDe().getId()));

            /*
             * preparamos el envoltorio
             */
            Resultado.setEstatus("OK");
            Resultado.ingresarPost(mensajeActual);

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    
    
     /**
     * Obtiene informacion detallada sobre un mensaje determinado.
     *
     * @param mensajeActual objeto de la clase mensaje que al menos debera
     * poseer el id asociado al mensaje que se desea consultar si ha sido leido o no leido
     * @param usuarioActual objeto de la clase usuario que contendra el
     * identificador del usuario que desea consultar el mensaje
     * @return un string si ha sido leido o no  el mensaje
     */
    @WebMethod(operationName = "consultarLeido")
    public String consultarLeido(@WebParam(name = "mensajeActual") post mensajeActual, @WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_post Resultado = new WR_post();
        
        Resultado = myValidador.validarConsultarMensaje(mensajeActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return "Fail: EL USUARIO O EL MENSAJE NO SON VALIDOS";
        }
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            mensajeActual = myPostfacade.find(mensajeActual.getId());
            if (mensajeActual == null) {
                return "FAIL: Mensaje no encontrado";
            }
            if (usuarioActual == null) {
                return "FAIL: Usuario no encontrado";
            }

            /*
             * se consulta si el mensaje ha sido Leido o No Leido
             */
            Collection<post_en_bandeja> mensajes = mensajeActual.getPostenbandejaCollection();
            Iterator iterador = mensajes.iterator();
            post_en_bandeja post_en_bandejaAuxiliar;
            boolean bandera = false;
            while (iterador.hasNext()) {
                post_en_bandejaAuxiliar = (post_en_bandeja) iterador.next();
                if (post_en_bandejaAuxiliar.getIdUsuario().getId().compareTo(usuarioActual.getId()) == 0) {
                    if (post_en_bandejaAuxiliar.getLeido()==true) {
                        resul="Leido";
                        bandera = true;
                        break;
                    }else if (post_en_bandejaAuxiliar.getLeido()==true) {
                        resul="No Leido";
                         bandera = true;
                        break;
                    }
                       
                }
            }
            if (!bandera) {
               
                
                return "FAIL Parece que no existe un registro del mensaje asociado al usuario ingresado";
            }
           

        }catch (Exception e) {
            resul=e.getMessage().toString();
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return resul;
        }
    }
    
    
    
    /**
     * Obtiene los mensajes que un usuario determinado posee en una carpeta.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id servira para
     * ubicar las bandejas asociadas
     * @param bandejaActual objeto de la clase bandeja cuyo id servira para
     * filtrar los mensajes
     * @return objeto de la clase WR_post que informa sobre el resultado de la
     * operacion y posee una lista de los mensajes (post) solicitados
     * @see WR_post
     */
    @WebMethod(operationName = "ConsultarMensajes")
    public WR_post ConsultarMensajes(@WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "bandejaActual") bandeja bandejaActual) {
        WR_post Resultado = new WR_post();
        Resultado = myValidador.validarConsultarMensajes(usuarioActual, bandejaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            
            bandejaActual = myBandejafacade.find(bandejaActual.getId());
            if(bandejaActual== null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Bandeja no encontrada");
                return Resultado;
            }
            if(bandejaActual.getIdUsuario()!=null){
                    if(!bandejaActual.getIdUsuario().equals(usuarioActual)){
                        Resultado.setEstatus("FAIL");
                        Resultado.setObservacion("Bandeja no encontrada");
                        return Resultado;
                    }
            }
            Collection<post_en_bandeja> intermedio = usuarioActual.getPostenbandejaCollection();
            Iterator iterador = intermedio.iterator();
            post_en_bandeja post_en_bandejaAuxiliar;
            while (iterador.hasNext()) {
                post_en_bandejaAuxiliar = (post_en_bandeja) iterador.next();
                if (post_en_bandejaAuxiliar.getIdBandeja().getId() != bandejaActual.getId()) {
                    iterador.remove();
                } else {
                    post_en_bandejaAuxiliar.getIdPost().setPostenbandejaCollection(null);
                    post_en_bandejaAuxiliar.getIdPost().setTexto(null);
                    post_en_bandejaAuxiliar.getIdPost().setDe(new usuario(post_en_bandejaAuxiliar.getIdPost().getDe().getId()));
                    if(!Resultado.getPosts().contains(post_en_bandejaAuxiliar.getIdPost())){
                        Resultado.ingresarPost(post_en_bandejaAuxiliar.getIdPost());
                    }
                }
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getPosts().size() + " Mensajes encontrados");

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Crea una nueva bandeja o carpeta en la que el usuario podra archivar sus
     * mensajes.
     *
     * @param bandejaActual objeto de la clase bandeja con las caracteristicas
     * necesarias para crear el nuevo registro, incluyendo un objeto de la clase
     * usuario asociado con el atributo id correspondiente al usuario al que se
     * le creara la nueva bandeja
     * @return objeto de la clase WR_resultado que informara el resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "CrearBandeja")
    public WR_resultado CrearBandeja(@WebParam(name = "bandejaActual") bandeja bandejaActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarCrearBandeja(bandejaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            usuario nuevoUsuario = myUsuarioFacade.find(bandejaActual.getIdUsuario().getId());
            if (nuevoUsuario == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            bandeja nuevaBandeja = new bandeja();
            nuevaBandeja.setNombre(bandejaActual.getNombre());
            nuevaBandeja.setIdUsuario(nuevoUsuario);
            myBandejafacade.create(nuevaBandeja);
            Resultado.setEstatus("OK");

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Elimina una bandeja y todos los mensajes archivados en ella. Las bandejas
     * "entrada", "enviados" y "papelera" id=(1,2,3) no pueden ser eliminadas.
     *
     * <p>Estos registros son eliminados de manera logica
     *
     * @param bandejaActual objeto de la clase bandeja cuyo id se corresponde
     * con el del registro que se desea borrar
     * @return objeto de la clase WR_resultado que informara sobre el resultado
     * de la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "EliminarBandeja")
    public WR_resultado EliminarBandeja(@WebParam(name = "bandejaActual") bandeja bandejaActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarEliminarBandeja(bandejaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            bandejaActual = myBandejafacade.find(bandejaActual.getId());
            if (bandejaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Bandeja no encontrada");
                return Resultado;
            }
            
            myBandejafacade.remove(bandejaActual);
            Resultado.setEstatus("OK");
            
            /**
             * Limpiamos los registros del tipo post que no tienen registro del
             * tipo post_en_bandeja asociados
             */
            Collection<post> postList = myPostfacade.findAll();
            Iterator iterador = postList.iterator();
            post postAuxiliar;
            while (iterador.hasNext()) {
                postAuxiliar = (post) iterador.next();
                if (postAuxiliar.getPostenbandejaCollection().isEmpty()) {
                    myPostfacade.remove(postAuxiliar);
                }
            }
        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Elimina una mensaje de manera fisica. los mensajes eliminados mediante
     * este metodo ya no podran ser consultados por el usuario.
     *
     * <p>Un mensaje puede estar disponible para muchos usuarios, al eliminarlo
     * solo dejara de estar disponible para el usuario ingresado.
     *
     * @param mensajeActual objeto de la clase mensaje que desea ser eliminado
     * @param usuarioActual usuario que desea eliminar el mensaje
     * @return objeto de la clase WR_resultado que informara sobre el resultado
     * de la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "EliminarMensaje")
    public WR_resultado EliminarMensaje(@WebParam(name = "mensajeActual") post mensajeActual, @WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_resultado Resultado;
        Resultado = myValidador.validarEliminarMensaje(mensajeActual, usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Mensaje no encontrado");
                return Resultado;
            }
            if (usuarioActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Mensaje no encontrado");
                return Resultado;
            }
            Collection<post_en_bandeja> mensajes = usuarioActual.getPostenbandejaCollection();
            post_en_bandeja mensajeAuxiliar;
            Iterator iterador = mensajes.iterator();
            while (iterador.hasNext()) {
                mensajeAuxiliar = (post_en_bandeja) iterador.next();
                if (mensajeAuxiliar.getIdPost().getId() == mensajeActual.getId()) {
                    myPost_En_BandejaFacade.remove(mensajeAuxiliar);
                    Resultado.setEstatus("OK");
                    return Resultado;
                }
            }
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Mensaje no encontrado");

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Cambia el nombre de una bandeja o carpeta.
     *
     * @param carpetaActual objeto de la clase bandeja que debe poseer el id de
     * la carpeta que se desea cambiar y el nombre nuevo que se le desea dar
     * @return objeto de la clase WR_resultado que informara del resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "ModificarBandeja")
    public WR_resultado ModificarBandeja(@WebParam(name = "carpetaActual") bandeja carpetaActual, @WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarModificarBandeja(carpetaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            String nombre = carpetaActual.getNombre();
            carpetaActual = myBandejafacade.find(carpetaActual.getId());
            if (carpetaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Bandeja no encontrada");
                return Resultado;
            }
            if (carpetaActual.getIdUsuario().getId().compareTo(usuarioActual.getId()) != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El el usuario asociado a la bandeja puede modificarla");
                return Resultado;
            }
            carpetaActual.setNombre(nombre);
            myBandejafacade.edit(carpetaActual);

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Mueve un mensaje de una carpeta a otra.
     *
     * @param post_en_bandejaActual objeto de la clase post_en_bandeja cuyo post
     * asociado corresponde al mensaje que se desea mover y su bandeja asociada
     * corresponde con la bandeja de destino
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "MoverMensaje")
    public WR_resultado MoverMensaje(@WebParam(name = "post_en_bandejaActual") post_en_bandeja post_en_bandejaActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarMoverMensaje(post_en_bandejaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            bandeja bandejaAuxiliar = myBandejafacade.find(post_en_bandejaActual.getIdBandeja().getId());
            if (bandejaAuxiliar == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Bandeja no encontrada");
                return Resultado;
            }


            post_en_bandejaActual = myPost_En_BandejaFacade.find(post_en_bandejaActual.getId());
            if (post_en_bandejaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("post_en_bandeja no encontrado");
                return Resultado;
            }

            if (bandejaAuxiliar.getId() == post_en_bandejaActual.getIdBandeja().getId()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La bandeja de origen y de destino no pueden ser la misma");
            }

            post_en_bandejaActual.setIdBandeja(bandejaAuxiliar);
            myPost_En_BandejaFacade.edit(post_en_bandejaActual);

            Resultado.setEstatus("OK");

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Registra un nuevo mensaje. Este metodo registra el objeto del mensaje,
     * interpreta las direcciones de destinatario, crea los objetos necesarios
     * para asociar a los destinatarios con el mensaje y ubica el mensaje en la
     * bandeja de entrada de cada uno de ellos y en la bandeja de enviados del
     * usuario remitente
     *
     * @param mensajeActual objeto de la clase post que se desea registrar, debe
     * contener el identificador del usuario remitente, las direcciones de los
     * usuarios destinatarios el cuerpo del mensaje y el asunto del mismo
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "EnviarPost")
    public WR_resultado EnviarPost(@WebParam(name = "mensajeActual") post mensajeActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarEnviarPost(mensajeActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            String[] direcciones = mensajeActual.getPara().split("(;)");
            int i = 0;
            String[] auxiliar;
            mensajeActual.setFecha(new Date());
            myPostfacade.create(mensajeActual);
            Resultado.setEstatus("OK");
            String observacion = "";
            post_en_bandeja nuevoPostEnBandeja = new post_en_bandeja();
            nuevoPostEnBandeja.setIdBandeja(myBandejafacade.find(Long.parseLong("2")));
            nuevoPostEnBandeja.setIdPost(mensajeActual);
            nuevoPostEnBandeja.setIdUsuario(mensajeActual.getDe());
            nuevoPostEnBandeja.setLeido(true);
            myPost_En_BandejaFacade.create(nuevoPostEnBandeja);
            while (i < direcciones.length) {
                System.out.print("direccion completa " + direcciones[i]);

                auxiliar = direcciones[i].split("@");
                System.out.print("nombre: " + auxiliar[0] + " tipo: " + auxiliar[1]);
                if (auxiliar[1].compareTo("usuario") == 0) {
                    usuario destinatario = myUsuarioFacade.find(auxiliar[0]);
                    if (destinatario == null) {
                        Resultado.setEstatus("Enviado con fallos");
                        observacion = observacion + "La direccion: " + direcciones[i] + "es invalida; ";
                    } else {
                        nuevoPostEnBandeja = new post_en_bandeja();
                        nuevoPostEnBandeja.setIdBandeja(myBandejafacade.find(Long.parseLong("1")));
                        nuevoPostEnBandeja.setIdPost(mensajeActual);
                        nuevoPostEnBandeja.setIdUsuario(destinatario);
                        nuevoPostEnBandeja.setLeido(false);
                        myPost_En_BandejaFacade.create(nuevoPostEnBandeja);
                    }
                }
                if (auxiliar[1].compareTo("grupo") == 0) {
                    EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
                    EntityManager em = emf.createEntityManager();
                    Long idGrupo = (Long) em.createNativeQuery("SELECT id FROM grupo WHERE nombre='" + auxiliar[0] + "'").getSingleResult();
                    grupo grupoDestinatario = myGrupoFacade.find(idGrupo);
                    if (grupoDestinatario == null) {
                        Resultado.setEstatus("Enviado con fallos");
                        observacion = observacion + "La direccion: " + direcciones[i] + "es invalida; ";
                    } else {
                        Collection<usuario_grupo_rol> intermedio = grupoDestinatario.getUsuariogruporolCollection();
                        ArrayList<usuario> destinatariosFinales = new ArrayList<usuario>();
                        Iterator iterador = intermedio.iterator();
                        usuario_grupo_rol usuarioGrupoRolAuxiliar = new usuario_grupo_rol();
                        usuario usuarioAuxiliar;
                        while (iterador.hasNext()) {
                            usuarioGrupoRolAuxiliar = (usuario_grupo_rol) iterador.next();
                            if (!destinatariosFinales.contains(usuarioGrupoRolAuxiliar.getIdUsuario())) {
                                destinatariosFinales.add(usuarioGrupoRolAuxiliar.getIdUsuario());
                            }
                        }
                        iterador = destinatariosFinales.iterator();
                        while (iterador.hasNext()) {
                            usuarioAuxiliar = (usuario) iterador.next();
                            nuevoPostEnBandeja = new post_en_bandeja();
                            nuevoPostEnBandeja.setIdBandeja(myBandejafacade.find(Long.parseLong("1")));
                            nuevoPostEnBandeja.setIdPost(mensajeActual);
                            nuevoPostEnBandeja.setIdUsuario(usuarioAuxiliar);
                            nuevoPostEnBandeja.setLeido(false);
                            myPost_En_BandejaFacade.create(nuevoPostEnBandeja);
                        }
                    }
                }
                if (auxiliar[1].compareTo("rol") == 0) {
                    EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
                    EntityManager em = emf.createEntityManager();
                    Long idRol = (Long) em.createNativeQuery("SELECT id FROM rol WHERE nombre='" + auxiliar[0] + "'").getSingleResult();
                    rol rolDestinatario = myRolFacade.find(idRol);
                    if (rolDestinatario == null) {
                        Resultado.setEstatus("Enviado con fallos");
                        observacion = observacion + "La direccion: " + direcciones[i] + "es invalida; ";
                    } else {
                        Collection<usuario_grupo_rol> intermedio = rolDestinatario.getUsuariogruporolCollection();
                        ArrayList<usuario> destinatariosFinales = new ArrayList<usuario>();
                        Iterator iterador = intermedio.iterator();
                        usuario_grupo_rol usuarioGrupoRolAuxiliar = new usuario_grupo_rol();
                        usuario usuarioAuxiliar;
                        while (iterador.hasNext()) {
                            usuarioGrupoRolAuxiliar = (usuario_grupo_rol) iterador.next();
                            if (!destinatariosFinales.contains(usuarioGrupoRolAuxiliar.getIdUsuario())) {
                                destinatariosFinales.add(usuarioGrupoRolAuxiliar.getIdUsuario());
                            }
                        }
                        iterador = destinatariosFinales.iterator();
                        while (iterador.hasNext()) {
                            usuarioAuxiliar = (usuario) iterador.next();
                            nuevoPostEnBandeja = new post_en_bandeja();
                            nuevoPostEnBandeja.setIdBandeja(myBandejafacade.find(Long.parseLong("1")));
                            nuevoPostEnBandeja.setIdPost(mensajeActual);
                            nuevoPostEnBandeja.setIdUsuario(usuarioAuxiliar);
                            nuevoPostEnBandeja.setLeido(false);
                            myPost_En_BandejaFacade.create(nuevoPostEnBandeja);
                        }
                    }
                }
                i++;
            }


        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Registra un nuevo mensaje temporal. Este metodo registra el objeto del
     * mensaje, registra los destinatarios, crea los objetos necesarios para
     * asociar a los destinatarios con el sus respectivas prioridades
     *
     * @param Destinatarios objeto de la clase ArrayList<destinatario> que
     * posee, los destinatarios que se desea que el mensaje tenga, estos debe
     * estar asociados con uno y solo un objeto de la clase usuario, grupo o
     * rol, y deben especificar en el campo filtro el tipo de destinatario que
     * se quiere crear, tambien deben estar asociados con un objeto registrado
     * de la clase mensaje_prioridad_en_linea
     * @param usuarioActual objeto de la clase usario que posee el identificador
     * del ususario remitente del mensaje
     * @param mensajeActual objeto de la clase mensaje que posee los atributos
     * relacionados con el mensaje que se desea registrar
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "EnviarMensaje")
    public WR_resultado EnviarMensaje(@WebParam(name = "destinatarios") WR_destinatario destinatarios, @WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "mensajeActual") mensaje mensajeActual) {
        WR_resultado Resultado = new WR_resultado();
        ArrayList<destinatario> Destinatarios = destinatarios.getDestinatarios();
        Resultado = myValidador.validarEnviarMensaje(Destinatarios, mensajeActual, usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
            }
            mensajeActual.setIdUsuario(usuarioActual);
            mensajeActual.setBorrado(false);
            mensajeActual.setEstado("Activo");
            myMensajeFacade.create(mensajeActual);
            Iterator iterador = Destinatarios.iterator();
            destinatario destinatarioAuxiliar;
            prioridad_mensaje_en_linea prioridadAuxiliar;
            while (iterador.hasNext()) {
                destinatarioAuxiliar = (destinatario) iterador.next();
                prioridadAuxiliar = myPrioridadMensajeEnLineaFacade.find(destinatarioAuxiliar.getIdPrioridadMensajeEnLinea().getId());
                if (prioridadAuxiliar == null) {
                    Resultado.setEstatus("FAIL");
                    Resultado.setObservacion("El mensaje no pudo ser enviado a todos los destinatarios, al menos una de las prioridades asociadas no fue encontrada");
                    return Resultado;
                }
                destinatarioAuxiliar.setIdPrioridadMensajeEnLinea(prioridadAuxiliar);
                if (destinatarioAuxiliar.getFiltro().compareTo("usuario") == 0) {
                    destinatarioAuxiliar.setIdUsuario(myUsuarioFacade.find(destinatarioAuxiliar.getIdUsuario().getId()));
                    destinatarioAuxiliar.setIdGrupo(null);
                    destinatarioAuxiliar.setIdRol(null);
                    if (destinatarioAuxiliar.getIdUsuario() == null) {
                        Resultado.setEstatus("FAIL");
                        myMensajeFacade.remove(mensajeActual);
                        Resultado.setObservacion("Al menos uno de los destinatarios no existe, El mensaje no pudo ser enviado");
                    }

                }
                if (destinatarioAuxiliar.getFiltro().compareTo("grupo") == 0) {
                    destinatarioAuxiliar.setIdGrupo(myGrupoFacade.find(destinatarioAuxiliar.getIdGrupo().getId()));
                    destinatarioAuxiliar.setIdRol(null);
                    destinatarioAuxiliar.setIdUsuario(null);
                    if (destinatarioAuxiliar.getIdGrupo() == null) {
                        Resultado.setEstatus("FAIL");
                        myMensajeFacade.remove(mensajeActual);
                        Resultado.setObservacion("Al menos uno de los destinatarios no existe, El mensaje no pudo ser enviado");
                    }
                }
                if (destinatarioAuxiliar.getFiltro().compareTo("rol") == 0) {
                    destinatarioAuxiliar.setIdRol(myRolFacade.find(destinatarioAuxiliar.getIdRol().getId()));
                    destinatarioAuxiliar.setIdUsuario(null);
                    destinatarioAuxiliar.setIdGrupo(null);
                    if (destinatarioAuxiliar.getIdRol() == null) {
                        Resultado.setEstatus("FAIL");
                        myMensajeFacade.remove(mensajeActual);
                        Resultado.setObservacion("Al menos uno de los destinatarios no existe, El mensaje no pudo ser enviado");
                    }
                }
                destinatarioAuxiliar.setFechaCreacion(new Date());
                destinatarioAuxiliar.setIdMensaje(mensajeActual);
                destinatarioAuxiliar.setEstado("Activo");
                myDestinatarioFacade.create(destinatarioAuxiliar);
            }

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Retorna los mensajes temporales asociados al usuario. Este metodo busca
     * todos los mensajes  vigentes asociados al usuario al grupo al que 
     * perteneces dicho usuario o a su rol y luego los retorna junto a su remitente.
     *
     * @param usuarioActual objeto de la clase usario que posee el identificador
     * del usuario que desea revisar sus mensajes temporales
     * @return objeto de la clase WR_mensaje que contiene la lista de mensajes
     * encontrados e informa sobre el resultado de la operacion
     * @see WR_mensaje
     */
    @WebMethod(operationName = "ConsultarMensajeTemporales")
    public WR_mensaje ConsultarMensajeTemporales(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_mensaje Resultado = new WR_mensaje();
        Resultado = myValidador.validarConsultarMensajesTemporales(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }

            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            Date fecha = new Date();
            int year = fecha.getYear() + 1900;
            int month = fecha.getMonth() + 1;
            String fechaString = "" + year + "-" + month + "-" + fecha.getDate();
            List<Long> mensajesGrupo = (List<Long>) em.createNativeQuery("SELECT distinct  mensaje.id FROM   public.mensaje,   public.destinatario,   public.grupo,   public.usuario_grupo_rol WHERE   destinatario.id_mensaje = mensaje.id AND   destinatario.id_grupo = grupo.id AND   usuario_grupo_rol.id_grupo = grupo.id AND  mensaje.borrado =  false AND destinatario.fecha_finalizacion > '" + fechaString + "'   AND  usuario_grupo_rol.id_usuario ='" + usuarioActual.getId() + "' AND destinatario.borrado= false;").getResultList();
            System.out.printf("SELECT distinct  mensaje.id FROM   public.mensaje,   public.destinatario,   public.grupo,   public.usuario_grupo_rol WHERE   destinatario.id_mensaje = mensaje.id AND   destinatario.id_grupo = grupo.id AND   usuario_grupo_rol.id_grupo = grupo.id AND  mensaje.borrado =  false AND destinatario.fecha_finalizacion > '" + fechaString + "'   AND  usuario_grupo_rol.id_usuario ='" + usuarioActual.getId() + "' AND destinatario.borrado= false;");
            List<Long> mensajesRol = (List<Long>) em.createNativeQuery("SELECT  distinct  mensaje.id FROM   public.mensaje,   public.destinatario,   public.usuario_grupo_rol,   public.rol WHERE   destinatario.id_mensaje = mensaje.id AND   destinatario.id_rol = rol.id AND   usuario_grupo_rol.id_rol = rol.id AND destinatario.fecha_finalizacion > '" + fechaString + "'  AND  mensaje.borrado = false AND  usuario_grupo_rol.id_usuario = '" + usuarioActual.getId() + "' AND destinatario.borrado= false;").getResultList();
            List<Long> mensajesUsuario = (List<Long>) em.createNativeQuery("SELECT    mensaje.id   FROM   public.mensaje,   public.destinatario WHERE   destinatario.id_mensaje = mensaje.id AND  mensaje.borrado = false AND destinatario.fecha_finalizacion > '" + fechaString + "'  AND  destinatario.id_usuario ='" + usuarioActual.getId() + "' AND destinatario.borrado= false;").getResultList();
            Iterator iterador = mensajesGrupo.iterator();
            mensaje mensajeAuxililar;
            while (iterador.hasNext()) {
                mensajeAuxililar = myMensajeFacade.find((Long) iterador.next());
                mensajeAuxililar.setDestinatarioCollection(null);
                mensajeAuxililar.setEstado(null);
                mensajeAuxililar.setIdUsuario(new usuario(mensajeAuxililar.getIdUsuario().getId()));

                Resultado.ingresarMensaje(mensajeAuxililar);
            }
            iterador = mensajesRol.iterator();
            while (iterador.hasNext()) {
                mensajeAuxililar = myMensajeFacade.find((Long) iterador.next());
                mensajeAuxililar.setDestinatarioCollection(null);
                mensajeAuxililar.setEstado(null);
                mensajeAuxililar.setIdUsuario(new usuario(mensajeAuxililar.getIdUsuario().getId()));
                if (!Resultado.getMensajes().contains(mensajeAuxililar)) {
                    Resultado.ingresarMensaje(mensajeAuxililar);
                }
            }
            iterador = mensajesUsuario.iterator();
            while (iterador.hasNext()) {
                mensajeAuxililar = myMensajeFacade.find((Long) iterador.next());
                mensajeAuxililar.setDestinatarioCollection(null);
                mensajeAuxililar.setEstado(null);
                mensajeAuxililar.setIdUsuario(new usuario(mensajeAuxililar.getIdUsuario().getId()));
                if (!Resultado.getMensajes().contains(mensajeAuxililar)) {
                    Resultado.ingresarMensaje(mensajeAuxililar);
                }
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getMensajes().size() + " mensajes concontrados");
        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Elimina uno de los mensajes temporales enviados por el usuario. Este
     * metodo elimina un mensaje de manera logica incluso antes de que se
     * alcance la fecha de finalizacion de los destinatarios. por lo que ya el
     * mensaje no estara disponible para ellos. <p>El mensaje solo sera
     * eliminado en caso de que el usuario ingresado sea el remitente.
     *
     * @param usuarioActual objeto de la clase usario que posee el identificador
     * del usuario que desea eliminar el mensaje
     * @param mensajeActual objeto de la clase usuario que posee el
     * identificador del mensaje que se desea eliminar
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "EliminarMensajeTemporal")
    public WR_resultado EliminarMensajeTemporal(@WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "mensajeActual") mensaje mensajeActual) {
        WR_resultado Resultado;
        Resultado = myValidador.validarEliminarMensajeTemporal(usuarioActual, mensajeActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            mensajeActual = myMensajeFacade.find(mensajeActual.getId());
            if (mensajeActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("mensaje no encontrado");
                return Resultado;
            }
            if (mensajeActual.getIdUsuario().getId().compareTo(usuarioActual.getId()) == 0) {
                mensajeActual.setBorrado(true);
                myMensajeFacade.edit(mensajeActual);
                Resultado.setEstatus("OK");
            } else {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Solo el usuario remitente puede eliminar sus mensajes");
            }

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }

    /**
     * Consulta los mensajes temporales que han sido enviados por el usuario
     * introducido. Este metodo retorna todos los mensajes temporales enviados
     * sin importar las fechas de finalizacion asociadas.
     *
     * @param usuarioActual objeto de la clase usario que posee el identificador
     * del usuario que desea consultar sus mensajes
     * @return objeto de la clase WR_mensaje que contiene la lista de los
     * mensajes temporales encontrados e informa sobre el resultado de la
     * operacion
     * @see WR_mensaje
     */
    @WebMethod(operationName = "ConsultarMensajesTemporalesEnviados")
    public WR_mensaje ConsultarMensajesTemporalesEnviados(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_mensaje Resultado;
        Resultado = myValidador.validarConsultarMensajesTemporalesEnviados(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no registrado");
                return Resultado;
            }
            Iterator iterador = usuarioActual.getMensajeCollection().iterator();
            mensaje mensajeAuxiliar;
            while (iterador.hasNext()) {
                mensajeAuxiliar = (mensaje) iterador.next();
                mensajeAuxiliar.setDestinatarioCollection(null);
                mensajeAuxiliar.setIdUsuario(null);
                if (!mensajeAuxiliar.getBorrado()) {
                    Resultado.getMensajes().add(mensajeAuxiliar);
                }
            }

            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getMensajes().size() + " mensajes encontrados");
        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            System.out.print("*******************************************************************************");
            e.printStackTrace();
        } finally {
            return Resultado;
        }
    }
}
