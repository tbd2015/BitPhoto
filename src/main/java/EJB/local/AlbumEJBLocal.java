package EJB.local;

/**
 *
 * @author elias
 */

import java.util.List;
import javax.ejb.Local;
//import java.util.Date;
import MODEL.Album;
import MODEL.Foto;


@Local
public interface AlbumEJBLocal {
        
        List<Album> get();
        
        Album getAlbum(int idAlbum);
	
        void add(Album album);
	
	void update(Album album);
        
        void remove(Album album);

        List<Album> findRange(int[] i);

        Object count();
        
        List<Album> getAlbumesbycorreo(String correo);
        
        Foto getFotobyIdAlbum(int idAlbum);
        
        List<Foto> getFotosbyIdAlbum(int idAlbum);
}
