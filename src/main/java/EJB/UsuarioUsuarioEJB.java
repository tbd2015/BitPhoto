package EJB;

import EJB.local.UsuarioUsuarioEJBLocal;

import FACADE.UsuarioEJBFacade;
import FACADE.UsuarioUsuarioEJBFacade;

import MODEL.Usuario;
import MODEL.UsuarioUsuario;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class UsuarioUsuarioEJB implements UsuarioUsuarioEJBLocal{
    @EJB
    UsuarioEJBFacade UsuarioFacade;
    @EJB
    UsuarioUsuarioEJBFacade UsuarioUsuarioFacade;
     
    public UsuarioUsuarioEJB(){
    
    } 
    
    @Override
    public List<Usuario> getUsersFollow(Usuario user) {
        List<Usuario> UsuariosFollowsUser = new ArrayList<>();
        for( UsuarioUsuario u : user.getUsuarioUsuarioCollection()){
            UsuariosFollowsUser.add(UsuarioFacade.find(u.getIdUsuariofollow()));
        }
        return UsuariosFollowsUser;    
    }

    @Override
    public void addUserFollowUser(UsuarioUsuario userUser) {
        this.UsuarioUsuarioFacade.create(userUser);
    }

    @Override
    public void userUnfollowUser(Usuario user, Usuario unfollow) {
        UsuarioUsuario userUser = new UsuarioUsuario();
        for(UsuarioUsuario u : user.getUsuarioUsuarioCollection()){
            if(UsuarioFacade.find(u.getIdUsuariofollow()).equals(unfollow)){
              userUser = u;
            }
        }
        UsuarioUsuarioFacade.remove(userUser);
    }

    @Override
    public List<Usuario> ComunUsersFollows(Usuario user1, Usuario user2) {
        List<Usuario> UsuariosFollowsUser1 = new ArrayList<>();
        for( UsuarioUsuario u : user1.getUsuarioUsuarioCollection()){
            UsuariosFollowsUser1.add(UsuarioFacade.find(u.getIdUsuariofollow()));
        }
        List<Usuario> UsuariosFollowsUser2 = new ArrayList<>();
        for( UsuarioUsuario u : user2.getUsuarioUsuarioCollection()){
            UsuariosFollowsUser1.add(UsuarioFacade.find(u.getIdUsuariofollow()));
        }
        List<Usuario> commonUsers = new ArrayList<>();
        for(Usuario u: UsuariosFollowsUser1){
            if(UsuariosFollowsUser2.contains(u)){
              commonUsers.add(u);
            }
        }
        return commonUsers;    
    }  

    @Override
    public List<Usuario> getUsersFollower(Usuario userfollow) {
        List<Usuario> userUser = new ArrayList<>();
        for( UsuarioUsuario u : UsuarioUsuarioFacade.findAll()){
            if(UsuarioFacade.find(u.getIdUsuariofollow()).equals(userfollow)){
              userUser.add(UsuarioFacade.find(u.getIdUsuario()));
            }
        }
        return userUser;
    }
    
}
