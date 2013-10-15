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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "clasificacion_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clasificacion_rol.findAll", query = "SELECT c FROM clasificacion_rol c"),
    @NamedQuery(name = "clasificacion_rol.findByNombre", query = "SELECT c FROM clasificacion_rol c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "clasificacion_rol.findByDescripcion", query = "SELECT c FROM clasificacion_rol c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "clasificacion_rol.findByFechaCreacion", query = "SELECT c FROM clasificacion_rol c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "clasificacion_rol.findByFechaModificacion", query = "SELECT c FROM clasificacion_rol c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "clasificacion_rol.findById", query = "SELECT c FROM clasificacion_rol c WHERE c.id = :id"),
    @NamedQuery(name = "clasificacion_rol.findByBorrado", query = "SELECT c FROM clasificacion_rol c WHERE c.borrado = :borrado")})
public class clasificacion_rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClasificacionRol")
    private Collection<rol> rolCollection;

    public clasificacion_rol() {
    }

    public clasificacion_rol(Long id) {
        this.id = id;
    }

    public clasificacion_rol(Long id, String nombre, Date fechaCreacion, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.borrado = borrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    @XmlTransient
    public Collection<rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<rol> rolCollection) {
        this.rolCollection = rolCollection;
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
        if (!(object instanceof clasificacion_rol)) {
            return false;
        }
        clasificacion_rol other = (clasificacion_rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.clasificacion_rol[ id=" + id + " ]";
    }
    
}
