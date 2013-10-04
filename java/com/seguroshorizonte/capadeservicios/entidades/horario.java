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
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "horario.findAll", query = "SELECT t FROM horario t"),
    @NamedQuery(name = "horario.findByNombre", query = "SELECT t FROM horario t WHERE t.nombre = :nombre"),     @NamedQuery(name = "horario.findByDescripcion", query = "SELECT t FROM horario t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "horario.findById", query = "SELECT t FROM horario t WHERE t.id = :id"),
    @NamedQuery(name = "horario.findByBorrado", query = "SELECT t FROM horario t WHERE t.borrado = :borrado")})
public class horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    private int horaInicio;
     @Basic(optional = false)
    @NotNull
    @Column(name = "minuto_inicio")
    private int minutoInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fin")
    private int horaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minuto_fin")
    private int minutoFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHorario")
    private Collection<horario_usuario> horarioUsuarioCollection;
    
    public horario() {
    }

    public horario(Long id) {
        this.id = id;
    }

    public horario(Long id, String nombre, String descripcion,int horaInicio, int minutoInicio,int horaFin,int minutoFin, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.minutoInicio = minutoInicio;
        this.horaFin = horaFin;
        this.minutoFin = minutoFin;
        this.borrado = borrado;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImplementacion() {
        return descripcion;
    }

    public void setImplementacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

   
    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(int minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getMinutoFin() {
        return minutoFin;
    }

    public void setMinutoFin(int minutoFin) {
        this.minutoFin = minutoFin;
    }

    public Collection<horario_usuario> getHorarioUsuarioCollection() {
        return horarioUsuarioCollection;
    }

    public void setHorarioUsuarioCollection(Collection<horario_usuario> horarioUsuarioCollection) {
        this.horarioUsuarioCollection = horarioUsuarioCollection;
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
        if (!(object instanceof horario)) {
            return false;
        }
        horario other = (horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.horario[ id=" + id + " ]";
    }
    
}
