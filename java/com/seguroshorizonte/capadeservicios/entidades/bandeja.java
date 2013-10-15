/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "bandeja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "bandeja.findAll", query = "SELECT b FROM bandeja b"),
    @NamedQuery(name = "bandeja.findByNombre", query = "SELECT b FROM bandeja b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "bandeja.findById", query = "SELECT b FROM bandeja b WHERE b.id = :id")})
public class bandeja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBandeja")
    private Collection<post_en_bandeja> postenbandejaCollection;

    public bandeja() {
    }

    public bandeja(Long id) {
        this.id = id;
    }

    public bandeja(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof bandeja)) {
            return false;
        }
        bandeja other = (bandeja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.bandeja[ id=" + id + " ]";
    }
    
}
