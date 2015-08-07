package EJB;

import EJB.local.UsuarioEJBLocal;

import FACADE.FavoritosFotoEJBFacade;
import FACADE.FotoEJBFacade;
import FACADE.UsuarioEJBFacade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import MODEL.Usuario;

@Stateless
public class UsuarioEJB implements UsuarioEJBLocal{
    @EJB
    UsuarioEJBFacade UsuarioFacade;
    @EJB
    FavoritosFotoEJBFacade FavoritoFacade;
    @EJB
    FotoEJBFacade FotoFacade;
    
    public UsuarioEJB() {
       
    }
   
    @Override
    public List<Usuario> getUsers() {
        return this.UsuarioFacade.findAll();
    }

    @Override
    public Usuario getUser(int IdUsuario) {
       return this.UsuarioFacade.find(IdUsuario);
    }

    @Override
    public Usuario getUserConfirmByEmailAndPass(String email, String password) {
        List<Usuario> users = this.UsuarioFacade.findAll();
        for (Usuario u : users) {
            Integer id = u.getIdUsuario();
            String Correo = u.getCorreo();
            String Contraseña = u.getContrasena();
            if(Correo.compareTo(email)== 0 && password.compareTo(Contraseña) == 0){
                return u;
            }
        }       
        return null;
}

    @Override
    public void addUser(Usuario user) {
       this.UsuarioFacade.create(user);
    }

    @Override
    public void updateUser(Usuario user) {
       this.UsuarioFacade.edit(user);
    }

    @Override
    public void removeUser(Usuario user) {
       this.UsuarioFacade.remove(user);
    }

    @Override
    public List<Usuario> GetUserRange(int[] i) {
       return this.UsuarioFacade.findRange(i);
    }

    @Override
    public Integer countUsers() {
       return this.UsuarioFacade.findAll().size();
    }

    @Override
    public int findUserByEmailOrAlias(String email, String alias) {
        List<Usuario> users = this.UsuarioFacade.findAll();
        for (Usuario u : users) {
            if(u.getCorreo().compareTo(email)== 0){
                return 1;
            }
            if(u.getAlias().compareTo(alias) == 0){
                return 2;
            } 
        }       
        return -1;
    }
    
    @Override
    public Usuario findUserByAlias(String alias) {
        List<Usuario> users = this.UsuarioFacade.findAll();
        for (Usuario u : users) {
            if(u.getAlias().compareTo(alias) == 0){
                return u;
            } 
        }       
        return null;
    }

    @Override
    public Usuario findUserByEmail(String email) {
        List<Usuario> users = this.UsuarioFacade.findAll();
        for (Usuario u : users) {
            if(u.getCorreo().compareTo(email) == 0){
                return u;
            } 
        }       
        return null;
    }

}
