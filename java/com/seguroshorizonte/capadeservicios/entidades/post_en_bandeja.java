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
@Table(name = "post_en_bandeja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "post_en_bandeja.findAll", query = "SELECT p FROM post_en_bandeja p"),
    @NamedQuery(name = "post_en_bandeja.findByLeido", query = "SELECT p FROM post_en_bandeja p WHERE p.leido = :leido"),
    @NamedQuery(name = "post_en_bandeja.findById", query = "SELECT p FROM post_en_bandeja p WHERE p.id = :id")})
public class post_en_bandeja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "leido")
    private boolean leido;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuario;
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private post idPost;
    @JoinColumn(name = "id_bandeja", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private bandeja idBandeja;

    public post_en_bandeja() {
    }

    public post_en_bandeja(Long id) {
        this.id = id;
    }

    public post_en_bandeja(Long id, boolean leido) {
        this.id = id;
        this.leido = leido;
    }

    public boolean getLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public post getIdPost() {
        return idPost;
    }

    public void setIdPost(post idPost) {
        this.idPost = idPost;
    }

    public bandeja getIdBandeja() {
        return idBandeja;
    }

    public void setIdBandeja(bandeja idBandeja) {
        this.idBandeja = idBandeja;
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
        if (!(object instanceof post_en_bandeja)) {
            return false;
        }
        post_en_bandeja other = (post_en_bandeja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.post_en_bandeja[ id=" + id + " ]";
    }
    
}
