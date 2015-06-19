package EJB;

import EJB.local.UsuarioEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.UsuarioEJBFacade;
import MODEL.Usuario;

@Stateless
public class UsuarioEJB implements UsuarioEJBLocal{
    @EJB
    UsuarioEJBFacade UsuarioFacade;
	
    public UsuarioEJB() {
       
    }

    @Override
    public List<Usuario> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(String email, String pass) {
        List<Usuario> users = this.UsuarioFacade.findAll();
        for (Usuario u : users) {
            Integer id = u.getIdUsuario();
            String Correo = u.getCorreo();
            String Contraseña = u.getContrasena();
            if(Correo.compareTo(email)== 0 &&  pass.compareTo(Contraseña) == 0){
                return u;
            }
        }       
        return null;
}

    @Override
    public void add(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> findRange(int[] i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int find(String email, String alias) {
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
    
    
    
}
