/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "tarea_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "tarea_rol.findAll", query = "SELECT t FROM tarea_rol t"),
    @NamedQuery(name = "tarea_rol.findById", query = "SELECT t FROM tarea_rol t WHERE t.id = :id"),
    @NamedQuery(name = "tarea_rol.findByBorrado", query = "SELECT t FROM tarea_rol t WHERE t.borrado = :borrado")})
public class tarea_rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private tarea idTarea;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private rol idRol;

    public tarea_rol() {
    }

    public tarea_rol(Long id) {
        this.id = id;
    }

    public tarea_rol(Long id, boolean borrado) {
        this.id = id;
        this.borrado = borrado;
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

    public tarea getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(tarea idTarea) {
        this.idTarea = idTarea;
    }

    public rol getIdRol() {
        return idRol;
    }

    public void setIdRol(rol idRol) {
        this.idRol = idRol;
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
        if (!(object instanceof tarea_rol)) {
            return false;
        }
        tarea_rol other = (tarea_rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.tarea_rol[ id=" + id + " ]";
    }
    
}
