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
@Table(name = "condicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "condicion.findAll", query = "SELECT c FROM condicion c"),
    @NamedQuery(name = "condicion.findByNombre", query = "SELECT c FROM condicion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "condicion.findByDescripcion", query = "SELECT c FROM condicion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "condicion.findByDocumentacion", query = "SELECT c FROM condicion c WHERE c.documentacion = :documentacion"),
    @NamedQuery(name = "condicion.findByEstado", query = "SELECT c FROM condicion c WHERE c.estado = :estado"),
    @NamedQuery(name = "condicion.findById", query = "SELECT c FROM condicion c WHERE c.id = :id"),
    @NamedQuery(name = "condicion.findByBorrado", query = "SELECT c FROM condicion c WHERE c.borrado = :borrado")})
public class condicion implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondicion")
    private Collection<transicion> transicionCollection;

    public condicion() {
    }

    public condicion(Long id) {
        this.id = id;
    }

    public condicion(Long id, String nombre, String descripcion, String estado, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    public Collection<transicion> getTransicionCollection() {
        return transicionCollection;
    }

    public void setTransicionCollection(Collection<transicion> transicionCollection) {
        this.transicionCollection = transicionCollection;
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
        if (!(object instanceof condicion)) {
            return false;
        }
        condicion other = (condicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.condicion[ id=" + id + " ]";
    }
    
}
