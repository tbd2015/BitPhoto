/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "PERMISO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findByIdPermiso", query = "SELECT p FROM Permiso p WHERE p.idPermiso = :idPermiso"),
    @NamedQuery(name = "Permiso.findByCompartir", query = "SELECT p FROM Permiso p WHERE p.compartir = :compartir"),
    @NamedQuery(name = "Permiso.findByComentar", query = "SELECT p FROM Permiso p WHERE p.comentar = :comentar"),
    @NamedQuery(name = "Permiso.findByDescargar", query = "SELECT p FROM Permiso p WHERE p.descargar = :descargar")})
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERMISO")
    private Integer idPermiso;
    @Column(name = "COMPARTIR")
    private Boolean compartir;
    @Column(name = "COMENTAR")
    private Boolean comentar;
    @Column(name = "DESCARGAR")
    private Boolean descargar;
    @ManyToMany(mappedBy = "permisoCollection")
    private Collection<Album> albumCollection;
    @JoinTable(name = "FOTO_PERMISO", joinColumns = {
        @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID_PERMISO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")})
    @ManyToMany
    private Collection<Foto> fotoCollection;

    public Permiso() {
    }

    public Permiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Boolean getCompartir() {
        return compartir;
    }

    public void setCompartir(Boolean compartir) {
        this.compartir = compartir;
    }

    public Boolean getComentar() {
        return comentar;
    }

    public void setComentar(Boolean comentar) {
        this.comentar = comentar;
    }

    public Boolean getDescargar() {
        return descargar;
    }

    public void setDescargar(Boolean descargar) {
        this.descargar = descargar;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @XmlTransient
    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(Collection<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Permiso[ idPermiso=" + idPermiso + " ]";
    }
    
}
