package EJB.local;

/**
 *
 * @author elias
 */
import MODEL.Album;
import java.util.List;
import javax.ejb.Local;

import MODEL.Clasificacion;
import MODEL.ComentarioFoto;
import MODEL.Foto;

@Local
public interface ClasificacionEJBLocal {
    
    List<ComentarioFoto> getCommentsByClassification(Clasificacion classification); // Return a ComentarioFoto list whit the classification
    Integer getCommentsByPhoto(Foto photo, String classification); // Return the number of comments of a photo whit classification
    Integer getCommentsByPhotosAlbum(Album album, String classification); // Return the number total of comments of a album (al photos) whit classification

}