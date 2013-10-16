/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.actividadFacade;
import com.seguroshorizonte.capadeservicios.beans.auditoriaFacade;
import com.seguroshorizonte.capadeservicios.beans.cola_de_tareaFacade;
import com.seguroshorizonte.capadeservicios.beans.documentoFacade;
import com.seguroshorizonte.capadeservicios.beans.instanciaFacade;
import com.seguroshorizonte.capadeservicios.beans.sesionFacade;
import com.seguroshorizonte.capadeservicios.beans.transicionFacade;
import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.clienteweb.Actividad;
import com.seguroshorizonte.capadeservicios.clienteweb.Politica;
import com.seguroshorizonte.capadeservicios.clienteweb.AplicarPolitica_Service;
import com.seguroshorizonte.capadeservicios.clienteweb.WrResultado;
import com.seguroshorizonte.capadeservicios.entidades.actividad;
import com.seguroshorizonte.capadeservicios.entidades.auditoria;
import com.seguroshorizonte.capadeservicios.entidades.cola_de_tarea;
import com.seguroshorizonte.capadeservicios.entidades.condicion;
import com.seguroshorizonte.capadeservicios.entidades.documento;
import com.seguroshorizonte.capadeservicios.entidades.equivalencia_tiempo;
import com.seguroshorizonte.capadeservicios.entidades.instancia;
import com.seguroshorizonte.capadeservicios.entidades.prioridad;
import com.seguroshorizonte.capadeservicios.entidades.sesion;
import com.seguroshorizonte.capadeservicios.entidades.tarea;
import com.seguroshorizonte.capadeservicios.entidades.transicion;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_actividad;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_documento;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.validadores.GestionDeActividadesValidador;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.WebServiceRef;

/**
 * Esta clase contiene las operaciones necesarias para la ejecucion de
 * actividades y gestion de documentos. Las operaciones permitiran la ejecucion
 * de las actividades de una instancia de algun proceso determinado, ir pasando
 * por cada una de ellas, la asignacion de los usuarios que las ejecutaran de
 * manera manual o automatica y gestionar aquellos documentos externos al
 * sistema pero que estan asociados a las actividades realizadas.
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
 * @author pangea technologies c.a.
 */
@EJB(name = "GestionDeActividades", beanInterface = Local.class)
@WebService(serviceName = "GestionDeActividades")
public class GestionDeActividades {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_15362/Gestordepoliticas/AplicarPolitica.wsdl")
    private AplicarPolitica_Service service;
  
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
    
