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
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "documento.findAll", query = "SELECT d FROM documento d"),
    @NamedQuery(name = "documento.findByNombre", query = "SELECT d FROM documento d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "documento.findByProposito", query = "SELECT d FROM documento d WHERE d.proposito = :proposito"),
    @NamedQuery(name = "documento.findByObservacion", query = "SELECT d FROM documento d WHERE d.observacion = :observacion"),
    @NamedQuery(name = "documento.findByRuta", query = "SELECT d FROM documento d WHERE d.ruta = :ruta"),
    @NamedQuery(name = "documento.findByFechaCreacion", query = "SELECT d FROM documento d WHERE d.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "documento.findByFechaDocumento", query = "SELECT d FROM documento d WHERE d.fechaDocumento = :fechaDocumento"),
    @NamedQuery(name = "documento.findById", query = "SELECT d FROM documento d WHERE d.id = :id"),
    @NamedQuery(name = "documento.findByBorrado", query = "SELECT d FROM documento d WHERE d.borrado = :borrado")})
public class documento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "proposito")
    private String proposito;
    @Size(max = 150)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_documento")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private actividad idActividad;

    public documento() {
    }

    public documento(Long id) {
        this.id = id;
    }

    public documento(Long id, String nombre, String proposito, String ruta, Date fechaCreacion, Date fechaDocumento, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.proposito = proposito;
        this.ruta = ruta;
        this.fechaCreacion = fechaCreacion;
        this.fechaDocumento = fechaDocumento;
        this.borrado = borrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
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
        if (!(object instanceof documento)) {
            return false;
        }
        documento other = (documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.documento[ id=" + id + " ]";
    }
    
}
