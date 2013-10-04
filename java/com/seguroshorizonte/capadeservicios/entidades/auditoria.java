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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "auditoria.findAll", query = "SELECT a FROM auditoria a"),
    @NamedQuery(name = "auditoria.findByFechaAcceso", query = "SELECT a FROM auditoria a WHERE a.fechaAcceso = :fechaAcceso"),
    @NamedQuery(name = "auditoria.findByFechaSalida", query = "SELECT a FROM auditoria a WHERE a.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "auditoria.findById", query = "SELECT a FROM auditoria a WHERE a.id = :id"),
    @NamedQuery(name = "auditoria.findByBorrado", query = "SELECT a FROM auditoria a WHERE a.borrado = :borrado")})
public class auditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_acceso")
    @Temporal(TemporalType.DATE)
    private Date fechaAcceso;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_sesion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private sesion idSesion;
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private actividad idActividad;

    public auditoria() {
    }

    public auditoria(Long id) {
        this.id = id;
    }

    public auditoria(Long id, Date fechaAcceso, boolean borrado) {
        this.id = id;
        this.fechaAcceso = fechaAcceso;
        this.borrado = borrado;
    }

    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
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

    public sesion getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(sesion idSesion) {
        this.idSesion = idSesion;
    }

    public actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(actividad idActividad) {
        this.idActividad = idActividad;
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
        if (!(object instanceof auditoria)) {
            return false;
        }
        auditoria other = (auditoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.auditoria[ id=" + id + " ]";
    }
    
}