    @EJB
    actividadFacade actividadFacade;
    GestionDeActividadesValidador myValidador = new GestionDeActividadesValidador();
    @EJB
    actividadFacade myActividadFacade = new actividadFacade();
    @EJB
    documentoFacade myDocumentoFacade = new documentoFacade();
    @EJB
    usuarioFacade myUsuarioFacade = new usuarioFacade();
    @EJB
    sesionFacade mySesionFacade = new sesionFacade();
    @EJB
    auditoriaFacade myAuditoriaFacade = new auditoriaFacade();
    @EJB
    instanciaFacade myInstanciaFacade = new instanciaFacade();
    @EJB
    cola_de_tareaFacade myColaDeTareaFacade = new cola_de_tareaFacade();
    @EJB
    transicionFacade myTransicionFacade = new transicionFacade();
    private actividad activi;
    private WR_actividad actividad;
    /**
     * Agrega un enlace a un documento externo y lo asocia a una actividad
     * determinada. el documento sera registrado de manera de que se puedan
     * crear enlaces a el en las interfaces de usuario construidas sobre estos
     * servicios.
     *
     * @param documentoActual un objeto del tipo documento con la informacion
     * necesaria para su construccion
     * @param actividadActual un objeto de la clase actividad cuyo identificador
     * esta asociado a una actividad registrada
     * @return un objeto de la clase WR_resultado con el resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "AgregarDocumento")
    public WR_resultado AgregarDocumento(@WebParam(name = "documentoActual") documento documentoActual, @WebParam(name = "actividadActual") actividad actividadActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarAgregarDocumento(documentoActual,actividadActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        /*
         * Logica de negocios
         */
        try {
            actividadActual = myActividadFacade.find(actividadActual.getId());
            if (actividadActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Actividad no encontrada");
                return Resultado;
            }
            if(actividadActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Actividad no encontrada");
                return Resultado;
            }
            documentoActual.setFechaCreacion(new Date());
            documentoActual.setIdActividad(actividadActual);
            myDocumentoFacade.create(documentoActual);
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
     * Obtiene informacion detallada sobre una actividad registrada. La
     * informacion podra ser obtenida accediendo al primer objeto de la
     * lista de actividades del objeto de la clase WR_actividad retornado.
     *
     * @param actividadActual un objeto de la clase actividad cuyo atributo id
     * corresponda con el de alguna actividad registrada en el sistema
     * @return un objeto de la clase WR_actividad con la actividad solicitada y
     * el resultado de la operacion
     * @see WR_actividad
     */
    @WebMethod(operationName = "ConsultarActividad")
    public WR_actividad ConsultarActividad(@WebParam(name = "actividadActual") actividad actividadActual) {
        WR_actividad Resultado = new WR_actividad();
        Resultado = myValidador.validarConsultarActividad(actividadActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            actividad intermedio = myActividadFacade.find(actividadActual.getId());
            if (intermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            if (intermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }

            Resultado.ingresarActividad(intermedio);

            /*
             * limitamos la informacion que es retornada
             */
            Resultado.getActividads().get(0).setAuditoriaCollection(null);
            Resultado.getActividads().get(0).setColaDeTarea(null);
            Resultado.getActividads().get(0).setDocumentoCollection(null);
            

            tarea nuevaTarea = new tarea();
            nuevaTarea.setId(Resultado.getActividads().get(0).getIdTarea().getId());
            nuevaTarea.setNombre(Resultado.getActividads().get(0).getIdTarea().getNombre());
            Resultado.getActividads().get(0).setIdTarea(nuevaTarea);

            usuario nuevoUsuario = new usuario();
            nuevoUsuario.setId(Resultado.getActividads().get(0).getIdUsuario().getId());
            nuevoUsuario.setPrimerNombre(Resultado.getActividads().get(0).getIdUsuario().getPrimerNombre());
            nuevoUsuario.setPrimerApellido(Resultado.getActividads().get(0).getIdUsuario().getPrimerApellido());
            Resultado.getActividads().get(0).setIdUsuario(nuevoUsuario);

            usuario nuevoUsuarioOrigen = new usuario();
            nuevoUsuarioOrigen.setId(Resultado.getActividads().get(0).getIdUsuarioOrigen().getId());
            nuevoUsuarioOrigen.setPrimerNombre(Resultado.getActividads().get(0).getIdUsuarioOrigen().getPrimerNombre());
            nuevoUsuarioOrigen.setPrimerApellido(Resultado.getActividads().get(0).getIdUsuarioOrigen().getPrimerApellido());
            Resultado.getActividads().get(0).setIdUsuarioOrigen(nuevoUsuarioOrigen);

            instancia nuevaInstancia = new instancia();
            nuevaInstancia.setId(Resultado.getActividads().get(0).getIdInstancia().getId());
            Resultado.getActividads().get(0).setIdInstancia(nuevaInstancia);
            Resultado.getActividads().get(0).setIdInstancia(nuevaInstancia);

            equivalencia_tiempo nuevaEquivalencia = new equivalencia_tiempo();
            nuevaEquivalencia.setId(Resultado.getActividads().get(0).getIdEquivalenciasTiempo().getId());
            nuevaEquivalencia.setNombre(Resultado.getActividads().get(0).getIdEquivalenciasTiempo().getNombre());
            Resultado.getActividads().get(0).setIdEquivalenciasTiempo(nuevaEquivalencia);

            prioridad nuevaPrioridad = new prioridad();
            nuevaPrioridad.setId(Resultado.getActividads().get(0).getIdPrioridad().getId());
            nuevaPrioridad.setNombre(Resultado.getActividads().get(0).getIdPrioridad().getNombre());
            Resultado.getActividads().get(0).setIdPrioridad(nuevaPrioridad);
            
            


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
     * Obtiene una lista de las actividades asociadas a un usuario en particular
     * y que posean un estado determinado.
     *
     * @param usuarioActual un objeto del tipo usuario cuyo id corresponda al
     * registro cuyas actividades desean ser consultadas
     * @param actividadActual un objeto del tipo actividad que servira para
     * filtrar las actividades encontradas mediante su atributo estado
     * @return un objeto de la clase WR_actividad que poseera la lista de las
     * actividades que cumplan con los requerimientos y el resultado de la
     * operacion
     * @see WR_actividad
     */
    @WebMethod(operationName = "ConsultarActividades")
    public WR_actividad ConsultarActividades(@WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "actividadActual") actividad actividadActual) {
        WR_actividad Resultado;
        Resultado = myValidador.validarConsultarActividads(usuarioActual, actividadActual);
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

            Collection<actividad> intermedio = usuarioActual.getActividadUsuarioCollection();
            String estado = actividadActual.getEstado();
            Iterator iterador = intermedio.iterator();
            instancia instanciaAuxiliar = new instancia();
            usuario usuarioAuxiliar = new usuario();
            usuario usuarioOrigenAuxiliar = new usuario();
            tarea tareaAuxiliar = new tarea();
            while (iterador.hasNext()) {
                actividadActual = (actividad) iterador.next();
                if (actividadActual.getEstado().compareTo(estado) == 0 && !actividadActual.getBorrado()) {
                    actividadActual.setAuditoriaCollection(null);

                    actividadActual.setDuracion(actividadActual.getDuracion());


                    actividadActual.setFechaAsignacion(actividadActual.getFechaAsignacion());
                    actividadActual.setFechaAlerta(actividadActual.getFechaAlerta());
                    actividadActual.setIdEquivalenciasTiempo(null);

                    instanciaAuxiliar=myInstanciaFacade.find(actividadActual.getIdInstancia().getId());
                    actividadActual.setIdInstancia(instanciaAuxiliar);

                    usuarioAuxiliar.setId(actividadActual.getIdUsuario().getId());
                    usuarioAuxiliar.setPrimerApellido(actividadActual.getIdUsuario().getPrimerApellido());
                    usuarioAuxiliar.setPrimerNombre(actividadActual.getIdUsuario().getPrimerNombre());
                    actividadActual.setIdUsuario(usuarioAuxiliar);

                    usuarioOrigenAuxiliar.setId(actividadActual.getIdUsuarioOrigen().getId());
                    usuarioOrigenAuxiliar.setPrimerApellido(actividadActual.getIdUsuarioOrigen().getPrimerApellido());
                    usuarioOrigenAuxiliar.setPrimerNombre(actividadActual.getIdUsuario().getPrimerNombre());
                    actividadActual.setIdUsuarioOrigen(usuarioOrigenAuxiliar);

                    actividadActual.setIdPrioridad(null);

                    tareaAuxiliar.setId(actividadActual.getIdTarea().getId());
                    tareaAuxiliar.setNombre(actividadActual.getIdTarea().getNombre());
                    actividadActual.setIdTarea(tareaAuxiliar);
                    actividadActual.setMaquina(actividadActual.getMaquina());
                    actividadActual.setParametrosEntrada(null);
                    actividadActual.setParametrosSalida(null);
                    actividadActual.getIdInstancia().setIdPeriodoGrupoProceso(actividadActual.getIdInstancia().getIdPeriodoGrupoProceso());

                    Resultado.ingresarActividad(actividadActual);
                }
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getActividads().size() + " Actividades encontradas");
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
     * Obtiene informacion detallada sobre un documento en especifico.
     *
     * @param documentooActual un objeto de la clase documento cuyo atributo id
     * corresponda al del registro que se desea consultar
     * @return un objeto del tipo WR_documento cuya primera posicion en su lista
     * de documentos correspondera con el documento solicitado y que ademas
     * posea el resultado de la operacion
     * @see WR_documento
     */
    @WebMethod(operationName = "ConsultarDocumento")
    public WR_documento ConsultarDocumento(@WebParam(name = "documentoActual") documento documentoActual) {
        WR_documento Resultado = new WR_documento();
        Resultado = myValidador.validarConsultarDocumento(documentoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            documentoActual = myDocumentoFacade.find(documentoActual.getId());


            /*
             * verificamos que se encontro el registro
             */
            if (documentoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            if (documentoActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }

            /*
             * preparamos el envoltorio y retornamos los datos
             */
            Resultado.setEstatus("OK");
            Resultado.ingresarDocumento(documentoActual);



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
     * Obtiene una lista de los documentos asociados a una actividad
     * determinada. Los documentos retornados mediante esta operacion solo
     * contendran informacion basica
     *
     * @param actividadActual un objeto del tipo actividad cuyo id se
     * corresponda con el registro de la actividad cuyos documentos se desean
     * consultar
     * @return un objeto de la clase WR_documento con la lista de los documentos
     * encontrados y el resultado de la operacion
     * @see WR_documento
     */
    @WebMethod(operationName = "ConsultarDocumentos")
    public WR_documento ConsultarDocumentos(@WebParam(name = "actividadActual") actividad actividadActual) {
        WR_documento Resultado = new WR_documento();
        Resultado = myValidador.validarConsultarDocumentos(actividadActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            List<documento> intermedios = myDocumentoFacade.findAll();

            /*
             * Verificamos que se encontraron registros
             */

            if (intermedios == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("No se encontraron registros");
                return Resultado;
            }

            /*
             * Preparamos los objetos
             */
            Iterator iterador = intermedios.iterator();
            documento documentoAuxiliar;
            while (iterador.hasNext()) {
                //Verificamos que el documento no ha sido borrado logicamente y que esta asociada a la actividad ingresada
                documentoAuxiliar = (documento) iterador.next();
                if (!documentoAuxiliar.getBorrado() && actividadActual.getId() == documentoAuxiliar.getIdActividad().getId()) {
                    //limitamos la informacion que es retornada
                    documentoAuxiliar.setFechaCreacion(null);
                    documentoAuxiliar.setFechaDocumento(null);
                    documentoAuxiliar.setObservacion(null);
                    documentoAuxiliar.setRuta(null);
                    documentoAuxiliar.setIdActividad(null);

                    //ingresamos el documento al envoltorio
                    Resultado.ingresarDocumento(documentoAuxiliar);
                }



            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getDocumentos().size() + " documentos encontrados");

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
     * Finaliza una actividad y prepara las siguientes para su 
     * ejecucion. finaliza la actividad y en caso de ser la ultima 
     * actividad no informativa esta operacion tambien finalizara la 
     * instancia
     * <p>Las actividades informativas no pueden ser finalizadas y 
     * seran cerradas cuando la instancia sea cerrada
     *
     * @param actividadActual un objeto de la clase actividad cuyo 
     * atributo id contenga el valor del identificador de la actividad 
     * que sera finalizada y que debera haber sido asignada e iniciada
     * previamente
     * @param sesionActual un objeto de la clase sesion cuyo atributo id se
     * corresponda al de una sesion abierta por el usuario asignado a 
     * la actividad
     * @param condicionActual un objeto de la clase condicion cuyo 
     * atributo id corresponda al de una condicion registrada y asociada
     * a un grupo de transiciones que tengan como tarea de inicio la 
     * tarea asociada a la activida ingresada
     * @return un objeto de la clase WR_resultado que informara del 
     * resultado de la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "FinalizarActividad")
    public WR_resultado FinalizarActividad(@WebParam(name = "actividadActual") actividad actividadActual, @WebParam(name = "sesionActual") sesion sesionActual, @WebParam(name = "condicionActual") condicion condicionActual) {
        WR_resultado Resultado =  new WR_resultado();
       Resultado = myValidador.validarFinalizarActividad(actividadActual, sesionActual,condicionActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
             return Resultado;
        }

        try {
            sesionActual = mySesionFacade.find(sesionActual.getId());
            if (sesionActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion no encontrada");
                return Resultado;
            }
            if (sesionActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion no encontrada");
                return Resultado;
            }
            if(sesionActual.getEstado().compareTo("Abierta")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion invalida");
                return Resultado;
            }
            usuario usuarioActual =  myUsuarioFacade.find(sesionActual.getIdUsuario().getId());
            actividadActual = myActividadFacade.find(actividadActual.getId());
            if(actividadActual.getIdTarea().getTareaInformativa()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Las actividades informativas no pueden ser finalizadas");
                return Resultado;
            }
            if (actividadActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Actividad no encontrada");
                return Resultado;
            }
            if (actividadActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Actividad no encontrada");
                return Resultado;
            }
            if (actividadActual.getEstado().compareTo("abierta") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La actividad no se encuentra abierta");
                return Resultado;
            }
            if (actividadActual.getIdUsuario().getId().compareTo(usuarioActual.getId()) != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario no posee permisos para esta actividad");
                return Resultado;
            }
            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            ArrayList Transiciones = new ArrayList();
            List<Long> TransicionesPreliminares = (List<Long>) em.createNativeQuery("SELECT transicion.id FROM public.tarea, public.transicion, public.actividad WHERE tarea.id = transicion.id_tarea AND actividad.id_tarea = tarea.id AND actividad.id='"+actividadActual.getId()+"';").getResultList();
            Iterator iteradorAuxiliar = TransicionesPreliminares.iterator();
            while(iteradorAuxiliar.hasNext()){
                Transiciones.add(myTransicionFacade.find((Long)iteradorAuxiliar.next()));
            }
            if (Transiciones.isEmpty()) {
                /**
                 * Es una actividad final
                 */
                actividadActual.setEstado("cerrada");
                actividadActual.setFechaCierre(new Date());
                myActividadFacade.edit(actividadActual);
                instancia instanciaActual = myInstanciaFacade.find(actividadActual.getIdInstancia().getId());
                
                
                    Iterator IT = instanciaActual.getActividadCollection().iterator();
                    actividad actividadCerrada;
                    int contadorActividadesAbiertas =0;
                    while(IT.hasNext()){
                        actividadCerrada =(actividad)IT.next();
                        if(actividadCerrada.getEstado().compareTo("cerrada")!=0 && !actividadCerrada.getIdTarea().getTareaInformativa())
                        {
                            contadorActividadesAbiertas++;
                        }
                    }
                if(contadorActividadesAbiertas==0){    
                instanciaActual.setEstado("cerrada");
                instanciaActual.setFechaCierre(new Date());
                    /**
                     * cerramos las actividades para evitar el ingreso a las actividades informativas
                     */
                Iterator iterador4 = instanciaActual.getActividadCollection().iterator();
                while(iterador4.hasNext()){
                    actividadCerrada = (actividad)iterador4.next();
                    actividadCerrada.setEstado("cerrada");
                    myActividadFacade.edit(actividadActual);
                }
                myInstanciaFacade.edit(instanciaActual);
                }
                
                auditoria nuevaAuditoria = new auditoria();
                nuevaAuditoria.setBorrado(false);
                nuevaAuditoria.setFechaAcceso(new Date());
                nuevaAuditoria.setIdActividad(actividadActual);
                nuevaAuditoria.setIdSesion(sesionActual);
                myAuditoriaFacade.create(nuevaAuditoria);
                Resultado.setEstatus("OK");
                return Resultado;
            }


            transicion transicionActual;
            int i =0;
            while (i<Transiciones.size()) {
                    transicionActual = (transicion)Transiciones.get(i);
                if (transicionActual.getIdCondicion().getId() != condicionActual.getId()) {
                    Transiciones.remove(transicionActual);
                   
                }else{
                    i++;
                }
            }

            /**
             * Filtramos las transiciones cuyas actividades de destino no pueden
             * ser abiertas por ANDJOIN
             */
            Iterator iterador2 = Transiciones.iterator();
            transicion transicionAuxiliar;
            Long actividadAuxiliarid;
            while (iterador2.hasNext()) {
                transicionActual = (transicion) iterador2.next();
                if (transicionActual.getIdReglaTransicion().getNombre().compareTo("AND JOIN") == 0) {
                    Iterator iterador3 = transicionActual.getIdTareaDestino().getTransicionCollection().iterator();
                    while (iterador3.hasNext()) {
                        transicionAuxiliar = (transicion) iterador3.next();
                        if (!transicionAuxiliar.equals(transicionActual) && transicionAuxiliar.getCodigo().compareTo(transicionActual.getCodigo()) == 0) {
                            try{
                            actividadAuxiliarid = (Long) em.createNativeQuery("Select actividad.id from actividad where actividad.id_tarea ='" + transicionAuxiliar.getIdTarea().getId() + "' AND actividad.id_instancia= '" + actividadActual.getIdInstancia().getId() + "' ").getSingleResult();
                            if (myActividadFacade.find(actividadAuxiliarid).getEstado().compareTo("cerrada")!=0) {
                                    Transiciones.remove(transicionActual);
                                    iterador2 = Transiciones.iterator();
                                }
                            }catch(javax.persistence.NoResultException e){
                                Transiciones.remove(transicionActual);
                                iterador2 = Transiciones.iterator();
                            }
                        }
                    }
                }

                /**
                 * Filtramos las transiciones cuyas actividades de destino no
                 * pueden ser abiertas por ORJOIN
                 */
                if (transicionActual.getIdReglaTransicion().getNombre().compareTo("JOIN OR") == 0) {
                    Iterator iterador4 = transicionActual.getIdTareaDestino().getTransicionCollection().iterator();
                    while (iterador4.hasNext()) {
                        transicionAuxiliar = (transicion) iterador4.next();
                        if (!transicionAuxiliar.equals(transicionActual) && transicionAuxiliar.getCodigo().compareTo(transicionActual.getCodigo()) == 0) {
                            try{
                            actividadAuxiliarid = (Long) em.createNativeQuery("Select actividad.id from actividad where actividad.id_tarea='" + transicionAuxiliar.getIdTareaDestino().getId() + "' AND actividad.id_instancia='" + actividadActual.getIdInstancia().getId() + "'").getSingleResult();
                            Transiciones.remove(transicionActual);
                            }catch(javax.persistence.NoResultException e){
                                System.out.println("No se consiguio la actividad por lo que no se eliminara la transicion");
                            }
                        }
                    }
                }


            }


            Iterator iterador5 = Transiciones.iterator();
            while (iterador5.hasNext()) {
                transicionAuxiliar = (transicion)iterador5.next();
                actividad nuevaActividad = new actividad();
                nuevaActividad.setDuracion(transicionAuxiliar.getIdTareaDestino().getDuracion());
                nuevaActividad.setEstado("pendiente");
                nuevaActividad.setIdInstancia(actividadActual.getIdInstancia());
                nuevaActividad.setIdPrioridad(transicionAuxiliar.getIdTareaDestino().getIdPrioridad());
                nuevaActividad.setIdTarea(transicionAuxiliar.getIdTareaDestino());
                nuevaActividad.setIdUsuarioOrigen(usuarioActual);
                nuevaActividad.setFechaAsignacion(new Date());
                nuevaActividad.setIdEquivalenciasTiempo(nuevaActividad.getIdTarea().getIdEquivalenciaTiempo());
                myActividadFacade.create(nuevaActividad);
                /**
                 * Hay que asignar el usuario
                 */
                Actividad ActividadActual = new Actividad();
                ActividadActual.setId(nuevaActividad.getId());
                Politica nuevaPolitica = new Politica();
                nuevaPolitica.setNombre(nuevaActividad.getIdTarea().getIdPolitica().getNombre());
               WrResultado ResultadoPreliminar = this.aplicarPolitica(ActividadActual,nuevaPolitica);
               if(ResultadoPreliminar.getEstatus().compareTo("FAIL")==0){
                   Resultado.setObservacion("Una o varias de las nuevas actividades no pudieron ser asignadas");
               }
               
            }
            
            actividadActual.setEstado("cerrada");
            actividadActual.setFechaCierre(new Date());
            myActividadFacade.edit(actividadActual);
            Resultado.setEstatus("OK");
            /**
             * AQUI VAS!!
             */
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
     * Inicia una actividad. una actividad solo podra ser iniciada por
     * el usuario que ha sido asignado para su ejecucion
     * 
     * @param actividadActual un objeto de la clase actividad cuyo atributo id
     * contenga el valor del identificador de la actividad que se desea
     * iniciar
     * @param sesionActual un objeto de la clase sesion abierta cuyo 
     * atributo id se corresponda al del usuario registrado que desea 
     * iniciar la actividad
     * @return un objeto de la clase WR_resultado que informara del resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "IniciarActividad")
    public WR_resultado IniciarActividad(@WebParam(name = "actividadActual") actividad actividadActual, @WebParam(name = "sesionActual") sesion sesionActual) {
        WR_resultado Resultado;
        Resultado = myValidador.validarIniciarActividad(actividadActual,sesionActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            actividad intermedio = myActividadFacade.find(actividadActual.getId());
            

            /*
             * verificamos que se encontro el registro
             */
            
            if (intermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            sesionActual= mySesionFacade.find(sesionActual.getId());
            if(sesionActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion no encontrada");
                return Resultado;
            }
            if(sesionActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion no encontrada");
                return Resultado;
            }
            if(sesionActual.getEstado().compareTo("Abierta")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La sesion no es valida");
                return Resultado;
            }
            usuario usuarioActual = myUsuarioFacade.find(sesionActual.getIdUsuario().getId());
            if(usuarioActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getEstado().compareTo("activo")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario ha sido desactivado");
                return Resultado;
            }
            if(!intermedio.getIdUsuario().equals(usuarioActual)){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Solo el usuario asignado puede iniciar la actividad");
            }
            /**
             * La instancia fue borrada
             */
            if(intermedio.getIdInstancia().getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            
            /**
             * La instancia fue cerrada de manera manual
             */
            if(intermedio.getIdInstancia().getEstado().compareTo("cerrada")==0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia cerrada");
                return Resultado;
            }
            
            if (intermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            /*
             * Verificamos el estado anterior de la actividad
             */
            if (intermedio.getEstado().compareTo("pendiente") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Una actividad solo puede ser abierta si se encuentra pendiente");
                return Resultado;
            }
            intermedio.setEstado("abierta");
            intermedio.setFechaApertura(new Date());
            myActividadFacade.edit(intermedio);
            Resultado.setEstatus("Ok");

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
     * Asigna de manera manual una actividad a un usuario.
     *
     * @param actividadActual un objeto de la clase actividad cuyo atributo id
     * contenga el valor del identificador de la actividad que sera asignada
     * @param usuarioActual un objeto de la clase usuario cuyo atributo id se
     * corresponda al del usuario registrado que se desea asignar
     * @return un objeto de la clase WR_resultado que informara del resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "AsignarActividad")
    public WR_resultado AsignarActividad(@WebParam(name = "actividadActual") actividad actividadActual, @WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_resultado Resultado;
        WR_resultado Resultado2;
        Resultado = myValidador.validarAsignarActividad(actividadActual, usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            actividad actividadIntermedia = myActividadFacade.find(actividadActual.getId());
            usuario usuarioIntermedio = myUsuarioFacade.find(usuarioActual.getId());
            if (actividadIntermedia == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setEstatus("Actividad no encontrada");
                return Resultado;
            }
            if (usuarioIntermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setEstatus("Usuario no encontrado");
                return Resultado;
            }
            if (actividadIntermedia.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setEstatus("Actividad no encontrada");
                return Resultado;
            }
            if (usuarioIntermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setEstatus("Usuario no encontrado");
                return Resultado;
            }

            actividadIntermedia.setIdUsuario(usuarioIntermedio);
           myActividadFacade.edit(actividadIntermedia);
       
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
     * libera una actividad pendiente o abierta de un usuario.
     *
     * @param actividadActual un objeto de la clase actividad cuyo atributo id
     * contenga el valor del identificador de la actividad que sera liberada
     * @param usuarioActual un objeto de la clase usuario cuyo atributo id se
     * corresponda al del usuario registrado que se desea liberar la actividad
     * @return un objeto de la clase WR_resultado que informara del resultado de
     * la operacion
     * @see WR_resultado
     */
    
    
     @WebMethod(operationName = "liberarActividad")
    public WR_resultado liberarActividad(@WebParam(name = "actividadLiberar") actividad actividadLiberar, @WebParam(name = "usuarioLiberar") usuario usuarioLiberar) {
        WR_resultado Resultado;
        Resultado = myValidador.validarAsignarActividad(actividadLiberar, usuarioLiberar);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            actividad intermedio = myActividadFacade.find(actividadLiberar.getId());
            

            /*
             * verificamos que se encontro el registro
             */
            
            if (intermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
           
             usuario usuarioActual = myUsuarioFacade.find(actividadLiberar.getId());
            if(usuarioActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getEstado().compareTo("activo")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario ha sido desactivado");
                return Resultado;
            }
            
            /**
             * La instancia fue borrada
             */
            if(intermedio.getIdInstancia().getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            
            /**
             * La instancia fue cerrada de manera manual
             */
            if(intermedio.getIdInstancia().getEstado().compareTo("cerrada")==0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia cerrada");
                return Resultado;
            }
            
            if (intermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            /*
             * Verificamos el estado anterior de la actividad
             */
            if (intermedio.getEstado().compareTo("cerrada") == 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("la actividad debe estar abierta o pendiente");
                return Resultado;
            }
            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            
            
            
            intermedio.setEstado("pendiente");
            intermedio.setFechaApertura(null);
            intermedio.setFechaAsignacion(null);
            intermedio.setIdUsuario(null);
            myActividadFacade.edit(intermedio);
            cola_de_tarea cola=new cola_de_tarea();
            cola.setIdGrupo(intermedio.getIdInstancia().getIdPeriodoGrupoProceso().getIdGrupo());
            cola.setIdTarea(intermedio.getIdTarea());
            cola.setIdActividad(intermedio);
            myColaDeTareaFacade.create(cola);
            
            Resultado.setEstatus("OK");
            
           // em.createQuery("UPDATE actividad SET  estado='pendiente', id_usuario=null, fecha_apertura=null WHERE id='"+intermedio.getId()+"'");
             

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
     * Libera todas las actividades pendientes y abiertas de un usuario.
     *
     * @param usuarioActual un objeto de la clase usuario cuyo atributo id se
     * corresponda al del usuario registrado que se desea liberar todas la actividas abiertas y pendientes
     * @return un objeto de la clase WR_resultado que informara del resultado de
     * la operacion
     * @see WR_resultado
     */
      @WebMethod(operationName = "liberarActividades")
    public WR_resultado liberarActividades(@WebParam(name = "usuarioLiberar") usuario usuarioLiberar) {
        WR_actividad Resultad;
        WR_resultado Resultado=new WR_resultado();
        Resultad = myValidador.validarConsultarActividadesCola(usuarioLiberar);
        if (Resultad.getEstatus().compareTo("OK") != 0) {
            Resultado.setEstatus(Resultad.getEstatus());
            Resultado.setObservacion(Resultad.getObservacion());
            return Resultado;
        }
        
        try {
             usuario usuarioActual = myUsuarioFacade.find(usuarioLiberar.getId());
             if(usuarioActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getEstado().compareTo("activo")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario ha sido desactivado");
                return Resultado;
            }
         int j=0;  
        activi = new actividad();
        activi.setEstado("abierta");
        actividad = ConsultarActividades(usuarioActual, activi);
         
        if (!actividad.getActividads().isEmpty()) {
            
      
        while (actividad.getActividads().size() > j) {
           
            actividad intermedio = myActividadFacade.find(actividad.getActividads().get(j).getId());
            intermedio.setFechaApertura(null);
            intermedio.setFechaAsignacion(null);
            intermedio.setIdUsuario(null);
            intermedio.setEstado("pendiente");
            myActividadFacade.edit(intermedio);
            cola_de_tarea cola=new cola_de_tarea();
            cola.setIdGrupo(actividad.getActividads().get(j).getIdInstancia().getIdPeriodoGrupoProceso().getIdGrupo());
            cola.setIdTarea(actividad.getActividads().get(j).getIdTarea());
            cola.setIdActividad(actividad.getActividads().get(j));
            myColaDeTareaFacade.create(cola);

            j++;
        }
          }
        j=0;
        activi.setEstado("pendiente");
        actividad = ConsultarActividades(usuarioActual, activi);
        if (!actividad.getActividads().isEmpty()) {
            
        
        while (actividad.getActividads().size() > j) {
            
           
            actividad intermedio = myActividadFacade.find(actividad.getActividads().get(j).getId());
            intermedio.setFechaApertura(null);
            intermedio.setFechaAsignacion(null);
            intermedio.setIdUsuario(null);
            intermedio.setEstado("pendiente");
            myActividadFacade.edit(intermedio);
            cola_de_tarea cola=new cola_de_tarea();
             cola.setIdGrupo(actividad.getActividads().get(j).getIdInstancia().getIdPeriodoGrupoProceso().getIdGrupo());
            cola.setIdTarea(actividad.getActividads().get(j).getIdTarea());
            cola.setIdActividad(actividad.getActividads().get(j));
            myColaDeTareaFacade.create(cola);
            j++;
        }
       }
             
               
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
     * Registra el comportamiento del usuario dentro del sistema.
     *
     * @param actividadActual un objeto de la clase actividad cuyo atributo id
     * contenga el valor del identificador de la actividad que a la que esta
     * asociada el registro
     * @param sesionActual un objeto de la clase sesion cuyo atributo id se
     * corresponda al de la sesion en la que se llevo a cabo la actividad
     * @return un objeto de la clase WR_resultado que informara del resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "IngresarActividad")
    public WR_resultado IngresarActividad(@WebParam(name = "actividadActual") actividad actividadActual, @WebParam(name = "sesionActual") sesion sesionActual) {
        WR_resultado Resultado;
        Resultado = myValidador.validarIngresarAuditoria(sesionActual, actividadActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            actividadActual = myActividadFacade.find(actividadActual.getId());
            if (actividadActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La actividad no fue encontrada o ha sido cerrada");
                return Resultado;
            }
            if (actividadActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La actividad no pudo ser encontrada");
            }
            if (actividadActual.getEstado().compareTo("abierta") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La actividad no fue encontrada o ha sido cerrada");
                return Resultado;
            }
            sesionActual = mySesionFacade.find(sesionActual.getId());
            if (sesionActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La sesion no pudo ser encontrada o ya ha sido cerrada");
                return Resultado;
            }
            if (sesionActual.getEstado().compareTo("activo") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La sesion no pudo ser encontrada o ya ha sido cerrada");
                return Resultado;
            }
            auditoria nuevaAuditoria = new auditoria();
            nuevaAuditoria.setBorrado(false);
            nuevaAuditoria.setFechaAcceso(new Date());
            nuevaAuditoria.setIdActividad(actividadActual);
            nuevaAuditoria.setIdSesion(sesionActual);
            myAuditoriaFacade.create(nuevaAuditoria);
            Resultado.setEstatus("Ok");


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
     * Retorna la lista de las actividades en cola que pueden ser consumidas
     * por un usuario determinado.
     * 
     * @param usuarioActual un objeto de la clase usuario cuyo atributo id se
     * corresponda al de un usuario regitrado
     * @return un objeto de la clase WR_actividad que informara del
     * resultado de la operacion y que contendra la lista de actividades
     * encontradas
     * @see WR_actividad
     */
    @WebMethod(operationName = "ConsultarActividadesCola")
    public WR_actividad ConsultarActividadesCola(@WebParam(name = "usuarioActual") usuario usuarioActual) {
         WR_actividad Resultado;
        Resultado = myValidador.validarConsultarActividadesCola(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if(usuarioActual == null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getEstado().compareTo("activo")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario ha sido desactivado");
                return Resultado;
            }
            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            
            List<Long> resultadosPreliminares = (List<Long>)em.createNativeQuery("select distinct actividad.id from actividad, cola_de_tarea, grupo, usuario_grupo_rol where actividad.id = cola_de_tarea.id_actividad and cola_de_tarea.id_grupo = grupo.id and usuario_grupo_rol.id_grupo = grupo.id and usuario_grupo_rol.id_usuario = '"+usuarioActual.getId()+"'").getResultList();
            if(resultadosPreliminares.isEmpty()){
                Resultado.setEstatus("OK");
                Resultado.setObservacion("No se encontraron actividades");
                return Resultado;
            }
            Iterator iterador = resultadosPreliminares.iterator();
            actividad actividadAuxiliar;
            actividad nuevaActividad;
            while(iterador.hasNext()){
                nuevaActividad = new actividad();
                actividadAuxiliar = myActividadFacade.find((Long)iterador.next());
                nuevaActividad.setId(actividadAuxiliar.getId());
                nuevaActividad.setIdTarea(new tarea(actividadAuxiliar.getIdTarea().getId(), actividadAuxiliar.getIdTarea().getNombre(), null, null, null, null, Double.MIN_VALUE, null, true,actividadAuxiliar.getIdTarea().getTareaInicial(), actividadAuxiliar.getIdTarea().getTareaInformativa()));
                nuevaActividad.setIdUsuarioOrigen(new usuario(actividadAuxiliar.getIdUsuarioOrigen().getId(), null,actividadAuxiliar.getIdUsuarioOrigen().getPrimerNombre(), actividadAuxiliar.getIdUsuarioOrigen().getPrimerApellido(), null, null, null, null, null, null, Integer.MIN_VALUE, false));
                nuevaActividad.setIdInstancia(new instancia(actividadAuxiliar.getIdInstancia().getId()));
                Resultado.ingresarActividad(nuevaActividad);
            }
            Resultado.setEstatus("Ok");
            Resultado.setObservacion(Resultado.getActividads().size()+" actividades encontradas");


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
     * Saca una actividad de la cola y la asigna al usuario ingresado.
     * una actividad solo puede sacarse de la cola y asignarse a un
     * usuario que pertenesca al grupo de trabajo propietario de la 
     * cola
     * 
     * @param actividadActual objeto de la clase actividad cuyo 
     * identificador debe corresponder al de una actividad en cola
     * @param usuarioActual objeto de la clase usuario cuyo id debe 
     * corresponder a un registro
     * @return un objeto de la clase WR_resultado que informara del
     * resultado de la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "ConsumirCola")
    public WR_resultado ConsumirCola(@WebParam(name = "actividadActual") actividad actividadActual, @WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_resultado Resultado;
        Resultado = myValidador.validarConsumirCola(actividadActual,usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if(usuarioActual == null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getEstado().compareTo("activo")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario ha sido desactivado");
                return Resultado;
            }
            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            Long colaDeTareaActual;
            try{
                colaDeTareaActual = (Long)em.createNativeQuery("select distinct cola_de_tarea.id from actividad, cola_de_tarea, grupo, usuario_grupo_rol where actividad.id = cola_de_tarea.id_actividad and cola_de_tarea.id_grupo = grupo.id and usuario_grupo_rol.id_grupo = grupo.id and usuario_grupo_rol.id_usuario = '"+usuarioActual.getId()+"' and actividad.id = '"+actividadActual.getId()+"'").getSingleResult();
            }catch(javax.persistence.NoResultException e){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La actividad no existe o no pertenece a una cola de tareas asociada al usuario");
                return Resultado;
            }
            actividadActual = myActividadFacade.find(actividadActual.getId());
            actividadActual.setIdUsuario(usuarioActual);
            myActividadFacade.edit(actividadActual);
            myColaDeTareaFacade.remove(myColaDeTareaFacade.find(colaDeTareaActual));
            Resultado.setEstatus("Ok");


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
     * busca los estados de todas las actividades.
     *
     * @return una lista de los estados de las actividades
     *
     */
    
    @WebMethod(operationName = "buscarEstados")
    public List<String> buscarEstados() {
        
        return actividadFacade.buscarestados();
       
    }
    
     @WebMethod(operationName = "condicionesTransiciones")
    public Collection<condicion> condicionesTransiciones(@WebParam(name = "actividadn") actividad act) {
         
         actividad acti= actividadFacade.find(act.getId());
         Collection<transicion> listatras;
         Collection<condicion> listacon =new ArrayList<condicion>();
         listatras= acti.getIdTarea().getTransicionCollection1();
        for (transicion tr : listatras) {
            listacon.add(tr.getIdCondicion());
        } 
        
        return listacon;
       
    }
    
    /** 
     * Método cambia de abierta a pendiente
     * 
     * @param actividadActual un objeto de la clase actividad cuyo atributo id
     * contenga el valor del identificador de la actividad que sera liberada
     * @param sesionActual un objeto de la clase sesion cuyo atributo id se
     * corresponda a la sesesion actual y el id_usuario que corresponde al id del 
     * usuario registrado que se desea pasar la actividad de abierta a pendiente
     * @return un objeto de la clase WR_resultado que informara del resultado de
     * la operacion
     * @see WR_resultado
     */ 
     @WebMethod(operationName = "pendienteActividad")
    public WR_resultado pendienteActividad(@WebParam(name = "actividadActual") actividad actividadActual, @WebParam(name = "sesionActual") sesion sesionActual) {
        WR_resultado Resultado;
        Resultado = myValidador.validarIniciarActividad(actividadActual,sesionActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            actividad intermedio = myActividadFacade.find(actividadActual.getId());
            

            /*
             * verificamos que se encontro el registro
             */
            
            if (intermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            sesionActual= mySesionFacade.find(sesionActual.getId());
            if(sesionActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion no encontrada");
                return Resultado;
            }
            if(sesionActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion no encontrada");
                return Resultado;
            }
            if(sesionActual.getEstado().compareTo("Abierta")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La sesion no es valida");
                return Resultado;
            }
            usuario usuarioActual = myUsuarioFacade.find(sesionActual.getIdUsuario().getId());
            if(usuarioActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Usuario no encontrado");
                return Resultado;
            }
            if(usuarioActual.getEstado().compareTo("activo")!=0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario ha sido desactivado");
                return Resultado;
            }
            if(!intermedio.getIdUsuario().equals(usuarioActual)){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Solo el usuario asignado puede iniciar la actividad");
            }
            /**
             * La instancia fue borrada
             */
            if(intermedio.getIdInstancia().getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            
            /**
             * La instancia fue cerrada de manera manual
             */
            if(intermedio.getIdInstancia().getEstado().compareTo("cerrada")==0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia cerrada");
                return Resultado;
            }
            
            if (intermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            /*
             * Verificamos el estado anterior de la actividad
             */
            if (intermedio.getEstado().compareTo("abierta") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Una actividad solo puede pasar a pendiente si se encuentra abierta");
                return Resultado;
            }
            intermedio.setEstado("pendiente");
            intermedio.setFechaApertura(new Date());
            myActividadFacade.edit(intermedio);
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
     * Método que lista las actividades que estan con estado pendiente y que no hayan sido borradas
     */
    @WebMethod(operationName = "listarActividades")
    public List<actividad> listarActividades(@WebParam(name = "estado") String estado, @WebParam(name = "borrado") boolean borradoo) {
        
        return actividadFacade.listarActividades(estado, borradoo);
       
    }

    private WrResultado aplicarPolitica(com.seguroshorizonte.capadeservicios.clienteweb.Actividad actividadActual, com.seguroshorizonte.capadeservicios.clienteweb.Politica politicaActual) {
        com.seguroshorizonte.capadeservicios.clienteweb.AplicarPolitica port = service.getAplicarPoliticaPort();
        return port.aplicarPolitica(actividadActual, politicaActual);
    }
    
   
    
}
