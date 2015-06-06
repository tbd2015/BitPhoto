package MODEL;

/**
 *
 * @author elias
 */
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

@Entity
@Table(name = "ALBUM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.idAlbum = :idAlbum"),
    @NamedQuery(name = "Album.findByNombre", query = "SELECT a FROM Album a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Album.findByFechaCreado", query = "SELECT a FROM Album a WHERE a.fechaCreado = :fechaCreado"),
    @NamedQuery(name = "Album.findByDescripcion", query = "SELECT a FROM Album a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Album.findByCantFoto", query = "SELECT a FROM Album a WHERE a.cantFoto = :cantFoto"),
    @NamedQuery(name = "Album.findByCantFav", query = "SELECT a FROM Album a WHERE a.cantFav = :cantFav"),
    @NamedQuery(name = "Album.findByCantComentarios", query = "SELECT a FROM Album a WHERE a.cantComentarios = :cantComentarios"),
    @NamedQuery(name = "Album.findByUrlfotoalbum", query = "SELECT a FROM Album a WHERE a.urlfotoalbum = :urlfotoalbum")})
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALBUM")
    private Integer idAlbum;
    //Remover cuando se carge todo
    //@Column(name = "ID_USUARIO")
    //private Integer idUsuario;
    //
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHA_CREADO")
    @Temporal(TemporalType.DATE)
    private Date fechaCreado;
    @Size(max = 256)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CANT_FOTO")
    private Integer cantFoto;
    @Column(name = "CANT_FAV")
    private Integer cantFav;
    @Column(name = "CANT_COMENTARIOS")
    private Integer cantComentarios;
    @Size(max = 256)
    @Column(name = "URLFOTOALBUM")
    private String urlfotoalbum;
    
    @JoinTable(name = "ALBUM_PERMISO", joinColumns = {
        @JoinColumn(name = "ID_ALBUM", referencedColumnName = "ID_ALBUM")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID_PERMISO")})
    @ManyToMany
    private Collection<Permiso> permisoCollection;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private Collection<AlbumFoto> albumFotoCollection;

    public Album() {
    }

    public Album(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantFoto() {
        return cantFoto;
    }

    public void setCantFoto(Integer cantFoto) {
        this.cantFoto = cantFoto;
    }

    public Integer getCantFav() {
        return cantFav;
    }

    public void setCantFav(Integer cantFav) {
        this.cantFav = cantFav;
    }

    public Integer getCantComentarios() {
        return cantComentarios;
    }

    public void setCantComentarios(Integer cantComentarios) {
        this.cantComentarios = cantComentarios;
    }

    public String getUrlfotoalbum() {
        return urlfotoalbum;
    }

    public void setUrlfotoalbum(String urlfotoalbum) {
        this.urlfotoalbum = urlfotoalbum;
    }
    
    //Borrar cunado se cargen todas las tablas
    //public int getIdUsuario() {
    //    return idUsuario;
    //}

    //public void setIdUsuario(Album album) {
    //    this.idUsuario = album.idUsuario;
    //}
    //

    @XmlTransient
    public Collection<Permiso> getPermisoCollection() {
        return permisoCollection;
    }

    public void setPermisoCollection(Collection<Permiso> permisoCollection) {
        this.permisoCollection = permisoCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    @XmlTransient
    public Collection<AlbumFoto> getAlbumFotoCollection() {
        return albumFotoCollection;
    }

    public void setAlbumFotoCollection(Collection<AlbumFoto> albumFotoCollection) {
        this.albumFotoCollection = albumFotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlbum != null ? idAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.idAlbum == null && other.idAlbum != null) || (this.idAlbum != null && !this.idAlbum.equals(other.idAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Album[ idAlbum=" + idAlbum + " ]";
    }
    
}
