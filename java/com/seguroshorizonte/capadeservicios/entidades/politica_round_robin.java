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
@Table(name = "politica_round_robin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "politica_round_robin.findAll", query = "SELECT p FROM politica_round_robin p"),
    @NamedQuery(name = "politica_round_robin.findBySecuencia", query = "SELECT p FROM politica_round_robin p WHERE p.secuencia = :secuencia"),
    @NamedQuery(name = "politica_round_robin.findById", query = "SELECT p FROM politica_round_robin p WHERE p.id = :id"),
    @NamedQuery(name = "politica_round_robin.findByBorrado", query = "SELECT p FROM politica_round_robin p WHERE p.borrado = :borrado")})
public class politica_round_robin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private int secuencia;
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
    @JoinColumn(name = "id_contador_round_robin", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private contador_round_robin idContadorRoundRobin;

    public politica_round_robin() {
    }

    public politica_round_robin(Long id) {
        this.id = id;
    }

    public politica_round_robin(Long id, int secuencia, boolean borrado) {
        this.id = id;
        this.secuencia = secuencia;
        this.borrado = borrado;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
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

    public contador_round_robin getIdContadorRoundRobin() {
        return idContadorRoundRobin;
    }

    public void setIdContadorRoundRobin(contador_round_robin idContadorRoundRobin) {
        this.idContadorRoundRobin = idContadorRoundRobin;
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
        if (!(object instanceof politica_round_robin)) {
            return false;
        }
        politica_round_robin other = (politica_round_robin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.politica_round_robin[ id=" + id + " ]";
    }
    
}
