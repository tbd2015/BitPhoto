package EJB;

import EJB.local.FotoEJBLocal;

import FACADE.AlbumFotoEJBFacade;
import FACADE.FavoritosFotoEJBFacade;
import FACADE.FotoEJBFacade;
import FACADE.UsuarioEJBFacade;
import MODEL.Etiqueta;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import MODEL.Foto;
import MODEL.Usuario;
import java.util.AbstractList;

@Stateless
public class FotoEJB implements FotoEJBLocal{
    
    @EJB
    FotoEJBFacade FotoFacade;
	
    public FotoEJB() {
       
    }

    @Override
    public Foto getPhoto(int IdFoto) {
        return this.FotoFacade.find(IdFoto);
    }

    @Override
    public void addPhoto(Foto photo) {
        this.FotoFacade.create(photo);
    }

    @Override
    public void updatePhoto(Foto photo) {
        this.FotoFacade.edit(photo);
    }

    @Override
    public void removePhoto(Foto photo) {
        this.FotoFacade.remove(photo);
    }

    @Override
    public Integer countPhotos() {
        return this.FotoFacade.findAll().size();
    }
    
    @Override
    public List<Foto> getPhotoRange(int[] i) {
        return this.FotoFacade.findRange(i);
    }
    
    @Override
    public List<Foto> getPhotosByUser(Usuario user) {   
        Collection<Foto> collection = user.getFotoCollection();
        List<Foto> photosUser = new ArrayList<>();
        
        for(Foto f: this.FotoFacade.findAll()){
            if(f.getIdUsuario().equals(user)){
                photosUser.add(f);
            }    
        }
        return photosUser;
    }

    @Override
    public List<Foto> getLatestPhotosSystem(int lot) {
        List<Foto> photos = FotoFacade.findAll();
        Collections.sort(photos, new Comparator<Foto>() {
        @Override
        public int compare(Foto f1, Foto f2) {
        return f2.getFechaCarga().compareTo(f1.getFechaCarga());
        }});
       if (photos.size()>lot){
          return photos.subList(0,lot);
       }else{
          return photos;
       }
    }

    @Override
    public List<Foto> getLatestPhotosByUser(Usuario u, int lot) {
         Collection<Foto> CollectionPhotos = u.getFotoCollection();
         List<Foto> Photos = new ArrayList<>();
         for(Foto f : CollectionPhotos){
            Photos.add(f);
         }
         
         Collections.sort(Photos, new Comparator<Foto>() {
         @Override
         public int compare(Foto f1, Foto f2) {
         return f2.getFechaCarga().compareTo(f1.getFechaCarga());
         }});
         
       if (Photos.size() > lot){
           return Photos.subList(0, lot);
       }else{
           return Photos;
       }
    }

    @Override
    public List<Foto> getLatestUserPhotosEtiqueta(Usuario user, int lot) {
             Collection<Etiqueta> collectionEtiquetaUser = user.getEtiquetaCollection();
             List<Foto> arrayPhoto = new ArrayList<>();
             for(Etiqueta e : collectionEtiquetaUser){
                 arrayPhoto.add(e.getIdFoto());
             }
             
             Collections.sort(arrayPhoto, new Comparator<Foto>() {
             @Override
             public int compare(Foto f1, Foto f2) {
             return f2.getFechaCarga().compareTo(f1.getFechaCarga());
             }});
             
             if (arrayPhoto.size() > lot){
                return arrayPhoto.subList(0, lot);
             }else{
                return arrayPhoto;
             }
    }
    
}
