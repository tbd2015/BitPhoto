package EJB.local;

/**
 *
 * @author elias
 */

import java.util.List;
import javax.ejb.Local;
import java.util.Date;
import MODEL.Album;
import MODEL.Usuario;

@Local
public interface AlbumEJBLocal {
        
        List<Album> getAll(); // Obtener todos los albumes
	
	Album get(int idAlbum); // Obtener datos de 1 album
	
	void add(Album album); // Agregar album
	
	void update(Album album); // Actualizar album
        
        void remove(Album album); // remove album
        
        List<Album> findRangeDate(int idUsuario, Date fechainicio , Date fechafin); // entrega albunes rango de fecha
               
        Object count(int idUsuario); // Numero de albumes por usuario
    
    
}
