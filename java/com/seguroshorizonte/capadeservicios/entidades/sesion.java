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
@Table(name = "sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "sesion.findAll", query = "SELECT s FROM sesion s"),
    @NamedQuery(name = "sesion.findByEstado", query = "SELECT s FROM sesion s WHERE s.estado = :estado"),
    @NamedQuery(name = "sesion.findByFechaCreacion", query = "SELECT s FROM sesion s WHERE s.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "sesion.findByFechaFinalizacion", query = "SELECT s FROM sesion s WHERE s.fechaFinalizacion = :fechaFinalizacion"),
    @NamedQuery(name = "sesion.findByIp", query = "SELECT s FROM sesion s WHERE s.ip = :ip"),
    @NamedQuery(name = "sesion.findById", query = "SELECT s FROM sesion s WHERE s.id = :id"),
    @NamedQuery(name = "sesion.findByBorrado", query = "SELECT s FROM sesion s WHERE s.borrado = :borrado")})
public class sesion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fecha_finalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ip")
    private String ip;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSesion")
    private Collection<auditoria> auditoriaCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuario;

    public sesion() {
    }

    public sesion(Long id) {
        this.id = id;
    }

    public sesion(Long id, String estado, Date fechaCreacion, String ip, boolean borrado) {
        this.id = id;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.ip = ip;
        this.borrado = borrado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
    public Collection<auditoria> getAuditoriaCollection() {
        return auditoriaCollection;
    }

    public void setAuditoriaCollection(Collection<auditoria> auditoriaCollection) {
        this.auditoriaCollection = auditoriaCollection;
    }

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof sesion)) {
            return false;
        }
        sesion other = (sesion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.sesion[ id=" + id + " ]";
    }
    
}
