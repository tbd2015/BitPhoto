package EJB.local;

/**
 *
 * @author elias
 */
import java.util.List;
import javax.ejb.Local;
import MODEL.Usuario;

public interface UsuarioEJBLocal {
        
        List<Usuario> get();
        
        Usuario get(int id);
        
        Usuario get(String email, String pass);
	
        void add(Usuario album);
	
	void update(Usuario album);
        
        void remove(Usuario album);

        List<Usuario> findRange(int[] i);

        Object count();
}
