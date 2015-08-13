package SERVICE;

//import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import java.util.Date;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.json.*;

@Path("/register")
public class RegisterService{
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    
	@POST
        @Produces({"application/json"})
        @Consumes(MediaType.APPLICATION_JSON)
        public String create(Usuario user){
            try {
                int op = UsuarioEJB.findUserByEmailOrAlias(user.getCorreo(),user.getAlias());
                if(op == 1){// Email ya existe
                    return "{ \"success\": false, \"message\": \"Otro usuario esta utilizando el Correo Electronico\" }";
                }else if(op == 2){ // Alias ya existe
                    return "{ \"success\": false, \"message\": \"Otro usuario esta utilizando el alias\" }";
                }else{
                    
                    Usuario u = new Usuario();
                    u.setNombrereal(user.getNombrereal());
                    u.setApellido(user.getApellido());
                    u.setAlias(user.getAlias());
                    u.setCorreo(user.getCorreo());
                    u.setContrasena(user.getContrasena());
                    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                   
                    u.setDescripcion("Nuevo usuario BitPhoto");
                    
                    
                   UsuarioEJB.addUser(u);

                   return "{ \"success\": true, \"message\": \"Registro de usuario exitoso\" , \"usuario\" : \""+u.getCorreo()+"\"}";
                     
    
                            }
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
            }
        }
}
           

