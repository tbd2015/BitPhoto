package EJB.local;

import java.util.List;
import javax.ejb.Local;

import MODEL.Usuario;
import MODEL.UsuarioUsuario;

@Local
public interface UsuarioUsuarioEJBLocal {
    
    List<Usuario> getUsersFollow(Usuario user); // Return list of users that an user follows
    void addUserFollowUser(UsuarioUsuario userUser); // Add a relation user follow user
    void userUnfollowUser(Usuario user, Usuario unfollow); // Remove relation user follow user
    List<Usuario> ComunUsersFollows(Usuario user1, Usuario user2); // Return a list from users follow in comun
    List<Usuario> getUsersFollower(Usuario userfollow); // return a list of users that follow an user
     
}
