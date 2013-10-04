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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "contador_round_robin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "contador_round_robin.findAll", query = "SELECT c FROM contador_round_robin c"),
    @NamedQuery(name = "contador_round_robin.findByValor", query = "SELECT c FROM contador_round_robin c WHERE c.valor = :valor"),
    @NamedQuery(name = "contador_round_robin.findById", query = "SELECT c FROM contador_round_robin c WHERE c.id = :id"),
    @NamedQuery(name = "contador_round_robin.findByBorrado", query = "SELECT c FROM contador_round_robin c WHERE c.borrado = :borrado")})
public class contador_round_robin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContadorRoundRobin")
    private Collection<politica_round_robin> politicaroundrobinCollection;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private tarea idTarea;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private grupo idGrupo;

    public contador_round_robin() {
    }

    public contador_round_robin(Long id) {
        this.id = id;
    }

    public contador_round_robin(Long id, int valor, boolean borrado) {
        this.id = id;
        this.valor = valor;
        this.borrado = borrado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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
    public Collection<politica_round_robin> getPoliticaroundrobinCollection() {
        return politicaroundrobinCollection;
    }

    public void setPoliticaroundrobinCollection(Collection<politica_round_robin> politicaroundrobinCollection) {
        this.politicaroundrobinCollection = politicaroundrobinCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof contador_round_robin)) {
            return false;
        }
        contador_round_robin other = (contador_round_robin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.contador_round_robin[ id=" + id + " ]";
    }
    
}
