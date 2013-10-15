/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.capadeservicios.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
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
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "actividad.findAll", query = "SELECT a FROM actividad a"),
    @NamedQuery(name = "actividad.findByEstado", query = "SELECT a FROM actividad a WHERE a.estado = :estado"),
    @NamedQuery(name = "actividad.findByFechaAsignacion", query = "SELECT a FROM actividad a WHERE a.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "actividad.findByFechaApertura", query = "SELECT a FROM actividad a WHERE a.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "actividad.findByFechaCierre", query = "SELECT a FROM actividad a WHERE a.fechaCierre = :fechaCierre"),
    @NamedQuery(name = "actividad.findByFechaAlerta", query = "SELECT a FROM actividad a WHERE a.fechaAlerta = :fechaAlerta"),
    @NamedQuery(name = "actividad.findByDuracion", query = "SELECT a FROM actividad a WHERE a.duracion = :duracion"),
    @NamedQuery(name = "actividad.findByParametrosEntrada", query = "SELECT a FROM actividad a WHERE a.parametrosEntrada = :parametrosEntrada"),
    @NamedQuery(name = "actividad.findByParametrosSalida", query = "SELECT a FROM actividad a WHERE a.parametrosSalida = :parametrosSalida"),
    @NamedQuery(name = "actividad.findByMaquina", query = "SELECT a FROM actividad a WHERE a.maquina = :maquina"),
    @NamedQuery(name = "actividad.findEstados", query = "SELECT distinct(a.estado) FROM actividad a"),
    @NamedQuery(name = "actividad.findById", query = "SELECT a FROM actividad a WHERE a.id = :id"),
    @NamedQuery(name = "actividad.findByBorrado", query = "SELECT a FROM actividad a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "actividad.findEstadoYBorrado", query = "SELECT a FROM actividad a WHERE a.estado = :estado AND a.borrado = :borrado")})
public class actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @Column(name = "fecha_alerta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlerta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private BigDecimal duracion;
    @Size(max = 50)
    @Column(name = "parametros_entrada")
    private String parametrosEntrada;
    @Size(max = 50)
    @Column(name = "parametros_salida")
    private String parametrosSalida;
    @Size(max = 20)
    @Column(name = "maquina")
    private String maquina;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @JoinColumn(name = "id_usuario_origen", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuarioOrigen;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private usuario idUsuario;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private tarea idTarea;
    @JoinColumn(name = "id_prioridad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private prioridad idPrioridad;
    @JoinColumn(name = "id_instancia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private instancia idInstancia;
    @JoinColumn(name = "id_equivalencias_tiempo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private equivalencia_tiempo idEquivalenciasTiempo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActividad")
    private Collection<documento> documentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActividad")
    private Collection<auditoria> auditoriaCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idActividad")
    private cola_de_tarea colaDeTarea;

    public actividad() {
    }

    public actividad(Long id) {
        this.id = id;
    }

    public actividad(Long id, String estado, Date fechaAsignacion, BigDecimal duracion, boolean borrado) {
        this.id = id;
        this.estado = estado;
        this.fechaAsignacion = fechaAsignacion;
        this.duracion = duracion;
        this.borrado = borrado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public BigDecimal getDuracion() {
        return duracion;
    }

    public void setDuracion(BigDecimal duracion) {
        this.duracion = duracion;
    }

    public String getParametrosEntrada() {
        return parametrosEntrada;
    }

    public void setParametrosEntrada(String parametrosEntrada) {
        this.parametrosEntrada = parametrosEntrada;
    }

    public String getParametrosSalida() {
        return parametrosSalida;
    }

    public void setParametrosSalida(String parametrosSalida) {
        this.parametrosSalida = parametrosSalida;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
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

    public usuario getIdUsuarioOrigen() {
        return idUsuarioOrigen;
    }

    public void setIdUsuarioOrigen(usuario idUsuarioOrigen) {
        this.idUsuarioOrigen = idUsuarioOrigen;
    }

    public usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public tarea getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(tarea idTarea) {
        this.idTarea = idTarea;
    }

    public prioridad getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(prioridad idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public instancia getIdInstancia() {
        return idInstancia;
    }

    public void setIdInstancia(instancia idInstancia) {
        this.idInstancia = idInstancia;
    }

    public equivalencia_tiempo getIdEquivalenciasTiempo() {
        return idEquivalenciasTiempo;
    }

    public void setIdEquivalenciasTiempo(equivalencia_tiempo idEquivalenciasTiempo) {
        this.idEquivalenciasTiempo = idEquivalenciasTiempo;
    }

    @XmlTransient
    public Collection<documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    public Collection<auditoria> getAuditoriaCollection() {
        return auditoriaCollection;
    }

    public void setAuditoriaCollection(Collection<auditoria> auditoriaCollection) {
        this.auditoriaCollection = auditoriaCollection;
    }

    public cola_de_tarea getColaDeTarea() {
        return colaDeTarea;
    }

    public void setColaDeTarea(cola_de_tarea colaDeTarea) {
        this.colaDeTarea = colaDeTarea;
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
        if (!(object instanceof actividad)) {
            return false;
        }
        actividad other = (actividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.capadeservicios.entidades.actividad[ id=" + id + " ]";
    }
    
}
