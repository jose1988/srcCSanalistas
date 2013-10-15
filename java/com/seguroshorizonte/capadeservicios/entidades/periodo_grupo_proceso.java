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
@Table(name = "periodo_grupo_proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "periodo_grupo_proceso.findAll", query = "SELECT p FROM periodo_grupo_proceso p"),
    @NamedQuery(name = "periodo_grupo_proceso.findById", query = "SELECT p FROM periodo_grupo_proceso p WHERE p.id = :id"),
    @NamedQuery(name = "periodo_grupo_proceso.findByBorrado", query = "SELECT p FROM periodo_grupo_proceso p WHERE p.borrado = :borrado")})
public class periodo_grupo_proceso implements Serializable {
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
    @JoinColumn(name = "id_proceso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private proceso idProceso;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private periodo idPeriodo;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private grupo idGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoGrupoProceso")
    private Collection<instancia> instanciaCollection;

    public periodo_grupo_proceso() {
    }

    public periodo_grupo_proceso(Long id) {
        this.id = id;
    }

    public periodo_grupo_proceso(Long id, boolean borrado) {
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

    public proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(proceso idProceso) {
        this.idProceso = idProceso;
    }

    public periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    @XmlTransient
    public Collection<instancia> getInstanciaCollection() {
        return instanciaCollection;
    }

    public void setInstanciaCollection(Collection<instancia> instanciaCollection) {
        this.instanciaCollection = instanciaCollection;
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
        if (!(object instanceof periodo_grupo_proceso)) {
            return false;
        }
        periodo_grupo_proceso other = (periodo_grupo_proceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.periodo_grupo_proceso[ id=" + id + " ]";
    }
    
}
