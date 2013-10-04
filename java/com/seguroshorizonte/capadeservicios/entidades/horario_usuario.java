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
@Table(name = "horario_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "horario_usuario.findAll", query = "SELECT g FROM horario_usuario g"),
    @NamedQuery(name = "horario_usuario.findById", query = "SELECT g FROM horario_usuario g WHERE g.id = :id"),
    @NamedQuery(name = "horario_usuario.findByBorrado", query = "SELECT g FROM horario_usuario g WHERE g.borrado = :borrado")})
public class horario_usuario implements Serializable {
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
    @JoinColumn(name = "id_horario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private horario idHorario;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuario;

    public horario_usuario() {
    }

    public horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(horario idHorario) {
        this.idHorario = idHorario;
    }

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public horario_usuario(Long id) {
        this.id = id;
    }

    public horario_usuario(Long id, boolean borrado) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof horario_usuario)) {
            return false;
        }
        horario_usuario other = (horario_usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.horario_usuario[ id=" + id + " ]";
    }
}
