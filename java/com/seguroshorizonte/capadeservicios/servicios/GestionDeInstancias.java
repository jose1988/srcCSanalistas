/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.actividadFacade;
import com.seguroshorizonte.capadeservicios.beans.grupoFacade;
import com.seguroshorizonte.capadeservicios.beans.instanciaFacade;
import com.seguroshorizonte.capadeservicios.beans.periodoFacade;
import com.seguroshorizonte.capadeservicios.beans.periodo_grupo_procesoFacade;
import com.seguroshorizonte.capadeservicios.beans.procesoFacade;
import com.seguroshorizonte.capadeservicios.beans.sesionFacade;
import com.seguroshorizonte.capadeservicios.beans.tareaFacade;
import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.clienteweb.Actividad;
import com.seguroshorizonte.capadeservicios.clienteweb.Politica;
import com.seguroshorizonte.capadeservicios.clienteweb.AplicarPolitica_Service;
import com.seguroshorizonte.capadeservicios.clienteweb.WrResultado;
import com.seguroshorizonte.capadeservicios.clienteweb.WrUsuario;
import com.seguroshorizonte.capadeservicios.entidades.actividad;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_proceso;
import com.seguroshorizonte.capadeservicios.entidades.equivalencia_tiempo;
import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.instancia;
import com.seguroshorizonte.capadeservicios.entidades.periodo;
import com.seguroshorizonte.capadeservicios.entidades.periodo_grupo_proceso;
import com.seguroshorizonte.capadeservicios.entidades.prioridad;
import com.seguroshorizonte.capadeservicios.entidades.proceso;
import com.seguroshorizonte.capadeservicios.entidades.sesion;
import com.seguroshorizonte.capadeservicios.entidades.tarea;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_actividad;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_instancia;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_periodo;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_proceso;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_tarea;
import com.seguroshorizonte.capadeservicios.validadores.GestionDeInstanciasValidador;
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
import javax.xml.ws.WebServiceRef;

