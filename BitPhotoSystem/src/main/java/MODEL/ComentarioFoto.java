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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "COMENTARIO_FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioFoto.findAll", query = "SELECT c FROM ComentarioFoto c"),
    @NamedQuery(name = "ComentarioFoto.findByIdComentarioFoto", query = "SELECT c FROM ComentarioFoto c WHERE c.idComentarioFoto = :idComentarioFoto"),
    @NamedQuery(name = "ComentarioFoto.findByIdUsuario", query = "SELECT c FROM ComentarioFoto c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "ComentarioFoto.findByComentarioFoto", query = "SELECT c FROM ComentarioFoto c WHERE c.comentarioFoto = :comentarioFoto"),
    @NamedQuery(name = "ComentarioFoto.findByFechaComentarioFoto", query = "SELECT c FROM ComentarioFoto c WHERE c.fechaComentarioFoto = :fechaComentarioFoto")})
public class ComentarioFoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMENTARIO_FOTO")
    private Integer idComentarioFoto;
    @Size(max = 45)
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Size(max = 256)
    @Column(name = "COMENTARIO_FOTO")
    private String comentarioFoto;
    @Column(name = "FECHA_COMENTARIO_FOTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComentarioFoto;
    @JoinColumn(name = "ID_CLASIFICACION", referencedColumnName = "ID_CLASIFICACION")
    @ManyToOne
    private Clasificacion idClasificacion;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")
    @ManyToOne
    private Foto idFoto;

    public ComentarioFoto() {
    }

    public ComentarioFoto(Integer idComentarioFoto) {
        this.idComentarioFoto = idComentarioFoto;
    }

    public Integer getIdComentarioFoto() {
        return idComentarioFoto;
    }

    public void setIdComentarioFoto(Integer idComentarioFoto) {
        this.idComentarioFoto = idComentarioFoto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentarioFoto() {
        return comentarioFoto;
    }

    public void setComentarioFoto(String comentarioFoto) {
        this.comentarioFoto = comentarioFoto;
    }

    public Date getFechaComentarioFoto() {
        return fechaComentarioFoto;
    }

    public void setFechaComentarioFoto(Date fechaComentarioFoto) {
        this.fechaComentarioFoto = fechaComentarioFoto;
    }

    public Clasificacion getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Clasificacion idClasificacion) {
        this.idClasificacion = idClasificacion;
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
        hash += (idComentarioFoto != null ? idComentarioFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioFoto)) {
            return false;
        }
        ComentarioFoto other = (ComentarioFoto) object;
        if ((this.idComentarioFoto == null && other.idComentarioFoto != null) || (this.idComentarioFoto != null && !this.idComentarioFoto.equals(other.idComentarioFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ComentarioFoto[ idComentarioFoto=" + idComentarioFoto + " ]";
    }
    
}
