package SERVICE;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import EJB.local.AlbumEJBLocal;
import EJB.local.FotoEJBLocal;
import EJB.local.UsuarioEJBLocal;
import FACADE.AlbumFotoEJBFacade;
import MODEL.Album;
import MODEL.AlbumFoto;
import MODEL.Foto;
import MODEL.Usuario;
import SERVICE.JsonFormat.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces({"application/json"})
public class UsuarioService {
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    
    @GET
    @Path("{correo}")
    @Produces({"application/json"})
    public String getUserbyEmail(@PathParam("correo") String email){
          Usuario u = UsuarioEJB.findUserByEmail(email);
      if(u != null){
          UsuarioJsonFormat ujf = new UsuarioJsonFormat();
          String salida = ujf.UsuarioToJsonPrivate(u).toString();
          return salida;
                  
      }else{
          return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";
      }

    }
}
