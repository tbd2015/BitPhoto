package SERVICE;

//import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import EJB.local.UsuarioEJBLocal;
import MODEL.Usuario;
import javax.json.*;

@Path("/register")
public class RegisterService{
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    
	@POST
        @Produces({"application/json"})
        @Consumes(MediaType.APPLICATION_JSON)
        public String create(JsonObject user){
            try {
              int op = UsuarioEJB.find(user.getString("email"), user.getString("apodo"));
            if(op == 1){// Email ya existe
                return "{success: false, message: 'Otro usuario esta utilizando el email'}";
            }else if(op == 2){ // Alias ya existe
                return "{success: false, message: 'Otro usuario esta utilizando el alias'}";
            }else{
                Usuario u = new Usuario();
                u.setAlias(user.getString("apodo"));
                u.setApellido(user.getString("apellido"));
                u.setContrasena(user.getString("password"));
                u.setNombrereal(user.getString("nombre"));
                u.setCorreo(user.getString("email"));
                UsuarioEJB.add(u);
                
                return "{success: true , message: 'Registro de usuario exitoso'}";
            }
            }catch(Exception e){
            return "{success: false, message: 'problema en el sistema', trackerror: '"+e.getMessage()+"'}";
            }
                 
        }
}
           