/**
 * Esta clase posee las operaciones necesarias para la gestion de las instancias
 * y las clases relacionadas
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
@EJB(name = "GestionDeInstancias", beanInterface = Local.class)
@WebService(serviceName = "GestionDeInstancias")
public class GestionDeInstancias {
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
    GestionDeInstanciasValidador myValidador = new GestionDeInstanciasValidador();
    @EJB
    instanciaFacade myInstanciaFacade = new instanciaFacade();
    @EJB
    periodoFacade myPeriodoFacade = new periodoFacade();
    @EJB
    procesoFacade myProcesoFacade = new procesoFacade();
    @EJB
    usuarioFacade myUsuarioFacade = new usuarioFacade();
    @EJB
    periodo_grupo_procesoFacade myperiodoGrupoProcesoFacade = new periodo_grupo_procesoFacade();
    @EJB
    grupoFacade myGrupoFacade = new grupoFacade();
    @EJB
    tareaFacade myTareaFacade = new tareaFacade();
    @EJB
    actividadFacade myActividadFacade = new actividadFacade();
    @EJB
    sesionFacade mySesionFacade = new sesionFacade();

    /**
     * Cierra de manera manual una instancia determinada. Cuando una instancia
     * es cerrada por este metodo los usuarios involucrados en la ejecucion de
     * las actividades asociadas dejaran de tener acceso a ellas y no se podra
     * avanzar en el proceso.
     *
     * @param instanciaActual objeto de la clase instancia cuyo id debera
     * corresponder con el identificador de la actividad que se desea cerrar
     * @param sesionActual objeto de la clase sesion cuyo id debera corresponder
     * al de una sesion registrada y abierta, la sesion ingresada debe estar
     * asociada al usuario creador de la instancia para que esta pueda ser
     * cerrada
     * @return objeto del tipo WR_resultado que informara sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "CerrarInstancia")
    public WR_resultado CerrarInstancia(@WebParam(name = "instanciaActual") instancia instanciaActual, @WebParam(name = "sesionActual") sesion sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarCerrarInstacia(instanciaActual, sesionActual);
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
            if (sesionActual.getEstado().compareTo("cerrada") == 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La sesion ingresada ha sido cerrada con anterioridad");
                return Resultado;
            }

            usuario usuarioActual = sesionActual.getIdUsuario();

            instanciaActual = myInstanciaFacade.find(instanciaActual.getId());
            if (instanciaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia no encontrada");
                return Resultado;
            }
            if (instanciaActual.getEstado().compareTo("abierta") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La instancia ingresada no se encuentra abierta");
                return Resultado;
            }

            if (!instanciaActual.getIdUsuario().equals(usuarioActual)) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El usuario asociado a la sesion ingresada no posee la permisologia necesaria");
                return Resultado;
            }
            instanciaActual.setEstado("cerrada");
            myInstanciaFacade.edit(instanciaActual);
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
     * Retorna informacion detallada sobre una instancia determinada.
     *
     * @param instanciaActual objeto de la clase instancia cuyo id debera
     * corresponder con el identificador del registro que se desea consultar
     * @return objeto del tipo WR_instancia que informara sobre el resultado de
     * la operacion y contiene la instancia deseada en la primera posicion de su
     * lista de instancias
     * @see WR_instancia
     */
    @WebMethod(operationName = "ConsultarInstancia")
    public WR_instancia ConsultarInstancia(@WebParam(name = "instanciaActual") instancia instanciaActual) {
        WR_instancia Resultado = new WR_instancia();
        Resultado = myValidador.validarConsultarInstancia(instanciaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            instanciaActual = myInstanciaFacade.find(instanciaActual.getId());
            if (instanciaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia no encontrada");
                return Resultado;
            }
            if (instanciaActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia no encontrada");
                return Resultado;
            }

            //instanciaActual.setActividadCollection(null);
            //instanciaActual.setId(null);

            usuario usuarioAuxiliar = new usuario();
            usuarioAuxiliar.setId(instanciaActual.getIdUsuario().getId());
            instanciaActual.setIdUsuario(usuarioAuxiliar);

            periodo_grupo_proceso periodoGrupoProcesoAuxiliar = new periodo_grupo_proceso();

            periodo periodoAuxiliar = new periodo();
            periodoAuxiliar = instanciaActual.getIdPeriodoGrupoProceso().getIdPeriodo();
            periodoGrupoProcesoAuxiliar.setIdPeriodo(periodoAuxiliar);

            grupo grupoAuxiliar = new grupo();
            grupoAuxiliar = instanciaActual.getIdPeriodoGrupoProceso().getIdGrupo();
            periodoGrupoProcesoAuxiliar.setIdGrupo(grupoAuxiliar);

            proceso procesoAuxiliar = new proceso();
            procesoAuxiliar = instanciaActual.getIdPeriodoGrupoProceso().getIdProceso();
            periodoGrupoProcesoAuxiliar.setIdProceso(procesoAuxiliar);

            instanciaActual.setIdPeriodoGrupoProceso(periodoGrupoProcesoAuxiliar);

            Resultado.setEstatus("OK");
            Resultado.ingresarInstancia(instanciaActual);

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
     * Retorna las instancias asociadas al usuario filtradas por estado. Esta
     * operacion obtiene aquellas instancias que han sido ingresadas por el
     * usuario ingresado y que poseen el estado de la instancia ingresada
     *
     * @param usuarioActual objeto del tipo usuario cuyo id corresponde al
     * identificadoor del usuario asociado a las instancias que se desea obtener
     * @param instanciaActual objeto del tipo instancia cuyo atributo estado
     * servira para filtrar las instancias que seran retornadas
     * @return objeto del tipo WR_instancia que informa sobre el resultado de la
     * operacion y posee la lista de instancias encontradas
     * @see WR_instancia
     */
    @WebMethod(operationName = "ConsultarInstancias")
    public WR_instancia ConsultarInstancias(@WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "instanciaActual") instancia instanciaActual) {
        WR_instancia Resultado = new WR_instancia();
        Resultado = myValidador.validarConsultarInstancias(instanciaActual, usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            Collection<instancia> instancias = myInstanciaFacade.findAll();
            Iterator iterador = instancias.iterator();
            instancia instanciaAuxiliar;



            while (iterador.hasNext()) {
                instanciaAuxiliar = (instancia) iterador.next();
                if (!instanciaAuxiliar.getBorrado() && instanciaAuxiliar.getIdUsuario().getId().compareTo(usuarioActual.getId()) == 0 && instanciaAuxiliar.getEstado().compareTo(instanciaActual.getEstado()) == 0) {

                    periodo_grupo_proceso periodoGrupoProcesoAuxiliar = new periodo_grupo_proceso();
                    proceso procesoAuxiliar = new proceso();
                    periodo periodoAuxiliar = new periodo();
                    grupo grupoAuxiliar = new grupo();
                    periodoAuxiliar = instanciaAuxiliar.getIdPeriodoGrupoProceso().getIdPeriodo();
                    periodoGrupoProcesoAuxiliar.setIdPeriodo(periodoAuxiliar);
                    procesoAuxiliar = instanciaAuxiliar.getIdPeriodoGrupoProceso().getIdProceso();
                    periodoGrupoProcesoAuxiliar.setIdProceso(procesoAuxiliar);
                    grupoAuxiliar = instanciaAuxiliar.getIdPeriodoGrupoProceso().getIdGrupo();
                    periodoGrupoProcesoAuxiliar.setIdGrupo(grupoAuxiliar);

                    periodoGrupoProcesoAuxiliar.getIdPeriodo().setPeriodogrupoprocesoCollection(null);

                    instanciaAuxiliar.setIdPeriodoGrupoProceso(periodoGrupoProcesoAuxiliar);

                    Resultado.ingresarInstancia(instanciaAuxiliar);
                }
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getInstancias().size() + " instancias encontradas");

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
     * Retorna informacion detallada sobre un periodo en especifico.
     *
     * @param periodoActual objeto de la clase periodo cuyo id debe
     * corresponderse con el identificador del registro que se desea consultar
     * @return objeto de la clase WR_periodo que informa del resultado de la
     * operacion y contiene el periodo solicitado en la primera posicion de su
     * lista de periodos
     * @see WR_periodo
     */
    @WebMethod(operationName = "ConsultarPeriodo")
    public WR_periodo ConsultarPeriodo(@WebParam(name = "periodoActual") periodo periodoActual) {
        WR_periodo Resultado = new WR_periodo();
        Resultado = myValidador.validarConsultarPeriodo(periodoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            periodoActual = myPeriodoFacade.find(periodoActual.getId());
            if (periodoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Periodo no encontrado");
                return Resultado;
            }
            if (periodoActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Periodo no encontrado");
                return Resultado;
            }

            periodoActual.setPeriodogrupoprocesoCollection(null);
            periodoActual.setEstado(null);

            Resultado.setEstatus("OK");
            Resultado.ingresarPeriodo(periodoActual);

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
     * Retorna los periodos disponibles para crear una instancia de un proceso
     * determinado
     *
     * @param procesoActual objeto de la clase proceso cuyo id corresponde al
     * del identificador del proceso para el que se buscan periodos disponibles
     * @return objeto de la clase WR_periodo que informa del resultado de la
     * operacion y posee la lista de los periodos disponibles que cumplen con
     * las limitantes
     * @see WR_periodo
     */
    @WebMethod(operationName = "ConsultarPeriodosDisponibles")
    public WR_periodo ConsultarPeriodosDisponibles(@WebParam(name = "procesoActual") proceso procesoActual) {
        WR_periodo Resultado = new WR_periodo();
        Resultado = myValidador.validarConsultarPeriodosDisponibles(procesoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            procesoActual = myProcesoFacade.find(procesoActual.getId());
            if (procesoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Proceso no encontrado");
                return Resultado;
            }
            if (procesoActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Proceso no encontrado");
                return Resultado;
            }

            Iterator iterador = procesoActual.getPeriodogrupoprocesoCollection().iterator();
            periodo_grupo_proceso periodoGrupoProcesoAuxiliar;
            while (iterador.hasNext()) {
                periodoGrupoProcesoAuxiliar = (periodo_grupo_proceso) iterador.next();
                if (!Resultado.getPeriodos().contains(periodoGrupoProcesoAuxiliar.getIdPeriodo())) {
                    periodoGrupoProcesoAuxiliar.getIdPeriodo().setFechaDesde(null);
                    periodoGrupoProcesoAuxiliar.getIdPeriodo().setFechaHasta(null);
                    periodoGrupoProcesoAuxiliar.getIdPeriodo().setPeriodogrupoprocesoCollection(null);
                    Resultado.ingresarPeriodo(periodoGrupoProcesoAuxiliar.getIdPeriodo());
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
     * Retorna informacion detallada de un proceso en especifico.
     *
     * @param procesoActual objeto del tipo proceso cuyo id corresponde con el
     * registro que se desea consultar
     * @return objeto de la clase WR_proceso que informa del resultado de la
     * operacion y contiene en la primera posicion de su lista de procesos al
     * proceso deseado
     * @see WR_proceso
     */
    @WebMethod(operationName = "ConsultarProceso")
    public WR_proceso ConsultarProceso(@WebParam(name = "procesoActual") proceso procesoActual) {
        WR_proceso Resultado = new WR_proceso();
        Resultado = myValidador.validarConsultarProceso(procesoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            procesoActual = myProcesoFacade.find(procesoActual.getId());
            if (procesoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Proceso no encontrado");
                return Resultado;
            }
            if (procesoActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Proceso no encontrado");
                return Resultado;
            }
            procesoActual.setAutor(null);
            procesoActual.setTareaCollection(null);

            clasificacion_proceso clasificacionAuxiliar = new clasificacion_proceso();
            clasificacionAuxiliar.setId(procesoActual.getIdClasificacionProceso().getId());
            procesoActual.setIdClasificacionProceso(clasificacionAuxiliar);

            prioridad prioridadAuxiliar = new prioridad();
            prioridadAuxiliar.setId(procesoActual.getIdPrioridad().getId());
            procesoActual.setIdPrioridad(prioridadAuxiliar);

            equivalencia_tiempo equivalenciaTiempoAuxiliar = new equivalencia_tiempo();
            equivalenciaTiempoAuxiliar.setId(procesoActual.getIdEquivalenciasTiempo().getId());
            procesoActual.setIdEquivalenciasTiempo(equivalenciaTiempoAuxiliar);

            Resultado.setEstatus("OK");
            Resultado.ingresarProceso(procesoActual);

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
     * Retorna una lista de los procesos que pueden ser usados por el usuario
     * para crear una nueva instancia.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id corresponde con
     * el del registro que desea ser consultado
     * @param periodoActual objeto de la clase periodo cuyo id corresponde al de
     * un registro que ayudara a filtrar los procesos disponibles
     * @return objeto de la clase WR_proceso que informa el resultado de la
     * operacion y posee la lista de los procesos que pueden ser instanciados
     * por el usuario
     * @see WR_proceso
     */
    @WebMethod(operationName = "ConsultarProcesosDisponibles")
    public WR_proceso ConsultarProcesosDisponibles(@WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "periodoActual") periodo periodoActual) {
        WR_proceso Resultado = new WR_proceso();
        Resultado = myValidador.validarConsultarProcesosDisponibles(usuarioActual, periodoActual);


        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {

            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            List<Long> procesos = (List<Long>) em.createNativeQuery("SELECT   DISTINCT proceso.id FROM   public.proceso,   public.periodo,   public.periodo_grupo_proceso,   public.grupo,   public.usuario_grupo_rol,   public.usuario WHERE   periodo.id = '" + periodoActual.getId() + "' AND   periodo_grupo_proceso.id_periodo = periodo.id AND   periodo_grupo_proceso.id_proceso = proceso.id AND   periodo_grupo_proceso.id_grupo = grupo.id AND   usuario_grupo_rol.id_grupo = grupo.id AND   usuario_grupo_rol.id_usuario = usuario.id AND   usuario.id = '" + usuarioActual.getId() + "';").getResultList();
            Iterator iterador = procesos.iterator();
            while (iterador.hasNext()) {
                Resultado.ingresarProceso(new proceso((Long) iterador.next()));
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getProcesos().size() + " procesos encontrados");
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
     * Crea una nueva instancia de algun proceso. para crear una nueva instancia
     * de algun proceso el objeto.
     * <p>los objetos introducidos solo necesitan poseer un identificador valido
     *
     * @param sesionActual objeto de la clase sesion asociado al usuario que
     * desea crear la instancia
     * @param periodoActual objeto de la clase periodo que define el lapso de
     * tiempo en el que se desea ejecutar la instancia
     * @param grupoActual grupo de usuarios que sera asignado al proceso para la
     * ejecucion
     * @param proceso objeto de la clase que describe la funcionalidad de la
     * instancia
     * @param objeto de la clase tarea que define por cual tarea del proceso se
     * procedera a comensar la ejecucion de la instancia
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "CrearInstancia")
    public WR_resultado CrearInstancia(@WebParam(name = "instanciaActual") instancia instanciaActual, @WebParam(name = "sesionActual") sesion sesionActual, @WebParam(name = "periodoActual") periodo periodoActual, @WebParam(name = "grupoActual") grupo grupoActual, @WebParam(name = "procesoActual") proceso procesoActual, @WebParam(name = "tareaInicial") tarea tareaInicial) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarCrearInstancia(sesionActual, periodoActual, grupoActual, procesoActual, tareaInicial);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            sesionActual = mySesionFacade.find(sesionActual.getId());
            if (sesionActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion invalida");
                return Resultado;
            }
            if (sesionActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion invalida");
                return Resultado;
            }
            if (sesionActual.getEstado().compareTo("abierta") != 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Sesion invalida");
                return Resultado;
            }
            instanciaActual.setIdUsuario(myUsuarioFacade.find(sesionActual.getIdUsuario().getId()));
            if (instanciaActual.getIdUsuario() == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("usuario no encontrado");
                return Resultado;
            }
            if (instanciaActual.getIdUsuario().getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("usuario no encontrado");
                return Resultado;
            }
            periodoActual = myPeriodoFacade.find(periodoActual.getId());
            if (periodoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El periodo ingresado no pudo ser encontrado");
                return Resultado;
            }
            grupoActual = myGrupoFacade.find(grupoActual.getId());
            if (grupoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El grupo ingresado no pudo ser encontrado");
                return Resultado;
            }
            procesoActual = myProcesoFacade.find(procesoActual.getId());
            if (procesoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El procesoo ingresado no pudo ser encontrado");
                return Resultado;
            }
            if (this.ObtenerTareasIniciales(procesoActual).getTareas().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("No es posible crear instancias a partir de un proceso sin tareas iniciales");
                return Resultado;
            }

            tareaInicial = myTareaFacade.find(tareaInicial.getId());
            if (tareaInicial == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La tarea inicial no pudo ser encontrada");
                return Resultado;
            }
            if (!tareaInicial.getTareaInicial()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La tarea ingresada no es una tarea inicial");
                return Resultado;
            }

            /**
             * Procedemos a crear la actividad inicial
             */
            actividad nuevaActividad = new actividad();
            nuevaActividad.setBorrado(false);
            nuevaActividad.setDuracion(tareaInicial.getDuracion());
            nuevaActividad.setEstado("pendiente");
            nuevaActividad.setFechaAsignacion(new Date());
            nuevaActividad.setIdEquivalenciasTiempo(tareaInicial.getIdEquivalenciaTiempo());
            nuevaActividad.setIdPrioridad(tareaInicial.getIdPrioridad());
            nuevaActividad.setIdTarea(tareaInicial);
            nuevaActividad.setIdUsuarioOrigen(instanciaActual.getIdUsuario());
            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            Long idPeriodoGrupoProcesoActual = null;
            periodo_grupo_proceso nuevoPeriodoGrupoProceso = new periodo_grupo_proceso();
            try {
                EntityManager em = emf.createEntityManager();
                idPeriodoGrupoProcesoActual = (Long) em.createNativeQuery("select id from periodo_grupo_proceso where id_periodo='" + periodoActual.getId() + "' and id_grupo = '" + grupoActual.getId() + "' and id_proceso = '" + procesoActual.getId() + "'").getSingleResult();
                nuevoPeriodoGrupoProceso = myperiodoGrupoProcesoFacade.find(idPeriodoGrupoProcesoActual);
            } catch (javax.persistence.NoResultException e) {
                /**
                 * el periodo_grupo_proceso no existe y hay que crearlo
                 */
                nuevoPeriodoGrupoProceso.setIdGrupo(grupoActual);
                nuevoPeriodoGrupoProceso.setIdPeriodo(periodoActual);
                nuevoPeriodoGrupoProceso.setIdProceso(procesoActual);
                myperiodoGrupoProcesoFacade.create(nuevoPeriodoGrupoProceso);
            }
            instanciaActual.setIdPeriodoGrupoProceso(nuevoPeriodoGrupoProceso);



            instanciaActual.setEstado("abierta");
            instanciaActual.setFechaApertura(new Date());

            myInstanciaFacade.create(instanciaActual);
            nuevaActividad.setIdInstancia(instanciaActual);
            myActividadFacade.create(nuevaActividad);

            Actividad ActividadAuxiliar = new Actividad();
            ActividadAuxiliar.setId(nuevaActividad.getId());
            Politica politicaAuxiliar = new Politica();
            politicaAuxiliar.setId(tareaInicial.getIdPolitica().getId());
            politicaAuxiliar.setNombre(tareaInicial.getIdPolitica().getNombre());

            WrResultado ResultadoPreliminar = this.aplicarPolitica(ActividadAuxiliar, politicaAuxiliar);
            if (ResultadoPreliminar.getEstatus().compareTo("FAIL") == 0) {
                Resultado.setObservacion("La tarea inicial no pudo ser asociada a un usuario");
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
     * Crea un nuevo registro del tipo periodo.
     *
     * @param periodoActual objeto del tipo periodo que debera contener los
     * atributos necesarios para la creacion del nuevo registro
     * @return objeto de la clase WR_resultado que informara sobre el resultado
     * de la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "CrearPeriodo")
    public WR_resultado CrearPeriodo(@WebParam(name = "periodoActual") periodo periodoActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarCrearPeriodo(periodoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            periodoActual.setEstado("Activo");
            myPeriodoFacade.create(periodoActual);
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
     * Consulta las actividades asociadas a la instancia ingresada.
     *
     * @param instanciaActual objeto del tipo instancia que debera contener el
     * identificador de la instancia que se desea consultar
     * @return objeto de la clase WR_actividad que informara sobre el resultado
     * de la operacion y la lista de actividades resultante
     * @see WR_actividad
     */
    @WebMethod(operationName = "ConsultarActividadesPorInstancia")
    public WR_actividad ConsultarActividadesPorInstancia(@WebParam(name = "instanciaActual") instancia instanciaActual) {
        WR_actividad Resultado = new WR_actividad();
        Resultado = myValidador.validarConsultarActividadesPorInstancia(instanciaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            instanciaActual = myInstanciaFacade.find(instanciaActual.getId());
            if (instanciaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Instancia no encontrada");
                return Resultado;
            }

            Iterator iterador = instanciaActual.getActividadCollection().iterator();
            actividad actividadAuxiliar;
            while (iterador.hasNext()) {
                actividadAuxiliar = (actividad) iterador.next();
                actividadAuxiliar.setAuditoriaCollection(null);
                actividadAuxiliar.setColaDeTarea(null);
                actividadAuxiliar.setDocumentoCollection(null);
                actividadAuxiliar.setEstado(null);
                actividadAuxiliar.setFechaAlerta(null);
                actividadAuxiliar.setFechaApertura(null);
                actividadAuxiliar.setFechaAsignacion(null);
                actividadAuxiliar.setFechaCierre(null);
                actividadAuxiliar.setDuracion(null);
                actividadAuxiliar.setIdEquivalenciasTiempo(null);
                actividadAuxiliar.setIdInstancia(null);
                actividadAuxiliar.setIdPrioridad(null);
                actividadAuxiliar.setIdUsuarioOrigen(null);
                actividadAuxiliar.setParametrosEntrada(null);
                actividadAuxiliar.setParametrosSalida(null);
                actividadAuxiliar.setMaquina(null);

                tarea tareaAuxiliar = new tarea();
                tareaAuxiliar.setId(actividadAuxiliar.getIdTarea().getId());
                tareaAuxiliar.setNombre(actividadAuxiliar.getIdTarea().getNombre());
                actividadAuxiliar.setIdTarea(tareaAuxiliar);

                usuario usuarioAuxiliar = new usuario();
                usuarioAuxiliar.setId(actividadAuxiliar.getIdUsuario().getId());
                usuarioAuxiliar.setPrimerNombre(actividadAuxiliar.getIdUsuario().getPrimerNombre());
                usuarioAuxiliar.setPrimerApellido(actividadAuxiliar.getIdUsuario().getPrimerApellido());
                actividadAuxiliar.setIdUsuario(usuarioAuxiliar);

                Resultado.ingresarActividad(actividadAuxiliar);
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getActividads().size() + " actividades encontradas");
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
     * Consulta las tareas ppor las cuales se pueden iniciar las instancias del
     * proceso ingresado..
     *
     * @param procesoActual objeto del tipo proceso que debera contener el
     * identificador del proceso cuyas tareas iniciales se desea consultar
     * @return objeto de la clase WR_tarea que informara sobre el resultado de
     * la operacion y contendra la lista de procesos resultantes
     * @see WR_tarea
     */
    @WebMethod(operationName = "ObtenerTareasIniciales")
    public WR_tarea ObtenerTareasIniciales(@WebParam(name = "procesoActual") proceso procesoActual) {
        WR_tarea Resultado = new WR_tarea();
        Resultado = myValidador.validarObtenerTareasIniciales(procesoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            procesoActual = myProcesoFacade.find(procesoActual.getId());
            if (procesoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("proceso no encontrado");
                return Resultado;
            }

            Iterator iterador = procesoActual.getTareaCollection().iterator();
            tarea tareaAuxiliar;
            while (iterador.hasNext()) {
                tareaAuxiliar = (tarea) iterador.next();
                if (tareaAuxiliar.getTareaInicial()) {
                    if (!tareaAuxiliar.getBorrado()) {
                        Resultado.getTareas().add(new tarea(tareaAuxiliar.getId(), tareaAuxiliar.getNombre(), tareaAuxiliar.getImplementacion(), tareaAuxiliar.getImplementacion(), null, tareaAuxiliar.getDuracion(), tareaAuxiliar.getCosto(), null, false, true, tareaAuxiliar.getTareaInformativa()));
                    }
                }
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getTareas().size() + " tareas encontradas");


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
     * Método que lista el estado de las instancias
     */
    @WebMethod(operationName = "buscarEstados")
    public List<String> buscarEstados() {

        return myInstanciaFacade.buscarestados();

    }

   
/**
     * Consume el servicio aplicarPolitica.
     *
     * @param actividadActual objeto de la clase Actividad cuyo identificador
     * debe corresponder a la actividad que se desea analizar
     * @param politicaActual objeto de la clase politica cuyo atributo nombre
     * debe poseer una politica valida para asignacion de usuarios a actividades
     * @return objeto de la clase WrUsuario con la lista de candidatos y la
     * informacion del resultado de la operacion
     * @see WrUsuario
     */
   
    private WrResultado aplicarPolitica(com.seguroshorizonte.capadeservicios.clienteweb.Actividad actividadActual, com.seguroshorizonte.capadeservicios.clienteweb.Politica politicaActual) {
        com.seguroshorizonte.capadeservicios.clienteweb.AplicarPolitica port = service.getAplicarPoliticaPort();
        return port.aplicarPolitica(actividadActual, politicaActual);
    }
   
    
}
