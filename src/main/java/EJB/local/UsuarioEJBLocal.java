package EJB.local;

import java.util.List;
import javax.ejb.Local;

import MODEL.Usuario;

@Local
public interface UsuarioEJBLocal {
        
        List<Usuario> getUsers();
        Usuario getUser(int IdUsuario);
        Usuario getUserConfirmByEmailAndPass(String email, String password);
        void addUser(Usuario user);
	void updateUser(Usuario user);
        void removeUser(Usuario user);
        List<Usuario> GetUserRange(int[] i);
        Integer countUsers();
        int findUserByEmailOrAlias(String email, String alias);
        Usuario findUserByAlias(String alias);
        Usuario findUserByEmail(String email);
        
}
