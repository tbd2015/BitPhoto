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
import EJB.local.AlbumEJBLocal;
import EJB.local.FotoEJBLocal;
import EJB.local.TagEJBLocal;
import EJB.local.UsuarioEJBLocal;
import FACADE.AlbumFotoEJBFacade;
import MODEL.Album;
import MODEL.AlbumFoto;
import MODEL.Foto;
import MODEL.Tag;
import MODEL.TagFoto;
import MODEL.Usuario;
import SERVICE.JsonFormat.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;

@Path("/tags")
@Produces({"application/json"})
public class TagService {
    @EJB
    TagEJBLocal TagEJB;
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    FotoEJBLocal FotoEJB;
    
    @GET
    @Path("{idPhoto}")
    @Produces({"application/json"})
    public String getTagsbyIdPhoto(@PathParam("idPhoto") String idphoto){
          try{  
            Foto f = FotoEJB.getPhoto(Integer.parseInt(idphoto));
            List<Tag> tags = new ArrayList<>();
            for(TagFoto tf: f.getTagFotoCollection()){
               tags.add(tf.getIdTag());
            }
            TagJsonFormat tg = new TagJsonFormat();
            return tg.TagsToJsonPrivate(tags).toString();
           }catch(Exception e){
                return "{ \"success\": false, \"message\": \"foto no encontrada\" }";
           }
    }
    
    @POST
    @Path("{idPhoto}/{Correo}/post")
    @Produces({"application/json"})
    @Consumes(MediaType.APPLICATION_JSON)
    public String postTagbyIdPhoto(@PathParam("idPhoto") String idphoto, @PathParam("correo") String correo, Tag tag){
          try{  
            Usuario u = UsuarioEJB.findUserByEmail(correo);
            Foto f = FotoEJB.getPhoto(Integer.parseInt(idphoto));
            TagEJB.addTagPhoto(tag,f);
            return "{ \"success\": true, \"message\": \"tag agregado exitosamente\" }";
           }catch(Exception e){
                return "{ \"success\": false, \"message\": \"foto no encontrada\" }";
           }
    }
    
    @DELETE
    @Path("{idPhoto}/{Correo}/delete")
    @Produces({"application/json"})
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteTagbyIdPhoto(@PathParam("idPhoto") String idphoto, @PathParam("correo") String correo, Tag tag){
          try{  
            Usuario u = UsuarioEJB.findUserByEmail(correo);
            Foto f = FotoEJB.getPhoto(Integer.parseInt(idphoto));
            TagEJB.deleteTagPhoto(tag, f);
            return "{ \"success\": true, \"message\": \"tag agregado exitosamente\" }";
           }catch(Exception e){
                return "{ \"success\": false, \"message\": \"foto no encontrada\" }";
           }
    }
    
    
    
    
}
