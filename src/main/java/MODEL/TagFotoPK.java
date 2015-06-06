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
public class TagFotoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TAG")
    private int idTag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FOTO")
    private int idFoto;

    public TagFotoPK() {
    }

    public TagFotoPK(int idTag, int idFoto) {
        this.idTag = idTag;
        this.idFoto = idFoto;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
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
        hash += (int) idTag;
        hash += (int) idFoto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagFotoPK)) {
            return false;
        }
        TagFotoPK other = (TagFotoPK) object;
        if (this.idTag != other.idTag) {
            return false;
        }
        if (this.idFoto != other.idFoto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TagFotoPK[ idTag=" + idTag + ", idFoto=" + idFoto + " ]";
    }
    
}
