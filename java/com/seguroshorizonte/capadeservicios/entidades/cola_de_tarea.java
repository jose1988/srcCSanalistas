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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "cola_de_tarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "cola_de_tarea.findAll", query = "SELECT c FROM cola_de_tarea c"),
    @NamedQuery(name = "cola_de_tarea.findById", query = "SELECT c FROM cola_de_tarea c WHERE c.id = :id"),
    @NamedQuery(name = "cola_de_tarea.findByBorrado", query = "SELECT c FROM cola_de_tarea c WHERE c.borrado = :borrado")})
public class cola_de_tarea implements Serializable {
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
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private grupo idGrupo;
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
    @OneToOne(optional = false)
    private actividad idActividad;

    public cola_de_tarea() {
    }

    public cola_de_tarea(Long id) {
        this.id = id;
    }

    public cola_de_tarea(Long id, boolean borrado) {
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

    public grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(actividad idActividad) {
        this.idActividad = idActividad;
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
        if (!(object instanceof cola_de_tarea)) {
            return false;
        }
        cola_de_tarea other = (cola_de_tarea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.cola_de_tarea[ id=" + id + " ]";
    }
    
}
