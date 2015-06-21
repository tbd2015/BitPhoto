package SERVICE;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import EJB.local.UsuarioEJBLocal;
import MODEL.Usuario;
import javax.ws.rs.PathParam;

@Path("/login")
public class LoginService{
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    
	@GET
        @Produces({"application/json"})
        @Path("{correo}/{contrasena}")
        public String find(@PathParam("correo") String correo, @PathParam("contrasena") String contrasena){
            Usuario u = UsuarioEJB.get(correo, contrasena);
            if (u != null) {
              return "{ \"success\": \"true\" }"; 

            } else {
              return "{ \"success\": \"false\", \"message\": \"Correo o contrasena son incorrectos\" }";
            }
        }
    }
           
