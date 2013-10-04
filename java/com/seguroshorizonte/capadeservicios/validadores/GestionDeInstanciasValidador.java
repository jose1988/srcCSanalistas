/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.validadores;

import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.instancia;
import com.seguroshorizonte.capadeservicios.entidades.periodo;
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

/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio
 * Gestion_De_Control_De_Usuarios. el objetivo de esta clase solo es verificar
 * que los atributos necesarios de los objetos ingresados existen y tienen un
 * formato correcto, no se verificara la coerencia de los datos con el sistema
 * de base de datos ni la congruencia entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class GestionDeInstanciasValidador {

    /**
     * Valida que el id de la instancia introducida sea un numero natural. el
     * objetivo de este metodo es validar que las instancias introducidas como
     * parametro a la operacion consultarInstancia tengan el formato adecuado.
     *
     * @param instanciaActual objeto de la clase instancia cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_instancia que informa sobre el resultado de
     * la validacion
     * @see WR_instancia
     */
    public WR_instancia validarConsultarInstancia(instancia instanciaActual) {
        WR_instancia Resultado = new WR_instancia();
        if (instanciaActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El id asociado a la instancia ingresada es invalido");
        } else {
            if (instanciaActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El id asociado a la instancia ingresada es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que los atributos id tanto de la instancia como del usuario
     * introducido tengan el formato deseado. El objetivo de este metodo es
     * validar que los atributos id del usuario y la instancia introducidoos
     * como parametro en la operacion consultarInstancias sea una cadena no
     * vacia de caracteres y un numero natural respectivamente.
     *
     * @param instanciaActual objeto de la clase instancia cuyo id debe ser un
     * numero natural
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @return objeto de la clase WR_instancia que informa sobre el resultado de
     * la validacion
     * @see WR_instancia
     */
    public WR_instancia validarConsultarInstancias(instancia instanciaActual, usuario usuarioActual) {
        WR_instancia Resultado = new WR_instancia();
        if (instanciaActual.getEstado() == null || usuarioActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El id asociado a la instancia ingresada es invalido");
        } else {
            if (instanciaActual.getEstado().isEmpty() || usuarioActual.getId().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El id asociado a la instancia ingresada es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del periodo introducido sea un numero natural. El
     * objetivo de este metodo es validar que los periodos introducidos a la
     * operacion consultarPeriodo tienen el el formato deseado.
     *
     * @param periodoActual objeto de la clase periodo cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_periodo que informa sobre el resultado de
     * la validacion
     * @see WR_periodo
     */
    public WR_periodo validarConsultarPeriodo(periodo periodoActual) {
        WR_periodo Resultado = new WR_periodo();
        if (periodoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo id del periodo ingresado es invalido");

        } else {
            if (periodoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo id del periodo ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del proceso introducido sea un numero natural. El
     * objetivo de este metodo es validar que el proceso introducido a la
     * operacion consultarPeriodosDisponibles tiene el formato deseado.
     *
     * @param procesoActual objeto de la clase proceso cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_periodo que informa el resultado de la
     * validacion
     * @see WR_periodo
     */
    public WR_periodo validarConsultarPeriodosDisponibles(proceso procesoActual) {
        WR_periodo Resultado = new WR_periodo();
        if (procesoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo id del proceso ingresado es invalido");
        } else {
            if (procesoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo id del proceso ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el id del proceso introducido es un numero natural. El
     * objetivo de este metodo es validar que el proceso introducido en la
     * operacion consultarProceso tiene el formato correcto.
     *
     * @param procesoActual objeto de la clase proceso cuyo id debe ser un
     * numero natural
     * @return objeto de la claseWR_proceso que informa el resultado de la
     * validacion
     * @see WR_proceso
     */
    public WR_proceso validarConsultarProceso(proceso procesoActual) {
        WR_proceso Resultado = new WR_proceso();
        if (procesoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo id del proceso ingresado es invalido");

        } else {
            if (procesoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo id del proceso ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que la instancia introducida posea todos los atributos requeridos
     * para la creacion de un nuevo registro. El objetivo de este metodo es
     * verificar que la instancia ingresada a la operacion crearInstancia posee
     * los atributos necesarios.
     *
     * @param sesionActual objeto de la clase sesion cuyo identificador debe ser
     * un numero natural
     * @param periodoActual objeto de la clase periodo cuyo identificador debe 
     * ser un numero natural
     * @param grupoActual objeto de la clase grupo cuyo identificador debe ser 
     * un numero natural
     * @param procesoActual objeto de la clase proceso cuyo identificador debe
     * ser un numero natural
     * @param tareaInicial objeto de la clase tarea cuyo identificador debe ser 
     * un numero natural
     * @return objeto de la clase WR_resultado que informara sobre el resultado
     * de la validacion
     * @see WR_resultado
     */
    public WR_resultado validarCrearInstancia(sesion sesionActual,periodo periodoActual, grupo grupoActual, proceso procesoActual,tarea tareaInicial) {
        WR_resultado Resultado = new WR_resultado();
        if(sesionActual.getId()==null || periodoActual.getId() == null || grupoActual.getId() == null || procesoActual.getId() == null || tareaInicial.getId() == null  ){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Deben ser introducidos todos los parametros necesarios");
            return Resultado;
        }
        if( sesionActual.getId()<0 || periodoActual.getId()<0 || grupoActual.getId()<0 ||procesoActual.getId()<0 || tareaInicial.getId()<0){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Alguno de los valores introducidos es invalido");
            return Resultado;
        }
        Resultado.setEstatus("OK");
        return Resultado;
    }

    /**
     * Valida que el periodo introducido posea los atributos requeridos para la
     * creacion de un nuevo registro.
     *
     * @param periodoActual objeto de la clase periodo que debe poseer valores
     * validos en los atributos descripcion, fecha_desde y fecha_hasta
     * @return objeto de la clase WR_resultado que informara sobre el resultado
     * de la validacion
     */
    public WR_resultado validarCrearPeriodo(periodo periodoActual) {
        WR_resultado Resultado = new WR_resultado();
        if (periodoActual.getDescripcion() == null || periodoActual.getFechaDesde() == null || periodoActual.getFechaHasta() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Debe ingresar los atributos descripcion fecha_desde y fecha_hasta al periodo que desea ingresar");
        } else {
            if (periodoActual.getDescripcion().isEmpty()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Debe ingresar los atributos descripcion fecha_desde y fecha_hasta al periodo que desea ingresar");
            } else {
                if (periodoActual.getFechaDesde().after(periodoActual.getFechaHasta())) {
                    Resultado.setEstatus("FAIL");
                    Resultado.setObservacion("el atributo fecha_hasta debe corresponder debe ser posterior al valor introducido en el atributo fecha_desde");
                } else {
                    Resultado.setEstatus("OK");
                }
            }
        }
        return Resultado;
    }

    /**
     * Valida que los atributos id tanto del periodo como del usuario
     * introducido tengan el formato deseado. El objetivo de este metodo es
     * validar que los atributos id del usuario y el periodo introducidos como
     * parametro en la operacion consultarProcesosDisponibles sean una cadena no
     * vacia de caracteres y un numero natural respectivamente.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser una
     * cadena no vacia de caracteres
     * @param periodoActual objeto de la clase periodo cuyo identificador debe
     * ser un numero natural
     * @return objeto de la clase WR_proceso que informa sobre el resultado de
     * la validacion
     * @see WR_proceso
     */
    public WR_proceso validarConsultarProcesosDisponibles(usuario usuarioActual, periodo periodoActual) {
        WR_proceso Resultado = new WR_proceso();
        if (usuarioActual.getId() == null || periodoActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El atributo id correspondiente al usuario ingresado es invalido");
        } else {
            if (usuarioActual.getId().isEmpty() || periodoActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El atributo id correspondiente al usuario ingresado es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el identificador de la instancia introducida tenga el formato
     * deseado. El objetivo de este metodo es validar que el atributo id de la
     * instancia introducida como parametro en la operacion
     * consultarActividadesPorInstancia sea un numero natural.
     *
     * @param instanciaActual objeto de la clase instancia cuyo identificador
     * debe ser un numero natural
     * @return objeto de la clase WR_actividad que informa sobre el resultado de
     * la validacion
     * @see WR_actividad
     */
    public WR_actividad validarConsultarActividadesPorInstancia(instancia instanciaActual) {
        WR_actividad Resultado = new WR_actividad();
        if (instanciaActual.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador de la instancia ingresada es invalido");
        } else {
            if (instanciaActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador de la instancia ingresada es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el identificador de la instancia introducida tenga el formato
     * deseado. El objetivo de este metodo es validar que el atributo id de la
     * instancia introducida como parametro en la operacion cerrarInstancia sea
     * un numero natural.
     *
     * @param instanciaActual objeto de la clase instancia cuyo identificador
     * debe ser un numero natural
     * @param sesionActual objeto de la clase sesion cuyo identificador debe ser
     * un numero natural
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarCerrarInstacia(instancia instanciaActual, sesion sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        if (instanciaActual.getId() == null || sesionActual == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador de la instancia introducida es invalido");
        } else {
            if (instanciaActual.getId() < 0 || sesionActual.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador de la instancia introducida es invalido");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
    
    /**
     * Valida que el identificador del proceso introducio tenga el formato
     * deseado. El objetivo de este metodo es validar que el atributo id del
     * proceso introducida como parametro en la operacion obtenerTareasIniciales
     * sea un numero natural.
     *
     * @param procesoActual objeto de la clase proceso cuyo identificador
     * debe ser un numero natural
     * @return objeto de la clase WR_tarea que informa sobre el resultado de
     * la validacion
     * @see WR_tarea
     */
    public WR_tarea validarObtenerTareasIniciales(proceso procesoActual){
        WR_tarea Resultado = new WR_tarea();
        if(procesoActual.getId()==null){
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("El identificador del proceso introducido es invalido");
        }else{
            if(procesoActual.getId()<0){
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("El identificador del proceso introducido es invalido");
            }else{
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
