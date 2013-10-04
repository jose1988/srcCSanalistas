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
@Table(name = "instancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "instancia.findAll", query = "SELECT i FROM instancia i"),
    @NamedQuery(name = "instancia.findByReferencia", query = "SELECT i FROM instancia i WHERE i.referencia = :referencia"),
    @NamedQuery(name = "instancia.findByDescripcion", query = "SELECT i FROM instancia i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "instancia.findByEstado", query = "SELECT i FROM instancia i WHERE i.estado = :estado"),
    @NamedQuery(name = "instancia.findByFechaApertura", query = "SELECT i FROM instancia i WHERE i.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "instancia.findByFechaCierre", query = "SELECT i FROM instancia i WHERE i.fechaCierre = :fechaCierre"),
    @NamedQuery(name = "instancia.findById", query = "SELECT i FROM instancia i WHERE i.id = :id"),
    @NamedQuery(name = "instancia.findEstados", query = "SELECT distinct(i.estado) FROM instancia i"),
    @NamedQuery(name = "instancia.findByBorrado", query = "SELECT i FROM instancia i WHERE i.borrado = :borrado")})
public class instancia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstancia")
    private Collection<actividad> actividadCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuario;
    @JoinColumn(name = "id_periodo_grupo_proceso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private periodo_grupo_proceso idPeriodoGrupoProceso;

    public instancia() {
    }

    public instancia(Long id) {
        this.id = id;
    }

    public instancia(Long id, String referencia, String descripcion, String estado, Date fechaApertura, boolean borrado) {
        this.id = id;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
        this.borrado = borrado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
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

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public periodo_grupo_proceso getIdPeriodoGrupoProceso() {
        return idPeriodoGrupoProceso;
    }

    public void setIdPeriodoGrupoProceso(periodo_grupo_proceso idPeriodoGrupoProceso) {
        this.idPeriodoGrupoProceso = idPeriodoGrupoProceso;
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
        if (!(object instanceof instancia)) {
            return false;
        }
        instancia other = (instancia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.instancia[ id=" + id + " ]";
    }
    
}
