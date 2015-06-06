package EJB.local;

/**
 *
 * @author elias
 */
import java.util.List;
import javax.ejb.Local;
import MODEL.AlbumFotoPK;

public interface AlbumFotoPKEJBLocal {
    
        List<AlbumFotoPK> get();
	
	/*AlbumFotoPK get(int id);
	
	void add(AlbumFotoPK album);
	
	void update(AlbumFotoPK album);
        
        void remove(AlbumFotoPK album);

        List<AlbumFotoPK> findRange(int[] i);

        Object count();*/
}
