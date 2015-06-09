/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foto.findAll", query = "SELECT f FROM Foto f"),
    @NamedQuery(name = "Foto.findByIdFoto", query = "SELECT f FROM Foto f WHERE f.idFoto = :idFoto"),
    @NamedQuery(name = "Foto.findByIdUsuario", query = "SELECT f FROM Foto f WHERE f.idUsuario = :idUsuario"),
    @NamedQuery(name = "Foto.findByFechaCarga", query = "SELECT f FROM Foto f WHERE f.fechaCarga = :fechaCarga"),
    @NamedQuery(name = "Foto.findByFechaTomada", query = "SELECT f FROM Foto f WHERE f.fechaTomada = :fechaTomada"),
    @NamedQuery(name = "Foto.findByVistas", query = "SELECT f FROM Foto f WHERE f.vistas = :vistas"),
    @NamedQuery(name = "Foto.findByTitulo", query = "SELECT f FROM Foto f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "Foto.findByDescripcion", query = "SELECT f FROM Foto f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "Foto.findByCantFavor", query = "SELECT f FROM Foto f WHERE f.cantFavor = :cantFavor"),
    @NamedQuery(name = "Foto.findByUrl", query = "SELECT f FROM Foto f WHERE f.url = :url"),
    @NamedQuery(name = "Foto.findByFormato", query = "SELECT f FROM Foto f WHERE f.formato = :formato"),
    @NamedQuery(name = "Foto.findByCantCom", query = "SELECT f FROM Foto f WHERE f.cantCom = :cantCom")})
public class Foto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FOTO")
    private Integer idFoto;
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "FECHA_CARGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarga;
    @Column(name = "FECHA_TOMADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTomada;
    @Column(name = "VISTAS")
    private Integer vistas;
    @Size(max = 20)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 20)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CANT_FAVOR")
    private Integer cantFavor;
    @Size(max = 256)
    @Column(name = "URL")
    private String url;
    @Size(max = 20)
    @Column(name = "FORMATO")
    private String formato;
    @Lob
    //@Column(name = "PUNTO_LUGAR")
    //private Object puntoLugar;
    
    @Column(name = "CANT_COM")
    private Integer cantCom;
    @JoinTable(name = "ETIQUETA", joinColumns = {
        @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;
    @ManyToMany(mappedBy = "fotoCollection")
    private Collection<Permiso> permisoCollection;
    @JoinColumn(name = "ID_CAMARA", referencedColumnName = "ID_CAMARA")
    @ManyToOne
    private Camara idCamara;
    @OneToMany(mappedBy = "idFoto")
    private Collection<ComentarioFoto> comentarioFotoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foto")
    private Collection<AlbumFoto> albumFotoCollection;
    @OneToMany(mappedBy = "idFoto")
    private Collection<FavoritosFoto> favoritosFotoCollection;

    public Foto() {
    }

    public Foto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaTomada() {
        return fechaTomada;
    }

    public void setFechaTomada(Date fechaTomada) {
        this.fechaTomada = fechaTomada;
    }

    public Integer getVistas() {
        return vistas;
    }

    public void setVistas(Integer vistas) {
        this.vistas = vistas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantFavor() {
        return cantFavor;
    }

    public void setCantFavor(Integer cantFavor) {
        this.cantFavor = cantFavor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**public Object getPuntoLugar() {
        return puntoLugar;
    }

    public void setPuntoLugar(Object puntoLugar) {
        this.puntoLugar = puntoLugar;
    }**/

    public Integer getCantCom() {
        return cantCom;
    }

    public void setCantCom(Integer cantCom) {
        this.cantCom = cantCom;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Permiso> getPermisoCollection() {
        return permisoCollection;
    }

    public void setPermisoCollection(Collection<Permiso> permisoCollection) {
        this.permisoCollection = permisoCollection;
    }

    public Camara getIdCamara() {
        return idCamara;
    }

    public void setIdCamara(Camara idCamara) {
        this.idCamara = idCamara;
    }

    @XmlTransient
    public Collection<ComentarioFoto> getComentarioFotoCollection() {
        return comentarioFotoCollection;
    }

    public void setComentarioFotoCollection(Collection<ComentarioFoto> comentarioFotoCollection) {
        this.comentarioFotoCollection = comentarioFotoCollection;
    }

    @XmlTransient
    public Collection<AlbumFoto> getAlbumFotoCollection() {
        return albumFotoCollection;
    }

    public void setAlbumFotoCollection(Collection<AlbumFoto> albumFotoCollection) {
        this.albumFotoCollection = albumFotoCollection;
    }

    @XmlTransient
    public Collection<FavoritosFoto> getFavoritosFotoCollection() {
        return favoritosFotoCollection;
    }

    public void setFavoritosFotoCollection(Collection<FavoritosFoto> favoritosFotoCollection) {
        this.favoritosFotoCollection = favoritosFotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFoto != null ? idFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foto)) {
            return false;
        }
        Foto other = (Foto) object;
        if ((this.idFoto == null && other.idFoto != null) || (this.idFoto != null && !this.idFoto.equals(other.idFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Foto[ idFoto=" + idFoto + " ]";
    }
    
}
