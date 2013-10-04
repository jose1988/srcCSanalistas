/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "destinatario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "destinatario.findAll", query = "SELECT d FROM destinatario d"),
    @NamedQuery(name = "destinatario.findByFiltro", query = "SELECT d FROM destinatario d WHERE d.filtro = :filtro"),
    @NamedQuery(name = "destinatario.findByEstado", query = "SELECT d FROM destinatario d WHERE d.estado = :estado"),
    @NamedQuery(name = "destinatario.findByFechaCreacion", query = "SELECT d FROM destinatario d WHERE d.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "destinatario.findByFechaFinalizacion", query = "SELECT d FROM destinatario d WHERE d.fechaFinalizacion = :fechaFinalizacion"),
    @NamedQuery(name = "destinatario.findById", query = "SELECT d FROM destinatario d WHERE d.id = :id"),
    @NamedQuery(name = "destinatario.findByBorrado", query = "SELECT d FROM destinatario d WHERE d.borrado = :borrado")})
public class destinatario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "filtro")
    private String filtro;
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
    @Column(name = "fecha_finalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private usuario idUsuario;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne
    private rol idRol;
    @JoinColumn(name = "id_prioridad_mensaje_en_linea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private prioridad_mensaje_en_linea idPrioridadMensajeEnLinea;
    @JoinColumn(name = "id_mensaje", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private mensaje idMensaje;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne
    private grupo idGrupo;

    public destinatario() {
    }

    public destinatario(Long id) {
        this.id = id;
    }

    public destinatario(Long id, String filtro, String estado, Date fechaCreacion, Date fechaFinalizacion, boolean borrado) {
        this.id = id;
        this.filtro = filtro;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.borrado = borrado;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
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

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public rol getIdRol() {
        return idRol;
    }

    public void setIdRol(rol idRol) {
        this.idRol = idRol;
    }

    public prioridad_mensaje_en_linea getIdPrioridadMensajeEnLinea() {
        return idPrioridadMensajeEnLinea;
    }

    public void setIdPrioridadMensajeEnLinea(prioridad_mensaje_en_linea idPrioridadMensajeEnLinea) {
        this.idPrioridadMensajeEnLinea = idPrioridadMensajeEnLinea;
    }

    public mensaje getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(mensaje idMensaje) {
        this.idMensaje = idMensaje;
    }

    public grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(grupo idGrupo) {
        this.idGrupo = idGrupo;
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
        if (!(object instanceof destinatario)) {
            return false;
        }
        destinatario other = (destinatario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.destinatario[ id=" + id + " ]";
    }
    
}
