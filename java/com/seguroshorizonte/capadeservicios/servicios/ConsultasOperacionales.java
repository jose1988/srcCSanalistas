/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.equivalencia_tiempoFacade;
import com.seguroshorizonte.capadeservicios.beans.organizacionFacade;
import com.seguroshorizonte.capadeservicios.beans.prioridadFacade;
import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.beans.usuario_grupo_rolFacade;
import com.seguroshorizonte.capadeservicios.entidades.equivalencia_tiempo;
import com.seguroshorizonte.capadeservicios.entidades.organizacion;
import com.seguroshorizonte.capadeservicios.entidades.prioridad;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_equivalencia_tiempos;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_equivalencia_tiempo;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_grupo;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_organizacion;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_prioridad;
import com.seguroshorizonte.capadeservicios.validadores.ConsultasOperacionalesValidador;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Esta clase contiene las operaciones web que devuelven los parametros de
 * funcionamiento del sistema. Contiene operaciones que serviran para construir
 * una interfaz de usuario capaz de ubicar al usuario en su entorno y permitir
 * la gestion de algunos parametros.
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
@EJB(name = "ConsultasOperacionales", beanInterface = Local.class)
@WebService(serviceName = "ConsultasOperacionales")
public class ConsultasOperacionales implements Serializable {

    /**
     * Declaracion del objeto validador y los facade necesarios. El validador
     * declarado debera verificar que fueron introducidos los parametros
     * necesarios y que tienen un valor valido
     *
     * <p>De esta manera se aisla el codigo de la validacion basica y se evita
     * incurrir en llamados innecesarios al sistema de base de datos
     *
     * <p>Las clases asociadas a las facade fueron generadas de manera
     * automatica a partir de las entidades y su funcion es la de gestionar las
     * operaciones asociadas a la persistencia que puedan necesitarse para
     * lograr el objetivo de las operaciones aqui expuestas
     */
    ConsultasOperacionalesValidador myValidador = new ConsultasOperacionalesValidador();
    @EJB
    organizacionFacade myOrganizacion_facade = new organizacionFacade();
    @EJB
    usuario_grupo_rolFacade myUsuario_Grupo_RolFacade = new usuario_grupo_rolFacade();
    @EJB
    equivalencia_tiempoFacade myEquivalencia_TiempoFacade = new equivalencia_tiempoFacade();
    @EJB
    prioridadFacade myPrioridadFacade = new prioridadFacade();
    @EJB
    usuarioFacade myUsuarioFacade = new usuarioFacade();

