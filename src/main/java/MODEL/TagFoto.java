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
@Table(name = "TAG_FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagFoto.findAll", query = "SELECT t FROM TagFoto t"),
    @NamedQuery(name = "TagFoto.findByIdTagFoto", query = "SELECT t FROM TagFoto t WHERE t.idTagFoto = :idTagFoto")})
public class TagFoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TAG_FOTO")
    private Integer idTagFoto;
    @JoinColumn(name = "ID_TAG", referencedColumnName = "ID_TAG")
    @ManyToOne
    private Tag idTag;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")
    @ManyToOne
    private Foto idFoto;

    public TagFoto() {
    }

    public TagFoto(Integer idTagFoto) {
        this.idTagFoto = idTagFoto;
    }

    public Integer getIdTagFoto() {
        return idTagFoto;
    }

    public void setIdTagFoto(Integer idTagFoto) {
        this.idTagFoto = idTagFoto;
    }

    public Tag getIdTag() {
        return idTag;
    }

    public void setIdTag(Tag idTag) {
        this.idTag = idTag;
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
        hash += (idTagFoto != null ? idTagFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagFoto)) {
            return false;
        }
        TagFoto other = (TagFoto) object;
        if ((this.idTagFoto == null && other.idTagFoto != null) || (this.idTagFoto != null && !this.idTagFoto.equals(other.idTagFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiti.TagFoto[ idTagFoto=" + idTagFoto + " ]";
    }
    
}
