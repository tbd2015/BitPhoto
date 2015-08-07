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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "FAVORITOS_FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavoritosFoto.findAll", query = "SELECT f FROM FavoritosFoto f"),
    @NamedQuery(name = "FavoritosFoto.findByFechaFavFoto", query = "SELECT f FROM FavoritosFoto f WHERE f.fechaFavFoto = :fechaFavFoto"),
    @NamedQuery(name = "FavoritosFoto.findByIdFavoritosFoto", query = "SELECT f FROM FavoritosFoto f WHERE f.idFavoritosFoto = :idFavoritosFoto")})
public class FavoritosFoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "FECHA_FAV_FOTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFavFoto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FAVORITOS_FOTO")
    private Integer idFavoritosFoto;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")
    @ManyToOne
    private Foto idFoto;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public FavoritosFoto() {
    }

    public FavoritosFoto(Integer idFavoritosFoto) {
        this.idFavoritosFoto = idFavoritosFoto;
    }

    public Date getFechaFavFoto() {
        return fechaFavFoto;
    }

    public void setFechaFavFoto(Date fechaFavFoto) {
        this.fechaFavFoto = fechaFavFoto;
    }

    public Integer getIdFavoritosFoto() {
        return idFavoritosFoto;
    }

    public void setIdFavoritosFoto(Integer idFavoritosFoto) {
        this.idFavoritosFoto = idFavoritosFoto;
    }

    public Foto getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Foto idFoto) {
        this.idFoto = idFoto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFavoritosFoto != null ? idFavoritosFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoritosFoto)) {
            return false;
        }
        FavoritosFoto other = (FavoritosFoto) object;
        if ((this.idFavoritosFoto == null && other.idFavoritosFoto != null) || (this.idFavoritosFoto != null && !this.idFavoritosFoto.equals(other.idFavoritosFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.FavoritosFoto[ idFavoritosFoto=" + idFavoritosFoto + " ]";
    }
    
}
