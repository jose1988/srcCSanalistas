/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.servicios;

import com.seguroshorizonte.capadeservicios.beans.reporteFacade;
import com.seguroshorizonte.capadeservicios.beans.rolFacade;
import com.seguroshorizonte.capadeservicios.entidades.reporte;
import com.seguroshorizonte.capadeservicios.entidades.usuario;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_reporte;
import com.seguroshorizonte.capadeservicios.validadores.ReportesValidador;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Esta clase posee las operaciones necesarias para la gestion de reportes
 * externos. Los objetos de la clase reporte creados se visualizaran como un
 * enlace a una direccion externa, y de esta manera podran ser integrados en las
 * interfaces de usuario
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
@EJB(name = "Reportes", beanInterface = Local.class)
@WebService(serviceName = "Reportes")
public class Reportes {

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
    ReportesValidador myValidador = new ReportesValidador();
    @EJB
    reporteFacade myReporteFacade = new reporteFacade();
    @EJB
    rolFacade myRolFacade = new rolFacade();

    /**
     * Retorna informacion detallada sobre un informe determinado.
     *
     * @param reporteActual objeto de la clase reporte cuyo id corresponde al
     * identificador del registro que se desea consultar
     * @return objeto de la clase WR_reporte que informa sobre el resultado de
     * la operacion y que posee en la primera posicion de su lista de reportes
     * la informacion solicitada
     * @see WR_reporte
     */
    @WebMethod(operationName = "ConsultarReporte")
    public WR_reporte ConsultarReporte(@WebParam(name = "reporteActual") reporte reporteActual) {
        WR_reporte Resultado = new WR_reporte();
        Resultado = myValidador.validarConsultarReporte(reporteActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {
            reporteActual = myReporteFacade.find(reporteActual.getId());
            if (reporteActual == null) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Reporte no encontrado");
                return Resultado;
            }
            if (reporteActual.getBorrado()) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Reporte no encontrado");
                return Resultado;
            }
            reporteActual.setReporterolCollection(null);
            Resultado.ingresarReporte(reporteActual);
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
     * Retorna los identificadores de los reportes a los que puede acceder el
     * usuario.
     *
     * @param usuarioActual objeto de la clase usuario cuyos reportes se desean
     * consultar
     *
     * @return objeto de la clase WR_reporte que informa sobre el resultado de
     * la operacion y que posee la lista de reportes que se desean consultar
     * @see WR_reporte
     */
    @WebMethod(operationName = "ConsultarReportesDisponibles")
    public WR_reporte ConsultarReportesDisponibles(@WebParam(name = "usuarioActual") usuario usuarioActual) {
        WR_reporte Resultado = new WR_reporte();
        Resultado = myValidador.validarConsultarReportesDisponibles(usuarioActual);
        if (Resultado.getEstatus().compareTo("OK") != 0) {
            return Resultado;
        }
        try {

            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("WebApplication2PU");
            EntityManager em = emf.createEntityManager();
            List<Long> reportes = (List<Long>) em.createNativeQuery("SELECT   DISTINCT reporte.id FROM  public.usuario,   public.usuario_grupo_rol,   public.rol,    public.reporte_rol,   public.reporte WHERE   usuario_grupo_rol.id_usuario = usuario.id AND   usuario_grupo_rol.id_rol = rol.id AND   reporte_rol.id_rol = rol.id AND   reporte_rol.id_reporte = reporte.id AND   usuario.id = '" + usuarioActual.getId() + "' ORDER BY reporte.id asc   ;").getResultList();
            Iterator iterador = reportes.iterator();
            while (iterador.hasNext()) {
                Resultado.ingresarReporte(new reporte((Long) iterador.next()));
            }
            Resultado.setEstatus("OK");
            Resultado.setObservacion(Resultado.getReportes().size() + " reportes encontrados");

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
