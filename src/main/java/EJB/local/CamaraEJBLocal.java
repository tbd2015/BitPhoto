package EJB.local;

import java.util.List;
import javax.ejb.Local;

import MODEL.Camara;
import MODEL.Foto;
import MODEL.Usuario;

@Local
public interface CamaraEJBLocal {
    
    List<Camara> getCameras(); // Get all system cameras
    List<Camara> getCamerasRange(int[] i); // Get a range of system cameras
    Camara getCamera(int IdCamara); // Get camera by IdCamara
    void addCamera(Camara camera); // Add an album in the system
    void updateCamera(Camara camera); // Update an album in the system
    void removeCamera(Camara camera); // Remove a camera in the system
    List<Foto> getPhotosByCamera(Camara camera); // Returns a photo list that uses the camera
    List<Camara> getCamerasByUser(Usuario user); // Returns a camara list that uses an user
    List<Usuario> getUsersByCamera(Camara camera); // Returns a user list thst uses the camera
    
}
