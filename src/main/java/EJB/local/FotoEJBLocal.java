package EJB.local;

/**
 *
 * @author elias
 */
import java.util.List;
import javax.ejb.Local;
import MODEL.Foto;
import java.util.ArrayList;

@Local
public interface FotoEJBLocal {
        
        Foto get(int id);
        
        void add(Foto foto);
	
	void update(Foto foto);
        
        void remove(Foto foto);

        List<Foto> findRange(int[] i);

        Object count();

        List<Foto> getFotosbyUserId(int id);
        
        List<Foto> getFotosRecientes(int cantidad);
        
       
        
}
