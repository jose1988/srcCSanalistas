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
@Table(name = "prioridad_mensaje_en_linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "prioridad_mensaje_en_linea.findAll", query = "SELECT p FROM prioridad_mensaje_en_linea p"),
    @NamedQuery(name = "prioridad_mensaje_en_linea.findByNombre", query = "SELECT p FROM prioridad_mensaje_en_linea p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "prioridad_mensaje_en_linea.findByColorTexto", query = "SELECT p FROM prioridad_mensaje_en_linea p WHERE p.colorTexto = :colorTexto"),
    @NamedQuery(name = "prioridad_mensaje_en_linea.findByColorFondo", query = "SELECT p FROM prioridad_mensaje_en_linea p WHERE p.colorFondo = :colorFondo"),
    @NamedQuery(name = "prioridad_mensaje_en_linea.findById", query = "SELECT p FROM prioridad_mensaje_en_linea p WHERE p.id = :id"),
    @NamedQuery(name = "prioridad_mensaje_en_linea.findByBorrado", query = "SELECT p FROM prioridad_mensaje_en_linea p WHERE p.borrado = :borrado")})
public class prioridad_mensaje_en_linea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "color_texto")
    private String colorTexto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "color_fondo")
    private String colorFondo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrioridadMensajeEnLinea")
    private Collection<destinatario> destinatarioCollection;

    public prioridad_mensaje_en_linea() {
    }

    public prioridad_mensaje_en_linea(Long id) {
        this.id = id;
    }

    public prioridad_mensaje_en_linea(Long id, String nombre, String colorTexto, String colorFondo, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.colorTexto = colorTexto;
        this.colorFondo = colorFondo;
        this.borrado = borrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
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
    public Collection<destinatario> getDestinatarioCollection() {
        return destinatarioCollection;
    }

    public void setDestinatarioCollection(Collection<destinatario> destinatarioCollection) {
        this.destinatarioCollection = destinatarioCollection;
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
        if (!(object instanceof prioridad_mensaje_en_linea)) {
            return false;
        }
        prioridad_mensaje_en_linea other = (prioridad_mensaje_en_linea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.prioridad_mensaje_en_linea[ id=" + id + " ]";
    }
    
}
