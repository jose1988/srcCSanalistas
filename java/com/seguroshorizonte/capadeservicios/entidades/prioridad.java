/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "prioridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "prioridad.findAll", query = "SELECT p FROM prioridad p"),
    @NamedQuery(name = "prioridad.findByNombre", query = "SELECT p FROM prioridad p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "prioridad.findByDescripcion", query = "SELECT p FROM prioridad p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "prioridad.findById", query = "SELECT p FROM prioridad p WHERE p.id = :id"),
    @NamedQuery(name = "prioridad.findByBorrado", query = "SELECT p FROM prioridad p WHERE p.borrado = :borrado")})
public class prioridad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrioridad")
    private Collection<actividad> actividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrioridad")
    private Collection<proceso> procesoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrioridad")
    private Collection<tarea> tareaCollection;

    public prioridad() {
    }

    public prioridad(Long id) {
        this.id = id;
    }

    public prioridad(Long id, String nombre, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
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
    public Collection<actividad> getActividadCollection() {
        return actividadCollection;
    }

    public void setActividadCollection(Collection<actividad> actividadCollection) {
        this.actividadCollection = actividadCollection;
    }

    @XmlTransient
    public Collection<proceso> getProcesoCollection() {
        return procesoCollection;
    }

    public void setProcesoCollection(Collection<proceso> procesoCollection) {
        this.procesoCollection = procesoCollection;
    }

    @XmlTransient
    public Collection<tarea> getTareaCollection() {
        return tareaCollection;
    }

    public void setTareaCollection(Collection<tarea> tareaCollection) {
        this.tareaCollection = tareaCollection;
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
        if (!(object instanceof prioridad)) {
            return false;
        }
        prioridad other = (prioridad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.prioridad[ id=" + id + " ]";
    }
    
}
