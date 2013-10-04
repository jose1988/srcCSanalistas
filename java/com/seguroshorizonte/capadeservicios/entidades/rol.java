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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "rol.findAll", query = "SELECT r FROM rol r"),
    @NamedQuery(name = "rol.findByNombre", query = "SELECT r FROM rol r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "rol.findByDescripcion", query = "SELECT r FROM rol r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "rol.findByDocumentacion", query = "SELECT r FROM rol r WHERE r.documentacion = :documentacion"),
    @NamedQuery(name = "rol.findByEstado", query = "SELECT r FROM rol r WHERE r.estado = :estado"),
    @NamedQuery(name = "rol.findById", query = "SELECT r FROM rol r WHERE r.id = :id"),
    @NamedQuery(name = "rol.findByBorrado", query = "SELECT r FROM rol r WHERE r.borrado = :borrado")})
public class rol implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<reporte_rol> reporterolCollection;
    @OneToMany(mappedBy = "idRol")
    private Collection<destinatario> destinatarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<usuario_grupo_rol> usuariogruporolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<tarea_rol> tarearolCollection;
    @JoinColumn(name = "id_clasificacion_rol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private clasificacion_rol idClasificacionRol;

    public rol() {
    }

    public rol(Long id) {
        this.id = id;
    }

    public rol(Long id, String nombre, String descripcion, String estado, boolean borrado) {
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
    public Collection<reporte_rol> getReporterolCollection() {
        return reporterolCollection;
    }

    public void setReporterolCollection(Collection<reporte_rol> reporterolCollection) {
        this.reporterolCollection = reporterolCollection;
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
    public Collection<tarea_rol> getTarearolCollection() {
        return tarearolCollection;
    }

    public void setTarearolCollection(Collection<tarea_rol> tarearolCollection) {
        this.tarearolCollection = tarearolCollection;
    }

    public clasificacion_rol getIdClasificacionRol() {
        return idClasificacionRol;
    }

    public void setIdClasificacionRol(clasificacion_rol idClasificacionRol) {
        this.idClasificacionRol = idClasificacionRol;
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
        if (!(object instanceof rol)) {
            return false;
        }
        rol other = (rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.rol[ id=" + id + " ]";
    }
    
}
