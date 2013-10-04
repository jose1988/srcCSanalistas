/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.clasificacion_procesoFacade;
import com.seguroshorizonte.capadeservicios.beans.clasificacion_rolFacade;
import com.seguroshorizonte.capadeservicios.beans.clasificacion_tareaFacade;
import com.seguroshorizonte.capadeservicios.beans.clasificacion_usuarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_proceso;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_rol;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_tarea;
import com.seguroshorizonte.capadeservicios.entidades.clasificacion_usuario;
import com.seguroshorizonte.capadeservicios.entidades.proceso;
import com.seguroshorizonte.capadeservicios.entidades.rol;
import com.seguroshorizonte.capadeservicios.entidades.tarea;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_proceso_detalle;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_rol_detalle;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_tarea_detalle;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_clasificacion_usuario_detalle;
import com.seguroshorizonte.capadeservicios.validadores.GestionDeClasificacionesValidador;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Esta clase posee las operaciones necesarias para consultar informacion
 * detallada sobre las clasificaciones registradas de los diferentes tipos.
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
@EJB(name = "GestionDeClasificaciones", beanInterface = Local.class)
@WebService(serviceName = "GestionDeClasificaciones")
public class GestionDeClasificaciones {

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
    GestionDeClasificacionesValidador myValidador = new GestionDeClasificacionesValidador();
    @EJB
    clasificacion_procesoFacade myclasificacion_procesoFacade = new clasificacion_procesoFacade();
    @EJB
    clasificacion_usuarioFacade myclasificacion_usuarioFacade = new clasificacion_usuarioFacade();
    @EJB
    clasificacion_tareaFacade myclasificacion_tareaFacade = new clasificacion_tareaFacade();
    @EJB
    clasificacion_rolFacade myclasificacion_rolFacade = new clasificacion_rolFacade();

