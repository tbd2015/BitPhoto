/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "CAMARA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camara.findAll", query = "SELECT c FROM Camara c"),
    @NamedQuery(name = "Camara.findByIdCamara", query = "SELECT c FROM Camara c WHERE c.idCamara = :idCamara"),
    @NamedQuery(name = "Camara.findByMegapixeles", query = "SELECT c FROM Camara c WHERE c.megapixeles = :megapixeles"),
    @NamedQuery(name = "Camara.findByZoom", query = "SELECT c FROM Camara c WHERE c.zoom = :zoom"),
    @NamedQuery(name = "Camara.findByUrlFotoCamara", query = "SELECT c FROM Camara c WHERE c.urlFotoCamara = :urlFotoCamara"),
    @NamedQuery(name = "Camara.findByNombre", query = "SELECT c FROM Camara c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Camara.findByModelo", query = "SELECT c FROM Camara c WHERE c.modelo = :modelo"),
    @NamedQuery(name = "Camara.findByCantFotos", query = "SELECT c FROM Camara c WHERE c.cantFotos = :cantFotos")})
public class Camara implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CAMARA")
    private Integer idCamara;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Size(max = 20)
    @Column(name = "MEGAPIXELES")
    private String megapixeles;
    @Column(name = "ZOOM")
    private Float zoom;
    @Size(max = 256)
    @Column(name = "URL_FOTO_CAMARA")
    private String urlFotoCamara;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "CANT_FOTOS")
    private Integer cantFotos;
    @OneToMany(mappedBy = "idCamara")
    private Collection<Foto> fotoCollection;
    @Size(max = 20)
    @Column(name = "MARCA")
    private String marca;

    public Camara() {
    }

    public Camara(Integer idCamara) {
        this.idCamara = idCamara;
    }

    public Integer getIdCamara() {
        return idCamara;
    }

    public void setIdCamara(Integer idCamara) {
        this.idCamara = idCamara;
    }

    public String getMegapixeles() {
        return megapixeles;
    }

    public void setMegapixeles(String megapixeles) {
        this.megapixeles = megapixeles;
    }

    public Float getZoom() {
        return zoom;
    }

    public void setZoom(Float zoom) {
        this.zoom = zoom;
    }

    public String getUrlFotoCamara() {
        return urlFotoCamara;
    }

    public void setUrlFotoCamara(String urlFotoCamara) {
        this.urlFotoCamara = urlFotoCamara;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCantFotos() {
        return cantFotos;
    }

    public void setCantFotos(Integer cantFotos) {
        this.cantFotos = cantFotos;
    }

    @XmlTransient
    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(Collection<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
    }

    @XmlTransient
    public String getMarca(){
        return this.marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamara != null ? idCamara.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camara)) {
            return false;
        }
        Camara other = (Camara) object;
        if ((this.idCamara == null && other.idCamara != null) || (this.idCamara != null && !this.idCamara.equals(other.idCamara))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Camara[ idCamara=" + idCamara + " ]";
    }
    
}
