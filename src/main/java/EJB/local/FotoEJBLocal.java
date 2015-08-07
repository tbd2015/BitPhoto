package EJB.local;


import java.util.List;
import javax.ejb.Local;

import MODEL.Foto;
import MODEL.Usuario;


@Local
public interface FotoEJBLocal {
        
        Foto getPhoto(int IdFoto); // Get the Photo info by IdFoto
        void addPhoto(Foto photo); // Add a new Photo to the system
	void updatePhoto(Foto photo); // Update the Photo entity
        void removePhoto(Foto photo); // Remove the Photo Entity
        Integer countPhotos(); // Return the number of photos in the system
        List<Foto> getPhotoRange(int[] i); // Return a list of photos from all system
        List<Foto> getPhotosByUser(Usuario user); // Return all photos from a user
        List<Foto> getLatestPhotosSystem(int lot); // Return the lasted photos addess to the system
        List<Foto> getLatestPhotosByUser(Usuario user, int lot); // Return the lasted photos addess by a user to the system
        
}
