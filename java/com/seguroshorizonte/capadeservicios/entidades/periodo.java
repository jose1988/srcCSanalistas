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
@Table(name = "periodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "periodo.findAll", query = "SELECT p FROM periodo p"),
    @NamedQuery(name = "periodo.findByDescripcion", query = "SELECT p FROM periodo p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "periodo.findByFechaDesde", query = "SELECT p FROM periodo p WHERE p.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "periodo.findByFechaHasta", query = "SELECT p FROM periodo p WHERE p.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "periodo.findByEstado", query = "SELECT p FROM periodo p WHERE p.estado = :estado"),
    @NamedQuery(name = "periodo.findById", query = "SELECT p FROM periodo p WHERE p.id = :id"),
    @NamedQuery(name = "periodo.findByBorrado", query = "SELECT p FROM periodo p WHERE p.borrado = :borrado")})
public class periodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodo")
    private Collection<periodo_grupo_proceso> periodogrupoprocesoCollection;

    public periodo() {
    }

    public periodo(Long id) {
        this.id = id;
    }

    public periodo(Long id, String descripcion, Date fechaDesde, Date fechaHasta, String estado, boolean borrado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.estado = estado;
        this.borrado = borrado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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
    public Collection<periodo_grupo_proceso> getPeriodogrupoprocesoCollection() {
        return periodogrupoprocesoCollection;
    }

    public void setPeriodogrupoprocesoCollection(Collection<periodo_grupo_proceso> periodogrupoprocesoCollection) {
        this.periodogrupoprocesoCollection = periodogrupoprocesoCollection;
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
        if (!(object instanceof periodo)) {
            return false;
        }
        periodo other = (periodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.periodo[ id=" + id + " ]";
    }
    
}