    /**
     * Retorna los datos asociados a una organizacion. a partir del id del
     * usuario ingresado se obtienen los datos asociados a la organizacion a la
     * que pertenece.
     *
     * @param usuarioActual un objeto de la clase usuario cuyo id este asociado
     * a la organizacion que se desea consultar
     * @return un wrapper del tipo WR_organizacion con los datos necesarios y el
     * resultado de la operacion
     * @see WR_organizacion
     */
    @WebMethod(operationName = "ConsultarOrganizacion")
    public WR_organizacion ConsultarOrganizacion(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_organizacion Resultado = new WR_organizacion();
        Resultado = myValidador.validarConsultarOrganizacion(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        //**Logica de Negocio**//
        try {
            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
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
            organizacion aux = usuarioActual.getIdOrganizacion();

            /*
             * Verificamos que el registro existe y que no ha sido borrado de
             * manera logica
             */
            if (aux == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Organizacion no encontrada");
                return Resultado;
            }
            if (aux.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Organizacion no encontrada");
                return Resultado;
            }

            Resultado.ingresarOrganizacion(aux);
            Resultado.setEstatus("OK");

        } catch (Exception e) {
            Resultado.setEstatus("Fail");
            Resultado.setObservacion(e.getMessage());
            e.printStackTrace();
        } finally {
            return Resultado;
        }


    }

    /**
     * Retorna los grupos a los que pertenece un usuario. a partir del id del
     * usuario ingresado se obtiene la lista de grupos asociados.
     *
     * @param usuarioActual un objeto de la clase usuario que posea un id valido
     * @return un wrapper del tipo WR_grupo con la lista de grupos asociados y
     * el resultado de la operacion
     * @see WR_grupo
     */
    @WebMethod(operationName = "ConsultarGrupos")
    public WR_grupo ConsultarGrupos(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_grupo Resultado = new WR_grupo();
        Resultado = myValidador.validarConsultarGrupos(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {

            usuarioActual = myUsuarioFacade.find(usuarioActual.getId());
            if (usuarioActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            Iterator iterador = usuarioActual.getUsuariogruporolCollection().iterator();
            usuario_grupo_rol usuarioGrupoRolAuxiliar;
            while (iterador.hasNext()) {
                usuarioGrupoRolAuxiliar = (usuario_grupo_rol) iterador.next();
                if (!Resultado.getGrupos().contains(usuarioGrupoRolAuxiliar.getIdGrupo())) {
                    usuarioGrupoRolAuxiliar.getIdGrupo().setColadetareaCollection(null);
                    usuarioGrupoRolAuxiliar.getIdGrupo().setContadorroundrobinCollection(null);
                    usuarioGrupoRolAuxiliar.getIdGrupo().setDestinatarioCollection(null);
                    usuarioGrupoRolAuxiliar.getIdGrupo().setGrupopoliticatareaCollection(null);
                    usuarioGrupoRolAuxiliar.getIdGrupo().setIdOrganizacion(null);
                    usuarioGrupoRolAuxiliar.getIdGrupo().setPeriodogrupoprocesoCollection(null);
                    usuarioGrupoRolAuxiliar.getIdGrupo().setUsuariogruporolCollection(null);
                    Resultado.ingresarGrupo(usuarioGrupoRolAuxiliar.getIdGrupo());
                }
            }




            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getGrupos().size() + " Grupos encontrados");

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
     * Retorna todos los objetos de la clase equivalencia_tiempo registrados.
     * Este metodo retorna una lista de las equivalencias de las unidades de
     * tiempo registradas e informacion basica de cada una de ellas, envueltas
     * en un objeto de la clase WR_equivalencia_tiempos.
     *
     * @return un wrapper del tipo WR_equivalencia_tiempos con informacion
     * basica sobre cada objeto y el resultado de la operacion
     *
     * @see WR_equivalencia_tiempos
     */
    @WebMethod(operationName = "ConsultarEquivalencias")
    public WR_equivalencia_tiempos ConsultarEquivalencias() {
        WR_equivalencia_tiempos Resultado = new WR_equivalencia_tiempos();

        try {
            List<equivalencia_tiempo> intermedios = myEquivalencia_TiempoFacade.findAll();
            if (intermedios == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("No se han encontrado registros");
                return Resultado;
            }
            Iterator iterador = intermedios.iterator();
            equivalencia_tiempo equivalencia_tiempoAuxiliar = new equivalencia_tiempo();
            while (iterador.hasNext()) {
                equivalencia_tiempoAuxiliar = (equivalencia_tiempo) iterador.next();
                if (!equivalencia_tiempoAuxiliar.getBorrado()) {
                    equivalencia_tiempoAuxiliar.setActividadCollection(null);
                    equivalencia_tiempoAuxiliar.setProcesoCollection(null);
                    equivalencia_tiempoAuxiliar.setTareaCollection(null);
                    equivalencia_tiempoAuxiliar.setMinutos(-1);
                    Resultado.ingresarEquivalencia(equivalencia_tiempoAuxiliar);
                }
            }

            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getEquivalenciaTiempos().size() + " Equivalencias encontradas");


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
     * Retorna informacion detallada de un objeto de la clase
     * equivalencia_tiempo. Esta operacion retorna informacion en detalle sobre
     * el objeto ingresado.
     *
     * @param EquivalenciaTiempoActual el objeto de la clase equivalencia_tiempo
     * que se desea conocer en detalle y cuyo atributo id este registrado
     * @return un objeto de la clase WR_equivalencia_tiempo con la informacion
     * solicitada y el resultado de la operacion
     * @see WR_equivalencia_tiempo
     */
    @WebMethod(operationName = "ConsultarEquivalencia")
    public WR_equivalencia_tiempo ConsultarEquivalencia(@WebParam(name = "EquivalenciaTiempoActual") equivalencia_tiempo EquivalenciaTiempoActual) {
        WR_equivalencia_tiempo Resultado = new WR_equivalencia_tiempo();
        Resultado = myValidador.validarConsultarEquivalencia(EquivalenciaTiempoActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }


        try {
            equivalencia_tiempo intermedio = myEquivalencia_TiempoFacade.find(EquivalenciaTiempoActual.getId());

            if (intermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("No se encontraron registros");
                return Resultado;
            }

            if (intermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("No se encontraron registros");
                return Resultado;
            }

            Resultado.setDescripcion(intermedio.getDescripcion());
            Resultado.setId(intermedio.getId());
            Resultado.setMinutos(intermedio.getMinutos());
            Resultado.setNombre(intermedio.getNombre());
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
     * Retorna una listaa con las prioridades registradas envuelta en un wrapper
     * de la clase WR_prioridad. la lista de objetos obtenida mediante esta
     * operacion solo contiene informacion basica sobre las prioridades.
     *
     * @return un objeto de la clase WR_prioridad que posee una lista de las
     * prioridades registradas y el resultado de la operacion
     * @see WR_prioridad
     */
    @WebMethod(operationName = "ConsultarPrioridades")
    public WR_prioridad ConsultarPrioridades() {
        WR_prioridad Resultado = new WR_prioridad();
        try {
            List<prioridad> intermedio = myPrioridadFacade.findAll();
            Iterator iterador = intermedio.iterator();
            prioridad prioridadAuxiliar;
            while (iterador.hasNext()) {
                prioridadAuxiliar = (prioridad) iterador.next();
                if (!prioridadAuxiliar.getBorrado()) {
                    prioridadAuxiliar.setActividadCollection(null);
                    prioridadAuxiliar.setProcesoCollection(null);
                    Resultado.ingresarPrioridad(prioridadAuxiliar);
                }
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(intermedio.size() + " prioridades encontradas");

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
     * Retorna informacion detallada sobre una prioridad en especifico. la
     * informacion de la prioridad vendra en un envoltorio de la clase
     * WR_prioridad.
     *
     * @param prioridadActual un objeto de la clase prioridad cuyo id
     * corresponde al de la prioridad que desea consultarse
     * @return un objeto de la clase WR_prioridad que almacena la prioridad
     * solicitada en la primera posicion de su lista de prioridades y el
     * resultado de la operacion
     * @see WR_prioridad
     */
    @WebMethod(operationName = "ConsultarPrioridad")
    public WR_prioridad ConsultarPrioridad(@WebParam(name = "prioridadActual") prioridad prioridadActual) {
        WR_prioridad Resultado = new WR_prioridad();
        Resultado = myValidador.validarConsultarPrioridad(prioridadActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {
            prioridadActual = myPrioridadFacade.find(prioridadActual.getId());
            if(prioridadActual==null){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Prioridad no encontrada");
                return Resultado;
            }
            if (prioridadActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Registro no encontrado");
                return Resultado;
            }
            prioridadActual.setActividadCollection(null);
            prioridadActual.setProcesoCollection(null);
            Resultado.ingresarPrioridad(prioridadActual);

            /*
             * limitamos la informacion que queremos retornar
             */

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
}
