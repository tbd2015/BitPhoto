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
	
        void add(Usuario usuario);
	
	void update(Usuario usuario);
        
        void remove(Usuario usuario);
        
        int find(String email, String alias);
        
        Usuario findbyalias(String alias);

        List<Usuario> findRange(int[] i);

        Object count();

        public Usuario findbycorreo(String correo);
        
}
