/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "TagFoto.findByIdTag", query = "SELECT t FROM TagFoto t WHERE t.tagFotoPK.idTag = :idTag"),
    @NamedQuery(name = "TagFoto.findByIdFoto", query = "SELECT t FROM TagFoto t WHERE t.tagFotoPK.idFoto = :idFoto")})
public class TagFoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TagFotoPK tagFotoPK;

    public TagFoto() {
    }

    public TagFoto(TagFotoPK tagFotoPK) {
        this.tagFotoPK = tagFotoPK;
    }

    public TagFoto(int idTag, int idFoto) {
        this.tagFotoPK = new TagFotoPK(idTag, idFoto);
    }

    public TagFotoPK getTagFotoPK() {
        return tagFotoPK;
    }

    public void setTagFotoPK(TagFotoPK tagFotoPK) {
        this.tagFotoPK = tagFotoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagFotoPK != null ? tagFotoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagFoto)) {
            return false;
        }
        TagFoto other = (TagFoto) object;
        if ((this.tagFotoPK == null && other.tagFotoPK != null) || (this.tagFotoPK != null && !this.tagFotoPK.equals(other.tagFotoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TagFoto[ tagFotoPK=" + tagFotoPK + " ]";
    }
    
}
