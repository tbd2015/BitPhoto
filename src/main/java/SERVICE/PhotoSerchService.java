
package SERVICE;

import EJB.local.ComentarioFotoEJBLocal;
import EJB.local.FavoritosFotoEJBLocal;
import EJB.local.FotoEJBLocal;
import EJB.local.TagEJBLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import java.util.Date;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import EJB.local.UsuarioEJBLocal;
import MODEL.ComentarioFoto;
import MODEL.Etiqueta;
import MODEL.FavoritosFoto;
import MODEL.Foto;
import MODEL.Usuario;
import SERVICE.JsonFormat.ComentarioFotoJsonFormat;
import SERVICE.JsonFormat.FotoJsonFormat;
import SERVICE.JsonFormat.UsuarioJsonFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import javax.json.*;
import com.google.gson.*;
import java.util.AbstractList;
import java.util.Collection;
import javax.ws.rs.PUT;
import org.json.simple.JSONArray;

@Path("/photoserch")
@Produces({"application/json"})
public class PhotoSerchService {
    @EJB
    TagEJBLocal TagEJB;
    
    @GET
    @Path("{frase}")
    @Produces({"application/json"})
    public String getTagsbyIdPhoto(@PathParam("frase") String frase){
          try{  
                
                return null;
           }catch(Exception e){
                return "{ \"success\": false, \"message\": \"foto no encontrada\" }";
           }
    }
}
