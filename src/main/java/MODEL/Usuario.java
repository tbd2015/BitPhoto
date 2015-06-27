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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByAlias", query = "SELECT u FROM Usuario u WHERE u.alias = :alias"),
    @NamedQuery(name = "Usuario.findByNombrereal", query = "SELECT u FROM Usuario u WHERE u.nombrereal = :nombrereal"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByCantFotos", query = "SELECT u FROM Usuario u WHERE u.cantFotos = :cantFotos"),
    @NamedQuery(name = "Usuario.findByCantAlbum", query = "SELECT u FROM Usuario u WHERE u.cantAlbum = :cantAlbum"),
    @NamedQuery(name = "Usuario.findByCantSeguidores", query = "SELECT u FROM Usuario u WHERE u.cantSeguidores = :cantSeguidores"),
    @NamedQuery(name = "Usuario.findByCantSeguidos", query = "SELECT u FROM Usuario u WHERE u.cantSeguidos = :cantSeguidos"),
    @NamedQuery(name = "Usuario.findByUrlPerfil", query = "SELECT u FROM Usuario u WHERE u.urlPerfil = :urlPerfil"),
    @NamedQuery(name = "Usuario.findByUrlAvatar", query = "SELECT u FROM Usuario u WHERE u.urlAvatar = :urlAvatar"),
    @NamedQuery(name = "Usuario.findByDescripcion", query = "SELECT u FROM Usuario u WHERE u.descripcion = :descripcion")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Size(max = 20)
    @Column(name = "ALIAS")
    private String alias;
    @Size(max = 20)
    @Column(name = "NOMBREREAL")
    private String nombrereal;
    @Size(max = 33)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 20)
    @Column(name = "APELLIDO")
    private String apellido;
    @Size(max = 40)
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "CANT_FOTOS")
    private Integer cantFotos;
    @Column(name = "CANT_ALBUM")
    private Integer cantAlbum;
    @Column(name = "CANT_SEGUIDORES")
    private Integer cantSeguidores;
    @Column(name = "CANT_SEGUIDOS")
    private Integer cantSeguidos;
    @Size(max = 256)
    @Column(name = "URL_PERFIL")
    private String urlPerfil;
    @Size(max = 256)
    @Column(name = "URL_AVATAR")
    private String urlAvatar;
    @Size(max = 144)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @ManyToMany(mappedBy = "usuarioCollection")
    private Collection<Foto> fotoCollection;
    @JoinTable(name = "TAG_USUARIO", joinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_TAG", referencedColumnName = "ID_TAG")})
    @ManyToMany
    private Collection<Tag> tagCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Album> albumCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<UsuarioUsuario> usuarioUsuarioCollection;

    public Usuario(){
        this.idUsuario = null;
        this.alias = null;
        this.nombrereal = null;
        this.apellido = null;
        this.cantAlbum = 0;
        this.cantFotos = 0;
        this.cantSeguidores = 0;
        this.cantSeguidos = 0;
        this.contrasena = null;
        this.correo = null;
        this.descripcion = null;
        this.fechaCreacion = null;
        this.urlAvatar = null;
        this.urlPerfil = null;

   
   

   
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombrereal() {
        return nombrereal;
    }

    public void setNombrereal(String nombrereal) {
        this.nombrereal = nombrereal;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCantFotos() {
        return cantFotos;
    }

    public void setCantFotos(Integer cantFotos) {
        this.cantFotos = cantFotos;
    }

    public Integer getCantAlbum() {
        return cantAlbum;
    }

    public void setCantAlbum(Integer cantAlbum) {
        this.cantAlbum = cantAlbum;
    }

    public Integer getCantSeguidores() {
        return cantSeguidores;
    }

    public void setCantSeguidores(Integer cantSeguidores) {
        this.cantSeguidores = cantSeguidores;
    }

    public Integer getCantSeguidos() {
        return cantSeguidos;
    }

    public void setCantSeguidos(Integer cantSeguidos) {
        this.cantSeguidos = cantSeguidos;
    }

    public String getUrlPerfil() {
        return urlPerfil;
    }

    public void setUrlPerfil(String urlPerfil) {
        this.urlPerfil = urlPerfil;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(Collection<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @XmlTransient
    public Collection<UsuarioUsuario> getUsuarioUsuarioCollection() {
        return usuarioUsuarioCollection;
    }

    public void setUsuarioUsuarioCollection(Collection<UsuarioUsuario> usuarioUsuarioCollection) {
        this.usuarioUsuarioCollection = usuarioUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
