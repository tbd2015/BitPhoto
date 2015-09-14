package SERVICE;

import EJB.local.ComentarioFotoEJBLocal;
import EJB.local.FavoritosFotoEJBLocal;
import EJB.local.FotoEJBLocal;
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


@Path("/cameraroll")
@Produces({"application/json"})
public class CameraRollService {
    @EJB
    FotoEJBLocal FotoEJB;
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    
    @GET
    @Produces({"application/json"})
    @Path("{correo}")
    public String getphotosrecientes(@PathParam("correo") String correo){
        Usuario u = UsuarioEJB.findUserByEmail(correo);
        List<Foto> fotos = FotoEJB.getPhotosByUser(u);
        if (u != null) {  
          if(!fotos.isEmpty()){
              String JsonList = ListJsonCameraRoll(fotos,u);
              return JsonList;
          }else{
           return "{ \"success\": false, \"message\": \"El usuario no posee fotos\"}";   
          } 
        } else {
          return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";

        }
    }

    private String ListJsonCameraRoll(List<Foto> fotos, Usuario u) {
           ArrayList<String> m = new ArrayList<>();
           String[ ] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };  
           
           //for(Foto f: fotos){
           //  
           //}
          return "";
    }
}
