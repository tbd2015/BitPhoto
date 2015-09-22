package EJB;

import EJB.local.ClasificacionEJBLocal;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import FACADE.ClasificacionEJBFacade;

import MODEL.Album;
import MODEL.AlbumFoto;
import MODEL.Clasificacion;
import MODEL.ComentarioFoto;
import MODEL.Foto;

import java.util.ArrayList;
import java.util.Collection;

@Stateless 
public class ClasificacionEJB implements ClasificacionEJBLocal{
    @EJB
    ClasificacionEJBFacade clasificacionEJB;
    
    public ClasificacionEJB(){
    
    }

    @Override
    public List<ComentarioFoto> getCommentsByClassification(Clasificacion classification) {
        Collection<ComentarioFoto> collectionPhotoComments = classification.getComentarioFotoCollection();
        List<ComentarioFoto> photoComments = new ArrayList<>();
        for(ComentarioFoto f: collectionPhotoComments){
            photoComments.add(f);
        }
        return photoComments;
    }

    @Override
    public Integer getCommentsByPhoto(Foto photo, String classification) {
           Collection<ComentarioFoto> collectionPhotoComments =  photo.getComentarioFotoCollection();
           int countClassification = 0;
           for(ComentarioFoto cf : collectionPhotoComments){
              if(classification.compareTo(cf.getIdClasificacion().getNombreClas()) == 0 && cf.getIdClasificacion() != null){
                 countClassification++;             
              }
           }
           return countClassification;
    }

    @Override
    public Integer getCommentsByPhotosAlbum(Album album, String classification) {
           Collection<AlbumFoto> collectionAlbumPhoto =  album.getAlbumFotoCollection();
           int countClassification = 0;
           for(AlbumFoto af: collectionAlbumPhoto){
            countClassification = countClassification + getCommentsByPhoto(af.getFoto(),classification);
           }
           return countClassification;
    }  

    @Override
    public Clasificacion getClasificacion(String nombre) {
           return clasificacionEJB.findAll().get(0);
    }
}
