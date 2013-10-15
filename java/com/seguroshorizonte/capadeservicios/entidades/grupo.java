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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "grupo.findAll", query = "SELECT g FROM grupo g ORDER BY g.nombre"),
    @NamedQuery(name = "grupo.findByNombre", query = "SELECT g FROM grupo g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "grupo.findByDescripcion", query = "SELECT g FROM grupo g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "grupo.findByDocumentacion", query = "SELECT g FROM grupo g WHERE g.documentacion = :documentacion"),
    @NamedQuery(name = "grupo.findByFechaCreacion", query = "SELECT g FROM grupo g WHERE g.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "grupo.findByTipo", query = "SELECT g FROM grupo g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "grupo.findByEstado", query = "SELECT g FROM grupo g WHERE g.estado = :estado"),
    @NamedQuery(name = "grupo.findById", query = "SELECT g FROM grupo g WHERE g.id = :id"),
    @NamedQuery(name = "grupo.findByBorrado", query = "SELECT g FROM grupo g WHERE g.borrado = :borrado")})
public class grupo implements Serializable {
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
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 20)
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
    @OneToMany(mappedBy = "idGrupo")
    private Collection<destinatario> destinatarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<periodo_grupo_proceso> periodogrupoprocesoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<usuario_grupo_rol> usuariogruporolCollection;
    @JoinColumn(name = "id_organizacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private organizacion idOrganizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<grupo_politica_tarea> grupopoliticatareaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<contador_round_robin> contadorroundrobinCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<cola_de_tarea> coladetareaCollection;

    public grupo() {
    }

    public grupo(Long id) {
        this.id = id;
    }

    public grupo(Long id, String nombre, String descripcion, Date fechaCreacion, String tipo, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public Collection<destinatario> getDestinatarioCollection() {
        return destinatarioCollection;
    }

    public void setDestinatarioCollection(Collection<destinatario> destinatarioCollection) {
        this.destinatarioCollection = destinatarioCollection;
    }

    @XmlTransient
    public Collection<periodo_grupo_proceso> getPeriodogrupoprocesoCollection() {
        return periodogrupoprocesoCollection;
    }

    public void setPeriodogrupoprocesoCollection(Collection<periodo_grupo_proceso> periodogrupoprocesoCollection) {
        this.periodogrupoprocesoCollection = periodogrupoprocesoCollection;
    }

    @XmlTransient
    public Collection<usuario_grupo_rol> getUsuariogruporolCollection() {
        return usuariogruporolCollection;
    }

    public void setUsuariogruporolCollection(Collection<usuario_grupo_rol> usuariogruporolCollection) {
        this.usuariogruporolCollection = usuariogruporolCollection;
    }

    public organizacion getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(organizacion idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    @XmlTransient
    public Collection<grupo_politica_tarea> getGrupopoliticatareaCollection() {
        return grupopoliticatareaCollection;
    }

    public void setGrupopoliticatareaCollection(Collection<grupo_politica_tarea> grupopoliticatareaCollection) {
        this.grupopoliticatareaCollection = grupopoliticatareaCollection;
    }

    @XmlTransient
    public Collection<contador_round_robin> getContadorroundrobinCollection() {
        return contadorroundrobinCollection;
    }

    public void setContadorroundrobinCollection(Collection<contador_round_robin> contadorroundrobinCollection) {
        this.contadorroundrobinCollection = contadorroundrobinCollection;
    }

    @XmlTransient
    public Collection<cola_de_tarea> getColadetareaCollection() {
        return coladetareaCollection;
    }

    public void setColadetareaCollection(Collection<cola_de_tarea> coladetareaCollection) {
        this.coladetareaCollection = coladetareaCollection;
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
        if (!(object instanceof grupo)) {
            return false;
        }
        grupo other = (grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.grupo[ id=" + id + " ]";
    }
    
}
