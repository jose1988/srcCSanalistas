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
@Table(name = "equivalencia_tiempo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "equivalencia_tiempo.findAll", query = "SELECT e FROM equivalencia_tiempo e"),
    @NamedQuery(name = "equivalencia_tiempo.findByNombre", query = "SELECT e FROM equivalencia_tiempo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "equivalencia_tiempo.findByDescripcion", query = "SELECT e FROM equivalencia_tiempo e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "equivalencia_tiempo.findByMinutos", query = "SELECT e FROM equivalencia_tiempo e WHERE e.minutos = :minutos"),
    @NamedQuery(name = "equivalencia_tiempo.findById", query = "SELECT e FROM equivalencia_tiempo e WHERE e.id = :id"),
    @NamedQuery(name = "equivalencia_tiempo.findByBorrado", query = "SELECT e FROM equivalencia_tiempo e WHERE e.borrado = :borrado")})
public class equivalencia_tiempo implements Serializable {
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
    @Column(name = "minutos")
    private long minutos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquivalenciasTiempo")
    private Collection<actividad> actividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquivalenciasTiempo")
    private Collection<proceso> procesoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquivalenciaTiempo")
    private Collection<tarea> tareaCollection;

    public equivalencia_tiempo() {
    }

    public equivalencia_tiempo(Long id) {
        this.id = id;
    }

    public equivalencia_tiempo(Long id, String nombre, long minutos, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.minutos = minutos;
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

    public long getMinutos() {
        return minutos;
    }

    public void setMinutos(long minutos) {
        this.minutos = minutos;
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
        if (!(object instanceof equivalencia_tiempo)) {
            return false;
        }
        equivalencia_tiempo other = (equivalencia_tiempo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.equivalencia_tiempo[ id=" + id + " ]";
    }
    
}
