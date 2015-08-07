package EJB.local;

/**
 *
 * @author elias
 */
import java.util.List;
import javax.ejb.Local;

import MODEL.FavoritosFoto;
import MODEL.Foto;
import MODEL.Usuario;

@Local
public interface FavoritosFotoEJBLocal {
    
        void addFavourite(FavoritosFoto photoFavourite); // Add a new relation favourite - photo to the system
        FavoritosFoto getFavourite(int IdFavoritosFoto); // Get FavouritePhoto information
        void removeFavourite(FavoritosFoto photoFavourite); // Remove a realtion favourite - photo
        List<Usuario> getFavouriteUsersPhoto(Foto photo); // Get a list of users thats have a photo like favourite
        List<Foto> getPhotosFavouriteFromUser(Usuario user); // Get a list of photos that a user have like a favourite
        List<FavoritosFoto> getFavouritePhotoFromUser(Usuario user);
}
