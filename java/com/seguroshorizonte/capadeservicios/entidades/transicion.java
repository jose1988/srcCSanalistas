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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pangea
 */
@Entity
@Table(name = "transicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "transicion.findAll", query = "SELECT t FROM transicion t"),
    @NamedQuery(name = "transicion.findByNombre", query = "SELECT t FROM transicion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "transicion.findByObservacion", query = "SELECT t FROM transicion t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "transicion.findByCodigo", query = "SELECT t FROM transicion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "transicion.findByDocumentacion", query = "SELECT t FROM transicion t WHERE t.documentacion = :documentacion"),
    @NamedQuery(name = "transicion.findByEnvioPorInterfaz", query = "SELECT t FROM transicion t WHERE t.envioPorInterfaz = :envioPorInterfaz"),
    @NamedQuery(name = "transicion.findById", query = "SELECT t FROM transicion t WHERE t.id = :id"),
    @NamedQuery(name = "transicion.findByBorrado", query = "SELECT t FROM transicion t WHERE t.borrado = :borrado")})
public class transicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 500)
    @Column(name = "documentacion")
    private String documentacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envio_por_interfaz")
    private boolean envioPorInterfaz;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_tarea_destino", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private tarea idTareaDestino;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private tarea idTarea;
    @JoinColumn(name = "id_regla_transicion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private regla_transicion idReglaTransicion;
    @JoinColumn(name = "id_condicion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private condicion idCondicion;

    public transicion() {
    }

    public transicion(Long id) {
        this.id = id;
    }

    public transicion(Long id, String nombre, String codigo, boolean envioPorInterfaz, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.envioPorInterfaz = envioPorInterfaz;
        this.borrado = borrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public boolean getEnvioPorInterfaz() {
        return envioPorInterfaz;
    }

    public void setEnvioPorInterfaz(boolean envioPorInterfaz) {
        this.envioPorInterfaz = envioPorInterfaz;
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

    public tarea getIdTareaDestino() {
        return idTareaDestino;
    }

    public void setIdTareaDestino(tarea idTareaDestino) {
        this.idTareaDestino = idTareaDestino;
    }

    public tarea getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(tarea idTarea) {
        this.idTarea = idTarea;
    }

    public regla_transicion getIdReglaTransicion() {
        return idReglaTransicion;
    }

    public void setIdReglaTransicion(regla_transicion idReglaTransicion) {
        this.idReglaTransicion = idReglaTransicion;
    }

    public condicion getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(condicion idCondicion) {
        this.idCondicion = idCondicion;
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
        if (!(object instanceof transicion)) {
            return false;
        }
        transicion other = (transicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.transicion[ id=" + id + " ]";
    }
    
}
