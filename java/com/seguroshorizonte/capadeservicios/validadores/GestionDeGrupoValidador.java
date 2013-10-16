
package com.seguroshorizonte.capadeservicios.validadores;
import com.seguroshorizonte.capadeservicios.entidades.grupo;
import com.seguroshorizonte.capadeservicios.entidades.rol;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_resultado;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_rol;
import com.seguroshorizonte.capadeservicios.envoltorios.WR_usuario_grupo_rol;

/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio Gestion_De_Grupo. el
 * objetivo de esta clase solo es verificar que los atributos necesarios de los
 * objetos ingresados existen y tienen un formato correcto, no se verificara la
 * coherencia de los datos con el sistema de base de datos ni la congruencia
 * entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class GestionDeGrupoValidador {

    /**
     * Valida que el grupo ingresado posea el atributo id valido. El objetivo de
     * este metodo es validar que el objeto como parametro a la operacion
     * listarRolesPorGrupo tengan el formato adecuado.
     *
     * @param idGrupo objeto de la clase grupo cuyo id debe ser un numero
     * natural
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_rol validarListarRolesPorGrupo(grupo idGrupo) {
        WR_rol Resultado = new WR_rol();
        if (idGrupo.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valores invalidos en el atributo 'id' asociado al objeto grupo");
        } else {
            if (idGrupo.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valores invalidos en el atributo 'id' asociado al objeto grupo");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }

    /**
     * Valida que el grupo y rol ingresados posean el atributo id valido. El objetivo de
     * este metodo es validar que los objetos como parametros a la operacion
     * listarUsuariosPorGrupoYRol tengan el formato adecuado.
     *
     * @param idGrupo objeto de la clase grupo cuyo id debe ser un numero
     * natural
     * @param idRol objeto de la clase rol cuyo id debe ser un numero
     * natural
     * @return objeto de la clase WR_resultado que informa del resultado de la
     * validacion
     * @see WR_resultado
     */
    public WR_usuario_grupo_rol ValidarListarUsuariosPorGrupoYRol(grupo idGrupo, rol idRol) {
        WR_usuario_grupo_rol Resultado = new WR_usuario_grupo_rol();
        if (idGrupo.getId() == null || idRol.getId() == null) {
            Resultado.setEstatus("FAIL");
            Resultado.setObservacion("Valores invalidos en los atributos 'id' asociados a los objetos ingresados");
        } else {
            if (idGrupo.getId() < 0 || idRol.getId() < 0) {
                Resultado.setEstatus("FAIL");
                Resultado.setObservacion("Valores invalidos en los atributos 'id' asociados a los objetos ingresados");
            } else {
                Resultado.setEstatus("OK");
            }
        }
        return Resultado;
    }
}
