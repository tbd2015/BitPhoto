/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author elias
 */
@Embeddable
public class AlbumFotoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ALBUM")
    private int idAlbum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FOTO")
    private int idFoto;

    public AlbumFotoPK() {
    }

    public AlbumFotoPK(int idAlbum, int idFoto) {
        this.idAlbum = idAlbum;
        this.idFoto = idFoto;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAlbum;
        hash += (int) idFoto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumFotoPK)) {
            return false;
        }
        AlbumFotoPK other = (AlbumFotoPK) object;
        if (this.idAlbum != other.idAlbum) {
            return false;
        }
        if (this.idFoto != other.idFoto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AlbumFotoPK[ idAlbum=" + idAlbum + ", idFoto=" + idFoto + " ]";
    }
    
}
