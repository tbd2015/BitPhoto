package EJB.local;
/**
 *
 * @author elias
 */

import java.util.List;
import javax.ejb.Local;
import MODEL.AlbumFoto;

@Local
public interface AlbumFotoEJBLocal {
   
        List<AlbumFoto> get();
	
	//AlbumFoto get(int id);
	
	//void add(AlbumFoto album);
	
	//void update(AlbumFoto album);
        
        //void remove(AlbumFoto album);

        //List<AlbumFoto> findRange(int[] i);

        //Object count();
    
}
