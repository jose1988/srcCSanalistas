/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.sesionFacade;
import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.sesion;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_sesion;
import com.seguroshorizonte.capadeservicios.validadores.GestionDeControlDeUsuariosValidador;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Esta clase posee las operaciones necesarias para el inicio y cierre de sesion
 * de los usuarios.
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
@EJB(name = "GestionDeControlDeUsuarios", beanInterface = Local.class)
@WebService(serviceName = "GestionDeControlDeUsuarios")
public class GestionDeControlDeUsuarios {

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
    GestionDeControlDeUsuariosValidador myValidador = new GestionDeControlDeUsuariosValidador();
    @EJB
    usuarioFacade myUsuarioFacade = new usuarioFacade();
    @EJB
    sesionFacade mySesionFacade = new sesionFacade();

    /**
     * Crea una nueva sesion que se mantiene hasta que sea cerrada y que le
     * permitira al usuario realizar algunas actividades
     *
     * @param usuarioActual objeto de la clase usuario que debera poseer valores
     * validos en sus atributos id y clave
     * @return objeto del tipo WR_resultado que informara del resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "LogIn")
    public WR_sesion LogIn(@WebParam(name = "sesionActual") sesion sesionActual) {
        WR_sesion Resultado = new WR_sesion();
        Resultado = myValidador.validarLogIn(sesionActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }

        try {
            usuario intermedio = myUsuarioFacade.find(sesionActual.getIdUsuario().getId());

            if (intermedio == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("usuario o contraseña incorrectos");
                return Resultado;
            }
            if (intermedio.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("usuario o contraseña incorrectos");
                return Resultado;
            }

            if (intermedio.getClave().compareTo(sesionActual.getIdUsuario().getClave()) == 0) {
                Resultado.setEstatus("OK");
                sesionActual.setEstado("Abierta");
                sesionActual.setFechaCreacion(new Date());
                sesionActual.setIdUsuario(intermedio);
                mySesionFacade.create(sesionActual);
                Resultado.addSesion(new sesion(sesionActual.getId()));
            } else {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("usuario o contraseña incorrectos");
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
     * Cierra la sesion de un determinado usuario limitando asi las operaciones
     * a las que podra tener acceso. NOTA IMPORTANTE: SERVICIO MODIFICADO DEBIDO
     * A QUE SOLO CERRABA LA ULTIMA SESIÓN ABIERTA, SE EDITO PARA QUE CERRARA
     * LAS SESIÓN ABIERTA QUE TENIA ACUALMENTE EL USUARIO EN EL EQUIPO NO LA
     * ULTIMA. FECHA: 02-09-2013
     *
     * @param usuarioActual objeto de la clase usuario cuyo atributo id posee el
     * identificador del usuario que desea cerrar sesion
     * @return objeto del tipo WR_resultado que informara del resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "LogOut")
    public WR_resultado LogOut(@WebParam(name = "sesionActual") sesion sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidador.validarLogOut(sesionActual);
        int sesionesCerradas = 0;
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            sesionActual = mySesionFacade.find(sesionActual.getId());
            if (sesionActual.getEstado().compareTo("Abierta") == 0) {
                sesionActual.setEstado("Cerrada");
                sesionActual.setFechaFinalizacion(new Date());
                mySesionFacade.edit(sesionActual);
                Resultado.setEstatus("OK");
                Resultado.setObservacion("Sesión cerrada correctamente");
            } else {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("La sesión no estaba abierta");
            }
            return Resultado;

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
     *
     * Verifica si la sesión de usuario esta abierta en la base de datos 
     *
     * @param sesionActual objeto de la clase sesion cuyo atributo id posee el
     * identificador de la sesión de usuario que desea verificar
     * @return objeto del tipo WR_resultado que informara del resultado de la
     * operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "LogSesion")
    public boolean LogSesion(@WebParam(name = "sesionActual") sesion sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        boolean bandera = false;
        try {
            sesion intermedio = mySesionFacade.find(sesionActual.getId());
            if (intermedio.getEstado().compareTo("Abierta") == 0) {
                bandera = true;
            }
        } catch (Exception e) {
            System.out.println("--------------------------------------------------------------");
        }
        return bandera;
    }
}
