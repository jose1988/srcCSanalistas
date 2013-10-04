/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "tarea.findAll", query = "SELECT t FROM tarea t"),
    @NamedQuery(name = "tarea.findByNombre", query = "SELECT t FROM tarea t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "tarea.findByImplementacion", query = "SELECT t FROM tarea t WHERE t.implementacion = :implementacion"),
    @NamedQuery(name = "tarea.findByDescripcion", query = "SELECT t FROM tarea t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "tarea.findByAutor", query = "SELECT t FROM tarea t WHERE t.autor = :autor"),
    @NamedQuery(name = "tarea.findByDuracion", query = "SELECT t FROM tarea t WHERE t.duracion = :duracion"),
    @NamedQuery(name = "tarea.findByCosto", query = "SELECT t FROM tarea t WHERE t.costo = :costo"),
    @NamedQuery(name = "tarea.findByDocumentacion", query = "SELECT t FROM tarea t WHERE t.documentacion = :documentacion"),
    @NamedQuery(name = "tarea.findByEstado", query = "SELECT t FROM tarea t WHERE t.estado = :estado"),
    @NamedQuery(name = "tarea.findById", query = "SELECT t FROM tarea t WHERE t.id = :id"),
    @NamedQuery(name = "tarea.findByBorrado", query = "SELECT t FROM tarea t WHERE t.borrado = :borrado"),
    @NamedQuery(name = "tarea.findByTareaInicial", query = "SELECT t FROM tarea t WHERE t.tareaInicial = :tareaInicial"),
    @NamedQuery(name = "tarea.findByTareaInformativa", query = "SELECT t FROM tarea t WHERE t.tareaInformativa = :tareaInformativa")})
public class tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "implementacion")
    private String implementacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private BigDecimal duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private double costo;
    @Size(max = 500)
    @Column(name = "documentacion")
    private String documentacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarea_inicial")
    private boolean tareaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarea_informativa")
    private boolean tareaInformativa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private Collection<actividad> actividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private Collection<tarea_rol> tarearolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private Collection<grupo_politica_tarea> grupopoliticatareaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTareaDestino")
    private Collection<transicion> transicionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private Collection<transicion> transicionCollection1;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private proceso idProceso;
    @JoinColumn(name = "id_prioridad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private prioridad idPrioridad;
    @JoinColumn(name = "id_politica", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private politica idPolitica;
    @JoinColumn(name = "id_equivalencia_tiempo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private equivalencia_tiempo idEquivalenciaTiempo;
    @JoinColumn(name = "id_clasificacion_tarea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private clasificacion_tarea idClasificacionTarea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private Collection<contador_round_robin> contadorroundrobinCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private Collection<cola_de_tarea> coladetareaCollection;

    public tarea() {
    }

    public tarea(Long id) {
        this.id = id;
    }

    public tarea(Long id, String nombre, String implementacion, String descripcion, String autor, BigDecimal duracion, double costo, String estado, boolean borrado, boolean tareaInicial, boolean tareaInformativa) {
        this.id = id;
        this.nombre = nombre;
        this.implementacion = implementacion;
        this.descripcion = descripcion;
        this.autor = autor;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
        this.borrado = borrado;
        this.tareaInicial = tareaInicial;
        this.tareaInformativa = tareaInformativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImplementacion() {
        return implementacion;
    }

    public void setImplementacion(String implementacion) {
        this.implementacion = implementacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public BigDecimal getDuracion() {
        return duracion;
    }

    public void setDuracion(BigDecimal duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public boolean getTareaInicial() {
        return tareaInicial;
    }

    public void setTareaInicial(boolean tareaInicial) {
        this.tareaInicial = tareaInicial;
    }

    public boolean getTareaInformativa() {
        return tareaInformativa;
    }

    public void setTareaInformativa(boolean tareaInformativa) {
        this.tareaInformativa = tareaInformativa;
    }

    @XmlTransient
    public Collection<actividad> getActividadCollection() {
        return actividadCollection;
    }

    public void setActividadCollection(Collection<actividad> actividadCollection) {
        this.actividadCollection = actividadCollection;
    }

    @XmlTransient
    public Collection<tarea_rol> getTarearolCollection() {
        return tarearolCollection;
    }

    public void setTarearolCollection(Collection<tarea_rol> tarearolCollection) {
        this.tarearolCollection = tarearolCollection;
    }

    @XmlTransient
    public Collection<grupo_politica_tarea> getGrupopoliticatareaCollection() {
        return grupopoliticatareaCollection;
    }

    public void setGrupopoliticatareaCollection(Collection<grupo_politica_tarea> grupopoliticatareaCollection) {
        this.grupopoliticatareaCollection = grupopoliticatareaCollection;
    }

    @XmlTransient
    public Collection<transicion> getTransicionCollection() {
        return transicionCollection;
    }

    public void setTransicionCollection(Collection<transicion> transicionCollection) {
        this.transicionCollection = transicionCollection;
    }

    @XmlTransient
    public Collection<transicion> getTransicionCollection1() {
        return transicionCollection1;
    }

    public void setTransicionCollection1(Collection<transicion> transicionCollection1) {
        this.transicionCollection1 = transicionCollection1;
    }

    public proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(proceso idProceso) {
        this.idProceso = idProceso;
    }

    public prioridad getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(prioridad idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public politica getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(politica idPolitica) {
        this.idPolitica = idPolitica;
    }

    public equivalencia_tiempo getIdEquivalenciaTiempo() {
        return idEquivalenciaTiempo;
    }

    public void setIdEquivalenciaTiempo(equivalencia_tiempo idEquivalenciaTiempo) {
        this.idEquivalenciaTiempo = idEquivalenciaTiempo;
    }

    public clasificacion_tarea getIdClasificacionTarea() {
        return idClasificacionTarea;
    }

    public void setIdClasificacionTarea(clasificacion_tarea idClasificacionTarea) {
        this.idClasificacionTarea = idClasificacionTarea;
    }

    @XmlTransient
    public Collection<contador_round_robin> getContadorroundrobinCollection() {
        return contadorroundrobinCollection;
    }

    public void setContadorroundrobinCollection(Collection<contador_round_robin> contadorroundrobinCollection) {
        this.contadorroundrobinCollection = contadorroundrobinCollection;
    }

    @XmlTransient
    public Collection<cola_de_tarea> getColadetareaCollection() {
        return coladetareaCollection;
    }

    public void setColadetareaCollection(Collection<cola_de_tarea> coladetareaCollection) {
        this.coladetareaCollection = coladetareaCollection;
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
        if (!(object instanceof tarea)) {
            return false;
        }
        tarea other = (tarea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.tarea[ id=" + id + " ]";
    }
    
}
