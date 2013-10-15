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
@Table(name = "clasificacion_proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "clasificacion_proceso.findAll", query = "SELECT c FROM clasificacion_proceso c"),
    @NamedQuery(name = "clasificacion_proceso.findByNombre", query = "SELECT c FROM clasificacion_proceso c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "clasificacion_proceso.findByDescripcion", query = "SELECT c FROM clasificacion_proceso c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "clasificacion_proceso.findByFechaCreacion", query = "SELECT c FROM clasificacion_proceso c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "clasificacion_proceso.findByFechaModificacion", query = "SELECT c FROM clasificacion_proceso c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "clasificacion_proceso.findById", query = "SELECT c FROM clasificacion_proceso c WHERE c.id = :id"),
    @NamedQuery(name = "clasificacion_proceso.findByBorrado", query = "SELECT c FROM clasificacion_proceso c WHERE c.borrado = :borrado")})
public class clasificacion_proceso implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClasificacionProceso")
    private Collection<proceso> procesoCollection;

    public clasificacion_proceso() {
    }

    public clasificacion_proceso(Long id) {
        this.id = id;
    }

    public clasificacion_proceso(Long id, String nombre, Date fechaCreacion, boolean borrado) {
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
    public Collection<proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
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
        if (!(object instanceof clasificacion_proceso)) {
            return false;
        }
        clasificacion_proceso other = (clasificacion_proceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.clasificacion_proceso[ id=" + id + " ]";
    }
    
}
