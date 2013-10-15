/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "usuario.findAll", query = "SELECT u FROM usuario u"),
    @NamedQuery(name = "usuario.findByClave", query = "SELECT u FROM usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "usuario.findByPrimerNombre", query = "SELECT u FROM usuario u WHERE u.primerNombre = :primerNombre"),
    @NamedQuery(name = "usuario.findBySegundoNombre", query = "SELECT u FROM usuario u WHERE u.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "usuario.findByPrimerApellido", query = "SELECT u FROM usuario u WHERE u.primerApellido = :primerApellido"),
    @NamedQuery(name = "usuario.findBySegundoApellido", query = "SELECT u FROM usuario u WHERE u.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "usuario.findByCedula", query = "SELECT u FROM usuario u WHERE u.cedula = :cedula"),
    @NamedQuery(name = "usuario.findByRif", query = "SELECT u FROM usuario u WHERE u.rif = :rif"),
    @NamedQuery(name = "usuario.findByTelefonosPersonal", query = "SELECT u FROM usuario u WHERE u.telefonosPersonal = :telefonosPersonal"),
    @NamedQuery(name = "usuario.findByTelefonosOficina", query = "SELECT u FROM usuario u WHERE u.telefonosOficina = :telefonosOficina"),
    @NamedQuery(name = "usuario.findByMail", query = "SELECT u FROM usuario u WHERE u.mail = :mail"),
    @NamedQuery(name = "usuario.findByFax", query = "SELECT u FROM usuario u WHERE u.fax = :fax"),
    @NamedQuery(name = "usuario.findByDireccionPersonal", query = "SELECT u FROM usuario u WHERE u.direccionPersonal = :direccionPersonal"),
    @NamedQuery(name = "usuario.findByDireccionOficina", query = "SELECT u FROM usuario u WHERE u.direccionOficina = :direccionOficina"),
    @NamedQuery(name = "usuario.findByDescripcion", query = "SELECT u FROM usuario u WHERE u.descripcion = :descripcion"),
    @NamedQuery(name = "usuario.findByEstado", query = "SELECT u FROM usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "usuario.findByFechaCreacion", query = "SELECT u FROM usuario u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "usuario.findByFechaActualizacionClave", query = "SELECT u FROM usuario u WHERE u.fechaActualizacionClave = :fechaActualizacionClave"),
    @NamedQuery(name = "usuario.findByDiasValidezClave", query = "SELECT u FROM usuario u WHERE u.diasValidezClave = :diasValidezClave"),
    @NamedQuery(name = "usuario.findById", query = "SELECT u FROM usuario u WHERE u.id = :id"),
    @NamedQuery(name = "usuario.findByBorrado", query = "SELECT u FROM usuario u WHERE u.borrado = :borrado")})

public class usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 20)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 20)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 20)
    @Column(name = "rif")
    private String rif;
    @Size(max = 20)
    @Column(name = "telefonos_personal")
    private String telefonosPersonal;
    @Size(max = 20)
    @Column(name = "telefonos_oficina")
    private String telefonosOficina;
    @Size(max = 20)
    @Column(name = "mail")
    private String mail;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "direccion_personal")
    private String direccionPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "direccion_oficina")
    private String direccionOficina;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_actualizacion_clave")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacionClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias_validez_clave")
    private int diasValidezClave;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_skin", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private skin idSkin;
    @JoinColumn(name = "id_organizacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private organizacion idOrganizacion;
    @JoinColumn(name = "id_clasificacion_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private clasificacion_usuario idClasificacionUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioOrigen")
    private Collection<actividad> actividadOrigenCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idUsuario")
    private Collection<actividad> actividadUsuarioCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<destinatario> destinatarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<usuario_grupo_rol> usuariogruporolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<instancia> instanciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<politica_round_robin> politicaroundrobinCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<bandeja> bandejaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "de")
    private Collection<post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<post_en_bandeja> postenbandejaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<mensaje> mensajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<sesion> sesionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<horario_usuario> horarioCollection;

    @XmlTransient
    public Collection<horario_usuario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<horario_usuario> horarioCollection) {
        this.horarioCollection = horarioCollection;
    }

    public usuario() {
    }

    public usuario(String id) {
        this.id = id;
    }

    public usuario(String id, String clave, String primerNombre, String primerApellido, String cedula, String direccionPersonal, String direccionOficina, String estado, Date fechaCreacion, Date fechaActualizacionClave, int diasValidezClave, boolean borrado) {
        this.id = id;
        this.clave = clave;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.cedula = cedula;
        this.direccionPersonal = direccionPersonal;
        this.direccionOficina = direccionOficina;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacionClave = fechaActualizacionClave;
        this.diasValidezClave = diasValidezClave;
        this.borrado = borrado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getTelefonosPersonal() {
        return telefonosPersonal;
    }

    public void setTelefonosPersonal(String telefonosPersonal) {
        this.telefonosPersonal = telefonosPersonal;
    }

    public String getTelefonosOficina() {
        return telefonosOficina;
    }

    public void setTelefonosOficina(String telefonosOficina) {
        this.telefonosOficina = telefonosOficina;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDireccionPersonal() {
        return direccionPersonal;
    }

    public void setDireccionPersonal(String direccionPersonal) {
        this.direccionPersonal = direccionPersonal;
    }

    public String getDireccionOficina() {
        return direccionOficina;
    }

    public void setDireccionOficina(String direccionOficina) {
        this.direccionOficina = direccionOficina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacionClave() {
        return fechaActualizacionClave;
    }

    public void setFechaActualizacionClave(Date fechaActualizacionClave) {
        this.fechaActualizacionClave = fechaActualizacionClave;
    }

    public int getDiasValidezClave() {
        return diasValidezClave;
    }

    public void setDiasValidezClave(int diasValidezClave) {
        this.diasValidezClave = diasValidezClave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public skin getIdSkin() {
        return idSkin;
    }

    public void setIdSkin(skin idSkin) {
        this.idSkin = idSkin;
    }

    public organizacion getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(organizacion idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public clasificacion_usuario getIdClasificacionUsuario() {
        return idClasificacionUsuario;
    }

    public void setIdClasificacionUsuario(clasificacion_usuario idClasificacionUsuario) {
        this.idClasificacionUsuario = idClasificacionUsuario;
    }

    @XmlTransient
    public Collection<actividad> getActividadOrigenCollection() {
        return actividadOrigenCollection;
    }

    public void setActividadOrigenCollection(Collection<actividad> actividadOrigenCollection) {
        this.actividadOrigenCollection = actividadOrigenCollection;
    }

    @XmlTransient
    public Collection<actividad> getActividadUsuarioCollection() {
        return actividadUsuarioCollection;
    }

    public void setActividadUsuarioCollection(Collection<actividad> actividadUsuarioCollection) {
        this.actividadUsuarioCollection = actividadUsuarioCollection;
    }

    @XmlTransient
    public Collection<destinatario> getDestinatarioCollection() {
        return destinatarioCollection;
    }

    public void setDestinatarioCollection(Collection<destinatario> destinatarioCollection) {
        this.destinatarioCollection = destinatarioCollection;
    }

    @XmlTransient
    public Collection<usuario_grupo_rol> getUsuariogruporolCollection() {
        return usuariogruporolCollection;
    }

    public void setUsuariogruporolCollection(Collection<usuario_grupo_rol> usuariogruporolCollection) {
        this.usuariogruporolCollection = usuariogruporolCollection;
    }

    @XmlTransient
    public Collection<instancia> getInstanciaCollection() {
        return instanciaCollection;
    }

    public void setInstanciaCollection(Collection<instancia> instanciaCollection) {
        this.instanciaCollection = instanciaCollection;
    }

    @XmlTransient
    public Collection<politica_round_robin> getPoliticaroundrobinCollection() {
        return politicaroundrobinCollection;
    }

    public void setPoliticaroundrobinCollection(Collection<politica_round_robin> politicaroundrobinCollection) {
        this.politicaroundrobinCollection = politicaroundrobinCollection;
    }

    @XmlTransient
    public Collection<bandeja> getBandejaCollection() {
        return bandejaCollection;
    }

    public void setBandejaCollection(Collection<bandeja> bandejaCollection) {
        this.bandejaCollection = bandejaCollection;
    }

    @XmlTransient
    public Collection<post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    public Collection<post_en_bandeja> getPostenbandejaCollection() {
        return postenbandejaCollection;
    }

    public void setPostenbandejaCollection(Collection<post_en_bandeja> postenbandejaCollection) {
        this.postenbandejaCollection = postenbandejaCollection;
    }

    @XmlTransient
    public Collection<mensaje> getMensajeCollection() {
        return mensajeCollection;
    }

    public void setMensajeCollection(Collection<mensaje> mensajeCollection) {
        this.mensajeCollection = mensajeCollection;
    }

    @XmlTransient
    public Collection<sesion> getSesionCollection() {
        return sesionCollection;
    }

    public void setSesionCollection(Collection<sesion> sesionCollection) {
        this.sesionCollection = sesionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof usuario)) {
            return false;
        }
        usuario other = (usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.usuario[ id=" + id + " ]";
    }
    
}
