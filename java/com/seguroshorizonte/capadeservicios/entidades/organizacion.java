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
@Table(name = "organizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "organizacion.findAll", query = "SELECT o FROM organizacion o"),
    @NamedQuery(name = "organizacion.findByNombre", query = "SELECT o FROM organizacion o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "organizacion.findByDescripcion", query = "SELECT o FROM organizacion o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "organizacion.findByTipo", query = "SELECT o FROM organizacion o WHERE o.tipo = :tipo"),
    @NamedQuery(name = "organizacion.findByDireccion", query = "SELECT o FROM organizacion o WHERE o.direccion = :direccion"),
    @NamedQuery(name = "organizacion.findByTelefono", query = "SELECT o FROM organizacion o WHERE o.telefono = :telefono"),
    @NamedQuery(name = "organizacion.findByFax", query = "SELECT o FROM organizacion o WHERE o.fax = :fax"),
    @NamedQuery(name = "organizacion.findByMail", query = "SELECT o FROM organizacion o WHERE o.mail = :mail"),
    @NamedQuery(name = "organizacion.findByCiudad", query = "SELECT o FROM organizacion o WHERE o.ciudad = :ciudad"),
    @NamedQuery(name = "organizacion.findByEstado", query = "SELECT o FROM organizacion o WHERE o.estado = :estado"),
    @NamedQuery(name = "organizacion.findById", query = "SELECT o FROM organizacion o WHERE o.id = :id"),
    @NamedQuery(name = "organizacion.findByBorrado", query = "SELECT o FROM organizacion o WHERE o.borrado = :borrado")})
public class organizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Size(max = 20)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ciudad")
    private String ciudad;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganizacion")
    private Collection<usuario> usuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganizacion")
    private Collection<grupo> grupoCollection;
    @OneToMany(mappedBy = "idOrganizacionPadre")
    private Collection<organizacion> organizacionCollection;
    @JoinColumn(name = "id_organizacion_padre", referencedColumnName = "id")
    @ManyToOne
    private organizacion idOrganizacionPadre;

    public organizacion() {
    }

    public organizacion(Long id) {
        this.id = id;
    }

    public organizacion(Long id, String nombre, String ciudad, String estado, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estado = estado;
        this.borrado = borrado;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    @XmlTransient
    public Collection<usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<grupo> getGrupoCollection() {
        return grupoCollection;
    }

    public void setGrupoCollection(Collection<grupo> grupoCollection) {
        this.grupoCollection = grupoCollection;
    }

    @XmlTransient
    public Collection<organizacion> getOrganizacionCollection() {
        return organizacionCollection;
    }

    public void setOrganizacionCollection(Collection<organizacion> organizacionCollection) {
        this.organizacionCollection = organizacionCollection;
    }

    public organizacion getIdOrganizacionPadre() {
        return idOrganizacionPadre;
    }

    public void setIdOrganizacionPadre(organizacion idOrganizacionPadre) {
        this.idOrganizacionPadre = idOrganizacionPadre;
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
        if (!(object instanceof organizacion)) {
            return false;
        }
        organizacion other = (organizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.capadeservicios.entidades.organizacion[ id=" + id + " ]";
    }
    
}
