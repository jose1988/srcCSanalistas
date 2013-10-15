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
@Table(name = "usuario_grupo_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "usuario_grupo_rol.findAll", query = "SELECT u FROM usuario_grupo_rol u"),
    @NamedQuery(name = "usuario_grupo_rol.findByCarga", query = "SELECT u FROM usuario_grupo_rol u WHERE u.carga = :carga"),
    @NamedQuery(name = "usuario_grupo_rol.findById", query = "SELECT u FROM usuario_grupo_rol u WHERE u.id = :id"),
    @NamedQuery(name = "usuario_grupo_rol.findByUser", query = "SELECT distinct(u.idGrupo) FROM usuario_grupo_rol u WHERE u.idUsuario = :idusr"),
    @NamedQuery(name = "usuario_grupo_rol.findByGrupo", query = "SELECT u FROM usuario_grupo_rol u WHERE u.idGrupo = :idGrupo AND u.borrado = :borrado"),
    @NamedQuery(name = "usuario_grupo_rol.findByRol", query = "SELECT distinct(u.idRol) FROM usuario_grupo_rol u WHERE u.idGrupo = :idGrupo AND u.borrado = :borrado"),
    @NamedQuery(name = "usuario_grupo_rol.findByRolGrupo", query = "SELECT u FROM usuario_grupo_rol u WHERE u.idGrupo = :idGrupo AND u.idRol = :idRol"),
    @NamedQuery(name = "usuario_grupo_rol.findByBorrado", query = "SELECT u FROM usuario_grupo_rol u WHERE u.borrado = :borrado")})
public class usuario_grupo_rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "carga")
    private Integer carga;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuario;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private rol idRol;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private grupo idGrupo;

    public usuario_grupo_rol() {
    }

    public usuario_grupo_rol(Long id) {
        this.id = id;
    }

    public usuario_grupo_rol(Long id, boolean borrado) {
        this.id = id;
        this.borrado = borrado;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
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

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public rol getIdRol() {
        return idRol;
    }

    public void setIdRol(rol idRol) {
        this.idRol = idRol;
    }

    public grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(grupo idGrupo) {
        this.idGrupo = idGrupo;
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
        if (!(object instanceof usuario_grupo_rol)) {
            return false;
        }
        usuario_grupo_rol other = (usuario_grupo_rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.usuario_grupo_rol[ id=" + id + " ]";
    }
}
