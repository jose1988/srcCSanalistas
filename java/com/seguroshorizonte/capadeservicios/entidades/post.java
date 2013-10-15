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
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "post.findAll", query = "SELECT p FROM post p"),
    @NamedQuery(name = "post.findByPara", query = "SELECT p FROM post p WHERE p.para = :para"),
    @NamedQuery(name = "post.findByTexto", query = "SELECT p FROM post p WHERE p.texto = :texto"),
    @NamedQuery(name = "post.findByFecha", query = "SELECT p FROM post p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "post.findByAsunto", query = "SELECT p FROM post p WHERE p.asunto = :asunto"),
    @NamedQuery(name = "post.findById", query = "SELECT p FROM post p WHERE p.id = :id")})
public class post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "para")
    private String para;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "asunto")
    private String asunto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "de", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario de;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPost")
    private Collection<post_en_bandeja> postenbandejaCollection;

    public post() {
    }

    public post(Long id) {
        this.id = id;
    }

    public post(Long id, String para, String texto, Date fecha, String asunto) {
        this.id = id;
        this.para = para;
        this.texto = texto;
        this.fecha = fecha;
        this.asunto = asunto;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public usuario getDe() {
        return de;
    }

    public void setDe(usuario de) {
        this.de = de;
    }

    @XmlTransient
    public Collection<post_en_bandeja> getPostenbandejaCollection() {
        return postenbandejaCollection;
    }

    public void setPostenbandejaCollection(Collection<post_en_bandeja> postenbandejaCollection) {
        this.postenbandejaCollection = postenbandejaCollection;
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
        if (!(object instanceof post)) {
            return false;
        }
        post other = (post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.post[ id=" + id + " ]";
    }
    
}
