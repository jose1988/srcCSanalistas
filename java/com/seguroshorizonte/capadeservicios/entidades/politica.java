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
@Table(name = "politica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "politica.findAll", query = "SELECT p FROM politica p"),
    @NamedQuery(name = "politica.findByNombre", query = "SELECT p FROM politica p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "politica.findByDescripcion", query = "SELECT p FROM politica p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "politica.findByDocumentacion", query = "SELECT p FROM politica p WHERE p.documentacion = :documentacion"),
    @NamedQuery(name = "politica.findByImplementacion", query = "SELECT p FROM politica p WHERE p.implementacion = :implementacion"),
    @NamedQuery(name = "politica.findByEstado", query = "SELECT p FROM politica p WHERE p.estado = :estado"),
    @NamedQuery(name = "politica.findById", query = "SELECT p FROM politica p WHERE p.id = :id"),
    @NamedQuery(name = "politica.findByBorrado", query = "SELECT p FROM politica p WHERE p.borrado = :borrado")})
public class politica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "documentacion")
    private String documentacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 750)
    @Column(name = "implementacion")
    private String implementacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPolitica")
    private Collection<grupo_politica_tarea> grupopoliticatareaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPolitica")
    private Collection<tarea> tareaCollection;

    public politica() {
    }

    public politica(Long id) {
        this.id = id;
    }

    public politica(Long id, String nombre, String descripcion, String implementacion, String estado, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.implementacion = implementacion;
        this.estado = estado;
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

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getImplementacion() {
        return implementacion;
    }

    public void setImplementacion(String implementacion) {
        this.implementacion = implementacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    public Collection<grupo_politica_tarea> getGrupopoliticatareaCollection() {
        return grupopoliticatareaCollection;
    }

    public void setGrupopoliticatareaCollection(Collection<grupo_politica_tarea> grupopoliticatareaCollection) {
        this.grupopoliticatareaCollection = grupopoliticatareaCollection;
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
        if (!(object instanceof politica)) {
            return false;
        }
        politica other = (politica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.politica[ id=" + id + " ]";
    }
    
}
