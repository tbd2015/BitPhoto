package SERVICE;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import EJB.local.UsuarioEJBLocal;
import MODEL.Usuario;

@Path("/login")
public class LoginService{
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    
	@GET
        @Produces({"application/json"})
        @Consumes(MediaType.APPLICATION_JSON)
        public String find(Usuario user){
             Usuario u = UsuarioEJB.get(user.getCorreo() ,user.getContrasena());
             if(u != null){
               return "{ success: true }"; 
               
             }else{
               return "{ success: false, message: 'Correo o contraseña son incorrectos' }";
             }
             
            
        }
                }
           
