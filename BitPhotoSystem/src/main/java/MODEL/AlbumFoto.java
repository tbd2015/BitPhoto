/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "ALBUM_FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlbumFoto.findAll", query = "SELECT a FROM AlbumFoto a"),
    @NamedQuery(name = "AlbumFoto.findByIdAlbum", query = "SELECT a FROM AlbumFoto a WHERE a.albumFotoPK.idAlbum = :idAlbum"),
    @NamedQuery(name = "AlbumFoto.findByIdFoto", query = "SELECT a FROM AlbumFoto a WHERE a.albumFotoPK.idFoto = :idFoto"),
    @NamedQuery(name = "AlbumFoto.findByFechaAgregacion", query = "SELECT a FROM AlbumFoto a WHERE a.fechaAgregacion = :fechaAgregacion")})
public class AlbumFoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlbumFotoPK albumFotoPK;
    @Column(name = "FECHA_AGREGACION")
    @Temporal(TemporalType.DATE)
    private Date fechaAgregacion;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Foto foto;
    @JoinColumn(name = "ID_ALBUM", referencedColumnName = "ID_ALBUM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Album album;

    public AlbumFoto() {
    }

    public AlbumFoto(AlbumFotoPK albumFotoPK) {
        this.albumFotoPK = albumFotoPK;
    }

    public AlbumFoto(int idAlbum, int idFoto) {
        this.albumFotoPK = new AlbumFotoPK(idAlbum, idFoto);
    }

    public AlbumFotoPK getAlbumFotoPK() {
        return albumFotoPK;
    }

    public void setAlbumFotoPK(AlbumFotoPK albumFotoPK) {
        this.albumFotoPK = albumFotoPK;
    }

    public Date getFechaAgregacion() {
        return fechaAgregacion;
    }

    public void setFechaAgregacion(Date fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumFotoPK != null ? albumFotoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumFoto)) {
            return false;
        }
        AlbumFoto other = (AlbumFoto) object;
        if ((this.albumFotoPK == null && other.albumFotoPK != null) || (this.albumFotoPK != null && !this.albumFotoPK.equals(other.albumFotoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AlbumFoto[ albumFotoPK=" + albumFotoPK + " ]";
    }
    
}
