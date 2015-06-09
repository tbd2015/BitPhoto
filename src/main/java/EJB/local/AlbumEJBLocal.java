package EJB.local;

/**
 *
 * @author elias
 */

import java.util.List;
import javax.ejb.Local;
//import java.util.Date;
import MODEL.Album;


@Local
public interface AlbumEJBLocal {
        
        List<Album> get();
        
        Album get(int id);
	
        void add(Album album);
	
	void update(Album album);
        
        void remove(Album album);

        List<Album> findRange(int[] i);

        Object count();

    
}
