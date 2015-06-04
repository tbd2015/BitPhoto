/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "FAVORITOS_FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavoritosFoto.findAll", query = "SELECT f FROM FavoritosFoto f"),
    @NamedQuery(name = "FavoritosFoto.findByFechaFavFoto", query = "SELECT f FROM FavoritosFoto f WHERE f.fechaFavFoto = :fechaFavFoto"),
    @NamedQuery(name = "FavoritosFoto.findByIdUsuario", query = "SELECT f FROM FavoritosFoto f WHERE f.idUsuario = :idUsuario")})
public class FavoritosFoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "FECHA_FAV_FOTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFavFoto;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")
    @ManyToOne
    private Foto idFoto;

    public FavoritosFoto() {
    }

    public FavoritosFoto(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaFavFoto() {
        return fechaFavFoto;
    }

    public void setFechaFavFoto(Date fechaFavFoto) {
        this.fechaFavFoto = fechaFavFoto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Foto getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Foto idFoto) {
        this.idFoto = idFoto;
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
        if (!(object instanceof FavoritosFoto)) {
            return false;
        }
        FavoritosFoto other = (FavoritosFoto) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.FavoritosFoto[ idUsuario=" + idUsuario + " ]";
    }
    
}
