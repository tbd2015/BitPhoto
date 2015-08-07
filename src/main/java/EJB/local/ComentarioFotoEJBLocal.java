package EJB.local;

import java.util.List;
import javax.ejb.Local;

import MODEL.ComentarioFoto;
import MODEL.Foto;
import MODEL.Usuario;
import MODEL.Clasificacion;

@Local
public interface ComentarioFotoEJBLocal {
    
    ComentarioFoto getCommentPhoto(int IdComentarioFoto); // Get photo comment by IdComentarioFoto
    Clasificacion getCommentClassificationPhoto(int IdComentarioFoto); // Get classification by IdComentarioFoto
    List<ComentarioFoto> getCommentsByPhoto(Foto photo); // Get photo commets from a photo
    List<ComentarioFoto> getCommentsByPhotoAndUser(Foto photo, Usuario user); // Get photo comments from a photo and a user(who made the comment)
    List<ComentarioFoto> getCommentsByUser(Usuario user); // Get photos comments from an user
    void addCommentPhoto(ComentarioFoto comment); // Add a new comment
    void removeCommentPhoto(ComentarioFoto comment); // Remove a comment of the system 
    void updateCommentPhoto(ComentarioFoto comment); // Update a comment
    
}