    /**
     * Retorna informacion detallada sobre una clasificacion de procesos. Con
     * esta operacion ademas se obtiene informacion basica sobre los procesos
     * asociados a la clasificacion consultada.
     *
     * @param clasificacionProcesoActual Objeto de la clase
     * clasificacion_proceso cuyo atributo id corresponda con el del registro
     * que desea consultarse
     * @return un objeto de la clase WR_clasificacion_proceso_detalle con la
     * informacion detallada de la clasificacion y una lista de los procesos
     * pertenecientes
     * @see WR_clasificacion_proceso_detalle
     */
    @WebMethod(operationName = "ConsultarDetallesClasificacionProceso")
    public WR_clasificacion_proceso_detalle ConsultarDetallesClasificacionProceso(@WebParam(name = "clasificacionProcesoActual") clasificacion_proceso clasificacionProcesoActual) {
        WR_clasificacion_proceso_detalle Resultado = new WR_clasificacion_proceso_detalle();
        Resultado = myValidador.validarConsultarDetallesClasificacionProceso(clasificacionProcesoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {

            clasificacionProcesoActual = myclasificacion_procesoFacade.find(clasificacionProcesoActual.getId());
            if (clasificacionProcesoActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de procesos no encontrada");
                return Resultado;
            }

            /*
             * Verificamos que el objeto no ha sido borrado
             */

            if (clasificacionProcesoActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de procesos no encontrada");
                return Resultado;
            }

            /*
             * preparamos el envoltorio
             */

            Resultado.setEstatus("OK");
            Resultado.setDescripcion(clasificacionProcesoActual.getDescripcion());
            Resultado.setFechaCreacion(clasificacionProcesoActual.getFechaCreacion());
            Resultado.setFechaModificacion(clasificacionProcesoActual.getFechaModificacion());
            Resultado.setIdClasificacionProceso(clasificacionProcesoActual.getId());
            Resultado.setNombre(clasificacionProcesoActual.getNombre());
            proceso nuevoProceso;
            Iterator myIterator = clasificacionProcesoActual.getProcesoCollection().iterator();
            while (myIterator.hasNext()) {
                nuevoProceso = (proceso) myIterator.next();

                if (!nuevoProceso.getBorrado()) {

                    nuevoProceso.setAutor(null);
                    nuevoProceso.setDescripcionVersion(null);
                    nuevoProceso.setDocumentacion(null);
                    nuevoProceso.setEstado(null);
                    nuevoProceso.setFechaCreacion(null);
                    nuevoProceso.setIdClasificacionProceso(null);
                    nuevoProceso.setIdEquivalenciasTiempo(null);
                    nuevoProceso.setIdPrioridad(null);
                    Resultado.getProcesos().add(nuevoProceso);

                }

            }
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
     * Retorna informacion detallada sobre una clasificacion de usuarios. Con
     * esta operacion ademas se obtiene informacion basica sobre los procesos
     * asociados a la clasificacion consultada.
     *
     * @param clasificacionUsuarioActual objeto de la clase
     * clasificacion_usuario cuyo atributo id corresponda con el del registro
     * que desea consultarse
     * @return un objeto de la clase WR_clasificacion_usuario_detalle con la
     * informacion detallada de la clasificacion y una lista de los usuarios
     * pertenecientes
     * @see WR_clasificacion_usuario_detalle
     */
    @WebMethod(operationName = "ConsultarDetallesClasificacionUsuario")
    public WR_clasificacion_usuario_detalle ConsultarDetallesClasificacionUsuario(@WebParam(name = "clasificacionUsuarioActual") clasificacion_usuario clasificacionUsuarioActual) {
        WR_clasificacion_usuario_detalle Resultado = new WR_clasificacion_usuario_detalle();
        Resultado = myValidador.validarConsultarDetallesClasificacionUsuario(clasificacionUsuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {

            clasificacionUsuarioActual = myclasificacion_usuarioFacade.find(clasificacionUsuarioActual.getId());
            if (clasificacionUsuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de usuarios no encontrada");
                return Resultado;
            }

            /*
             * Verificamos que el objeto no ha sido borrado
             */

            if (clasificacionUsuarioActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de usuario no encontrada");
                return Resultado;
            }

            /*
             * preparamos el envoltorio y limitamos la informacion que
             * retornamos
             */

            Resultado.setEstatus("OK");
            Resultado.setDescripcion(clasificacionUsuarioActual.getDescripcion());
            Resultado.setFechaCreacion(clasificacionUsuarioActual.getFechaCreacion());
            Resultado.setFechaModificacion(clasificacionUsuarioActual.getFechaModificacion());
            Resultado.setIdClasificacionUsuario(clasificacionUsuarioActual.getId());
            Resultado.setNombre(clasificacionUsuarioActual.getNombre());
            usuario nuevoUsuario;
            Iterator myIterator = clasificacionUsuarioActual.getUsuarioCollection().iterator();
            while (myIterator.hasNext()) {
                nuevoUsuario = (usuario) myIterator.next();
                if (!nuevoUsuario.getBorrado()) {

                    nuevoUsuario.setActividadOrigenCollection(null);
                    nuevoUsuario.setActividadUsuarioCollection(null);
                    nuevoUsuario.setBandejaCollection(null);
                    nuevoUsuario.setCedula(null);
                    nuevoUsuario.setClave(null);
                    nuevoUsuario.setDescripcion(null);
                    nuevoUsuario.setDestinatarioCollection(null);
                    nuevoUsuario.setDiasValidezClave(0);
                    nuevoUsuario.setDireccionOficina(null);
                    nuevoUsuario.setDireccionPersonal(null);
                    nuevoUsuario.setEstado(null);
                    nuevoUsuario.setFax(null);
                    nuevoUsuario.setFechaActualizacionClave(null);
                    nuevoUsuario.setFechaCreacion(null);
                    nuevoUsuario.setIdClasificacionUsuario(null);
                    nuevoUsuario.setIdOrganizacion(null);
                    nuevoUsuario.setIdSkin(null);
                    Resultado.getUsuarios().add(nuevoUsuario);

                }

            }
            Resultado.setObservacion(Resultado.getUsuarios().size() + " usuarios encontrados");

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
     * Retorna informacion detallada sobre una clasificacion de tareas. Con esta
     * operacion ademas se obtiene informacion basica sobre las tareas asociadas
     * a la clasificacion consultada.
     *
     * @param clasificacionTareaActual objeto de la clase clasificacion_tarea
     * cuyo atributo id corresponda con el del registro que desea consultarse
     * @return un objeto de la clase WR_clasificacion_tarea_detalle con la
     * informacion detallada de la clasificacion y una lista de las tareas
     * pertenecientes
     * @see WR_clasificacion_tarea_detalle
     */
    @WebMethod(operationName = "ConsultardetallesClasificacionTarea")
    public WR_clasificacion_tarea_detalle ConsultardetallesClasificacionTarea(@WebParam(name = "clasificacionTareaActual") clasificacion_tarea clasificacionTareaActual) {
        WR_clasificacion_tarea_detalle Resultado = new WR_clasificacion_tarea_detalle();
        Resultado = myValidador.validarConsultarDetallesClasificacionTarea(clasificacionTareaActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {

            clasificacionTareaActual = myclasificacion_tareaFacade.find(clasificacionTareaActual.getId());
            if (clasificacionTareaActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de tareas no encontrada");
                return Resultado;
            }

            /*
             * Verificamos que el objeto no ha sido borrado
             */

            if (clasificacionTareaActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de tareas no encontrada");
                return Resultado;
            }

            /*
             * preparamos el envoltorio
             */

            Resultado.setEstatus("OK");
            Resultado.setDescripcion(clasificacionTareaActual.getDescripcion());
            Resultado.setFechaCreacion(clasificacionTareaActual.getFechaCreacion());
            Resultado.setFechaModificacion(clasificacionTareaActual.getFechaModificacion());
            Resultado.setIdClasificacionTarea(clasificacionTareaActual.getId());
            Resultado.setNombre(clasificacionTareaActual.getNombre());
            tarea nuevaTarea;
            Iterator myIterator = clasificacionTareaActual.getTareaCollection().iterator();
            while (myIterator.hasNext()) {
                nuevaTarea = (tarea) myIterator.next();
                if (!nuevaTarea.getBorrado()) {

                    nuevaTarea.setActividadCollection(null);
                    nuevaTarea.setAutor(null);
                    nuevaTarea.setColadetareaCollection(null);
                    nuevaTarea.setContadorroundrobinCollection(null);
                    nuevaTarea.setCosto(0);
                    nuevaTarea.setDocumentacion(null);
                    nuevaTarea.setDuracion(null);
                    nuevaTarea.setEstado(null);
                    nuevaTarea.setGrupopoliticatareaCollection(null);
                    nuevaTarea.setIdClasificacionTarea(null);
                    nuevaTarea.setIdEquivalenciaTiempo(null);
                    nuevaTarea.setIdPolitica(null);
                    nuevaTarea.setIdPrioridad(null);
                    nuevaTarea.setIdProceso(null);
                    nuevaTarea.setImplementacion(null);
                    nuevaTarea.setTarearolCollection(null);
                    nuevaTarea.setTransicionCollection(null);
                    nuevaTarea.setTransicionCollection1(null);
                    Resultado.getTareas().add(nuevaTarea);

                }

            }
            Resultado.setObservacion(Resultado.getTareas().size() + " Tareas encontradas");

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
     * Retorna informacion detallada sobre una clasificacion de roles. Con esta
     * operacion ademas se obtiene informacion basica sobre los roles asociados
     * a la clasificacion consultada
     *
     * @param clasificacionRolActual objeto de la clase clasificacion_rol cuyo
     * atributo id corresponda con el del registro que desea consultar
     * @return un objeto de la clase WR_clasificacion_rol_detalle con la
     * informacion detallada de la clasificacion y una lista de los roles
     * pertenecientes
     * @see WR_clasificacion_rol_detalle
     */
    @WebMethod(operationName = "ConsultarDetallesClasificacionRol")
    public WR_clasificacion_rol_detalle ConsultarDetallesClasificacionRol(@WebParam(name = "clasificacionRolActual") clasificacion_rol clasificacionRolActual) {
        WR_clasificacion_rol_detalle Resultado = new WR_clasificacion_rol_detalle();
        Resultado = myValidador.validarConsultarDetallesClasificacionRol(clasificacionRolActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {

            clasificacionRolActual = myclasificacion_rolFacade.find(clasificacionRolActual.getId());
            if (clasificacionRolActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de roles no encontrada");
                return Resultado;
            }

            /*
             * Verificamos que el objeto no ha sido borrado
             */

            if (clasificacionRolActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Clasificacion de roles no encontrada");
                return Resultado;
            }

            /*
             * preparamos el envoltorio
             */

            Resultado.setEstatus("OK");
            Resultado.setDescripcion(clasificacionRolActual.getDescripcion());
            Resultado.setFechaCreacion(clasificacionRolActual.getFechaCreacion());
            Resultado.setFechaModificacion(clasificacionRolActual.getFechaModificacion());
            Resultado.setIdClasificacionRol(clasificacionRolActual.getId());
            Resultado.setNombre(clasificacionRolActual.getNombre());
            rol nuevoRol;
            Iterator myIterator = clasificacionRolActual.getRolCollection().iterator();
            while (myIterator.hasNext()) {
                nuevoRol = (rol) myIterator.next();
                if (!nuevoRol.getBorrado()) {

                    nuevoRol.setDestinatarioCollection(null);
                    nuevoRol.setDocumentacion(null);
                    nuevoRol.setEstado(null);
                    nuevoRol.setIdClasificacionRol(null);
                    nuevoRol.setReporterolCollection(null);
                    nuevoRol.setTarearolCollection(null);
                    nuevoRol.setUsuariogruporolCollection(null);
                    Resultado.getRols().add(nuevoRol);

                }

            }
            Resultado.setObservacion(Resultado.getRols().size() + " Roles encontrados");

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
