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
@Table(name = "USUARIO_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioUsuario.findAll", query = "SELECT u FROM UsuarioUsuario u"),
    @NamedQuery(name = "UsuarioUsuario.findByIdUsuarioUsuario", query = "SELECT u FROM UsuarioUsuario u WHERE u.idUsuarioUsuario = :idUsuarioUsuario"),
    @NamedQuery(name = "UsuarioUsuario.findByIdUsuariofollow", query = "SELECT u FROM UsuarioUsuario u WHERE u.idUsuariofollow = :idUsuariofollow"),
    @NamedQuery(name = "UsuarioUsuario.findByFechaSeguidos", query = "SELECT u FROM UsuarioUsuario u WHERE u.fechaSeguidos = :fechaSeguidos")})
public class UsuarioUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_USUARIO")
    private Integer idUsuarioUsuario;
    @Column(name = "ID_USUARIOFOLLOW")
    private Integer idUsuariofollow;
    @Column(name = "FECHA_SEGUIDOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSeguidos;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public UsuarioUsuario() {
    }

    public UsuarioUsuario(Integer idUsuarioUsuario) {
        this.idUsuarioUsuario = idUsuarioUsuario;
    }

    public Integer getIdUsuarioUsuario() {
        return idUsuarioUsuario;
    }

    public void setIdUsuarioUsuario(Integer idUsuarioUsuario) {
        this.idUsuarioUsuario = idUsuarioUsuario;
    }

    public Integer getIdUsuariofollow() {
        return idUsuariofollow;
    }

    public void setIdUsuariofollow(Integer idUsuariofollow) {
        this.idUsuariofollow = idUsuariofollow;
    }

    public Date getFechaSeguidos() {
        return fechaSeguidos;
    }

    public void setFechaSeguidos(Date fechaSeguidos) {
        this.fechaSeguidos = fechaSeguidos;
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
        hash += (idUsuarioUsuario != null ? idUsuarioUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioUsuario)) {
            return false;
        }
        UsuarioUsuario other = (UsuarioUsuario) object;
        if ((this.idUsuarioUsuario == null && other.idUsuarioUsuario != null) || (this.idUsuarioUsuario != null && !this.idUsuarioUsuario.equals(other.idUsuarioUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.UsuarioUsuario[ idUsuarioUsuario=" + idUsuarioUsuario + " ]";
    }
    
}
