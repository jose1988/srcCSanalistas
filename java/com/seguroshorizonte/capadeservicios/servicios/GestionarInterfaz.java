/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.skinFacade;
import com.seguroshorizonte.capadeservicios.beans.usuarioFacade;
import com.seguroshorizonte.capadeservicios.entidades.skin;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_skin;
import com.seguroshorizonte.capadeservicios.validadores.GestionarInterfaceValidador;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Esta clase posee las operaciones necesarias para la gestion de la interfaz de
 * usuario.
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
@EJB(name = "GestionarInterfaz", beanInterface = Local.class)
@WebService(serviceName = "GestionarInterfaz")
public class GestionarInterfaz {

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
    GestionarInterfaceValidador myValidator = new GestionarInterfaceValidador();
    @EJB
    usuarioFacade myUsuarioFacade = new usuarioFacade();
    @EJB
    skinFacade mySkinFacade = new skinFacade();

    /**
     * Esta operacion le permite al usuario cambiar el tema de su interfaz de
     * usuario
     *
     * @param usuarioActual objeto usuarioActual que debera poseer el id del
     * usuario que desea cambiar el tema y un objeto del tipo skin asociado con
     * el id del tema que se desea colocar
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la operacion
     * @see WR_resultado
     */
    @WebMethod(operationName = "CambiarSkin")
    public WR_resultado CambiarSkin(@WebParam(name = "usuarioActual") usuario usuarioActual, @WebParam(name = "skinActual") skin skinActual) {
        WR_resultado Resultado = new WR_resultado();
        Resultado = myValidator.validarCambiarSkin(usuarioActual,skinActual);
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
             
            skinActual = mySkinFacade.find(skinActual.getId());

           
            if (skinActual== null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Skin no encontrado");
                return Resultado;
            }
            if(skinActual.getBorrado()){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Skin no encontrado");
                return Resultado;
            }
            usuarioActual.setIdSkin(skinActual);

            myUsuarioFacade.edit(usuarioActual);
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
     * Restorna una lista de los temas disponibles
     *
     * @return objeto de la clase WR_skin que informa el resultado de la
     * operacion y posee la lista de los temas disponibles
     * @see WR_skin
     */
    @WebMethod(operationName = "ConsultarSkins")
    public WR_skin ConsultarSkins() {
        WR_skin Resultado = new WR_skin();
        try {
            List<skin> intermedios = mySkinFacade.findAll();
            Iterator iterador = intermedios.iterator();
            skin skinAuxiliar;
            while (iterador.hasNext()) {
                skinAuxiliar = (skin) iterador.next();
                if (!skinAuxiliar.getBorrado()) {
                    Resultado.ingresarSkin(skinAuxiliar);
                }
            }

            Resultado.setEstatus("Ok");
            Resultado.setObservacion(Resultado.getSkins().size() + " Skins encontradas");

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
