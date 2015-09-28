package EJB;

import EJB.local.ComentarioFotoEJBLocal;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import FACADE.ComentarioFotoEJBFacade;
import MODEL.Clasificacion;

import MODEL.ComentarioFoto;
import MODEL.Foto;
import MODEL.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;

@Stateless
public class ComentarioFotoEJB implements ComentarioFotoEJBLocal{
    @EJB
    ComentarioFotoEJBFacade comentarioFotoEJB;
    
    public ComentarioFotoEJB(){
    
    }

    @Override
    public ComentarioFoto getCommentPhoto(int IdComentarioFoto) {
        return comentarioFotoEJB.find(IdComentarioFoto);
    }

    @Override
    public Clasificacion getCommentClassificationPhoto(int IdComentarioFoto) {
        return comentarioFotoEJB.find(IdComentarioFoto).getIdClasificacion();
    }

    @Override
    public List<ComentarioFoto> getCommentsByPhoto(Foto photo) {
        Collection<ComentarioFoto> CollectionPhotoComments = photo.getComentarioFotoCollection();
        List<ComentarioFoto> photoComments = new ArrayList<>();
        
        for(ComentarioFoto cf: comentarioFotoEJB.findAll()){
            if(cf.getIdFoto().equals(photo)){
              photoComments.add(cf);
            }
        }
        return photoComments;
    }

    @Override
    public List<ComentarioFoto> getCommentsByPhotoAndUser(Foto photo, Usuario user) {
        Collection<ComentarioFoto> CollectionPhotoComments = photo.getComentarioFotoCollection();   
        List<ComentarioFoto> photoComments = new ArrayList<>();
        for(ComentarioFoto cf: CollectionPhotoComments){
            if(cf.getIdUsuario().equals(user) == true){
               photoComments.add(cf);
            }
        }
        return photoComments;
    }

    @Override
    public List<ComentarioFoto> getCommentsByUser(Usuario user) {
         Collection<ComentarioFoto>  CollectionPhotoComments = user.getComentarioFotoCollection();
         List<ComentarioFoto> photoComments = new ArrayList<>();
         for(ComentarioFoto cf: comentarioFotoEJB.findAll()){
             if(cf.getIdUsuario().equals(user)){
             photoComments.add(cf);
             }  
         }
         return photoComments;
    }

    @Override
    public void addCommentPhoto(ComentarioFoto comment) {
       
        this.comentarioFotoEJB.create(comment);
    }

    @Override
    public void removeCommentPhoto(ComentarioFoto comment) {
        this.comentarioFotoEJB.remove(comment);
    }

    @Override
    public void updateCommentPhoto(ComentarioFoto comment) {
        this.comentarioFotoEJB.edit(comment);
    }
     
}
