package SERVICE;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import EJB.local.UsuarioEJBLocal;
import MODEL.Usuario;

@Path("/login")
public class LoginService  {
    @EJB
    UsuarioEJBLocal UsuarioEJB;
	
        //@GET
        //@Produces({"application/json"})
        //public  String hola(@PathParam("email") String email) {
        //        return "hola mundo";
        //}
	
	@GET
        @Path("{email}/{pass}")
        @Produces({"application/json"})
        public Usuario find(@PathParam("email") String email,@PathParam("pass")  String pass) {
             Usuario u = UsuarioEJB.get(email,pass);
             return u;
            
        }
}
