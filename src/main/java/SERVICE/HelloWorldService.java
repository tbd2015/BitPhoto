package SERVICE;

import EJB.local.FotoEJBLocal;
import EJB.local.UsuarioEJBLocal;
import MODEL.Foto;
import MODEL.Usuario;
import MODEL.UsuarioUsuario;
import SERVICE.JsonFormat.FotoJsonFormat;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import com.google.gson.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.json.*;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import SERVICE.JsonFormat.UsuarioJsonFormat;


@Path("/helloworld")
@ApplicationPath("/")
@Produces({"application/json"})
public class HelloWorldService {
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    FotoEJBLocal FotoEJB;
     
    @GET
    @Path("{correo}")
    public String getMessage(@PathParam("correo") String correo) {
        
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        JsonArrayBuilder usuariosArrayBuilder = Json.createArrayBuilder();
                 
        Usuario User = UsuarioEJB.findUserByEmail(correo);
        Collection<UsuarioUsuario> usersFollows = User.getUsuarioUsuarioCollection();
        List<Usuario> users = new ArrayList<>();
        for(UsuarioUsuario u : usersFollows){
            users.add(UsuarioEJB.getUser(u.getIdUsuariofollow()));
        }
         UsuarioJsonFormat uf = new UsuarioJsonFormat();
        //for(Usuario u : users){
        //    usuariosArrayBuilder.add(uf.UsuarioToJsonPublic(u));
        //}
        
        jsonBuilder.add("usuariosfollows", uf.UsuariosToJsonPrivate(users));
        FotoJsonFormat ff = new FotoJsonFormat();
        
        //return uf.UsuariosToJsonPrivate(users).toString();
        List<Foto> fotos = new ArrayList<>();
        for(Foto f :users.get(1).getFotoCollection()){
           fotos.add(f);
        }
        
        //return String.valueOf(FotoEJB.getLatestPhotosByUser(users.get(1), 5).size());
        //return FotoEJB.getLatestPhotosByUser(users.get(1),5).get(1).getTitulo();
          return String.valueOf(users.get(1).getFotoCollection().size());
    }
    
    
    /*@GET
    @Path("{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getMessage(@PathParam("correo") String correo) {
        
        Usuario User = UsuarioEJB.findbycorreo(correo);
        Collection<UsuarioUsuario> usersFollows = User.getUsuarioUsuarioCollection();
        List<Usuario> users = new ArrayList<Usuario>();
        for(UsuarioUsuario u : usersFollows){
            users.add(UsuarioEJB.get(u.getIdUsuariofollow()));
        }
        return users;
    }*/
	
}
