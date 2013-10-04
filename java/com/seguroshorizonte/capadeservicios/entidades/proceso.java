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
@Table(name = "proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "proceso.findAll", query = "SELECT p FROM proceso p"),
    @NamedQuery(name = "proceso.findByCodigo", query = "SELECT p FROM proceso p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "proceso.findByVersion", query = "SELECT p FROM proceso p WHERE p.version = :version"),
    @NamedQuery(name = "proceso.findByNombre", query = "SELECT p FROM proceso p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "proceso.findByDescripcion", query = "SELECT p FROM proceso p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "proceso.findByAutor", query = "SELECT p FROM proceso p WHERE p.autor = :autor"),
    @NamedQuery(name = "proceso.findByDuracion", query = "SELECT p FROM proceso p WHERE p.duracion = :duracion"),
    @NamedQuery(name = "proceso.findByCosto", query = "SELECT p FROM proceso p WHERE p.costo = :costo"),
    @NamedQuery(name = "proceso.findByFechaCreacion", query = "SELECT p FROM proceso p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "proceso.findByDocumentacion", query = "SELECT p FROM proceso p WHERE p.documentacion = :documentacion"),
    @NamedQuery(name = "proceso.findByEstado", query = "SELECT p FROM proceso p WHERE p.estado = :estado"),
    @NamedQuery(name = "proceso.findByDescripcionVersion", query = "SELECT p FROM proceso p WHERE p.descripcionVersion = :descripcionVersion"),
    @NamedQuery(name = "proceso.findById", query = "SELECT p FROM proceso p WHERE p.id = :id"),
    @NamedQuery(name = "proceso.findByBorrado", query = "SELECT p FROM proceso p WHERE p.borrado = :borrado")})
public class proceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
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
    private int duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 500)
    @Column(name = "documentacion")
    private String documentacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion_version")
    private String descripcionVersion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
    private Collection<periodo_grupo_proceso> periodogrupoprocesoCollection;
    @JoinColumn(name = "id_prioridad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private prioridad idPrioridad;
    @JoinColumn(name = "id_equivalencias_tiempo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private equivalencia_tiempo idEquivalenciasTiempo;
    @JoinColumn(name = "id_clasificacion_proceso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private clasificacion_proceso idClasificacionProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
    private Collection<tarea> tareaCollection;

    public proceso() {
    }

    public proceso(Long id) {
        this.id = id;
    }

    public proceso(Long id, int codigo, int version, String nombre, String descripcion, String autor, int duracion, double costo, Date fechaCreacion, String estado, String descripcionVersion, boolean borrado) {
        this.id = id;
        this.codigo = codigo;
        this.version = version;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
        this.duracion = duracion;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.descripcionVersion = descripcionVersion;
        this.borrado = borrado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public String getDescripcionVersion() {
        return descripcionVersion;
    }

    public void setDescripcionVersion(String descripcionVersion) {
        this.descripcionVersion = descripcionVersion;
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
    public Collection<periodo_grupo_proceso> getPeriodogrupoprocesoCollection() {
        return periodogrupoprocesoCollection;
    }

    public void setPeriodogrupoprocesoCollection(Collection<periodo_grupo_proceso> periodogrupoprocesoCollection) {
        this.periodogrupoprocesoCollection = periodogrupoprocesoCollection;
    }

    public prioridad getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(prioridad idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public equivalencia_tiempo getIdEquivalenciasTiempo() {
        return idEquivalenciasTiempo;
    }

    public void setIdEquivalenciasTiempo(equivalencia_tiempo idEquivalenciasTiempo) {
        this.idEquivalenciasTiempo = idEquivalenciasTiempo;
    }

    public clasificacion_proceso getIdClasificacionProceso() {
        return idClasificacionProceso;
    }

    public void setIdClasificacionProceso(clasificacion_proceso idClasificacionProceso) {
        this.idClasificacionProceso = idClasificacionProceso;
    }

    @XmlTransient
    public Collection<tarea> getTareaCollection() {
        return tareaCollection;
    }

    public void setTareaCollection(Collection<tarea> tareaCollection) {
        this.tareaCollection = tareaCollection;
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
        if (!(object instanceof proceso)) {
            return false;
        }
        proceso other = (proceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.proceso[ id=" + id + " ]";
    }
    
}
