/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "TAG_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagUsuario.findAll", query = "SELECT t FROM TagUsuario t"),
    @NamedQuery(name = "TagUsuario.findByIdTagUsuario", query = "SELECT t FROM TagUsuario t WHERE t.idTagUsuario = :idTagUsuario")})
public class TagUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TAG_USUARIO")
    private Integer idTagUsuario;
    @JoinColumn(name = "ID_TAG", referencedColumnName = "ID_TAG")
    @ManyToOne
    private Tag idTag;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public TagUsuario() {
    }

    public TagUsuario(Integer idTagUsuario) {
        this.idTagUsuario = idTagUsuario;
    }

    public Integer getIdTagUsuario() {
        return idTagUsuario;
    }

    public void setIdTagUsuario(Integer idTagUsuario) {
        this.idTagUsuario = idTagUsuario;
    }

    public Tag getIdTag() {
        return idTag;
    }

    public void setIdTag(Tag idTag) {
        this.idTag = idTag;
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
        hash += (idTagUsuario != null ? idTagUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagUsuario)) {
            return false;
        }
        TagUsuario other = (TagUsuario) object;
        if ((this.idTagUsuario == null && other.idTagUsuario != null) || (this.idTagUsuario != null && !this.idTagUsuario.equals(other.idTagUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiti.TagUsuario[ idTagUsuario=" + idTagUsuario + " ]";
    }
    
}
