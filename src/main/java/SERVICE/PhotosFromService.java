package SERVICE;

//import java.util.List;
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
import EJB.local.UsuarioUsuarioEJBLocal;
import MODEL.FavoritosFoto;
import MODEL.Foto;
import MODEL.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PUT;
import SERVICE.JsonFormat.*;
import javax.json.*;

@Path("/photosfrom")
@Produces({"application/json"})
public class PhotosFromService {
    
    @EJB
    FotoEJBLocal FotoEJB;
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    UsuarioUsuarioEJBLocal UsuarioUsuarioEJB;
    
        @GET
        @Produces({"application/json"})
        @Path("{cant}/{correo}")
        public String getphotosfrom(@PathParam("cant") String cantidad, @PathParam("correo") String correo){
            Usuario u = UsuarioEJB.findUserByEmail(correo);
            List<Usuario> UsuariosFollows = UsuarioUsuarioEJB.getUsersFollow(u);
            if (u != null) {  
              if(!UsuariosFollows.isEmpty()){
                  String JsonList = ListToJsonPhotosFrom(u, Integer.parseInt(cantidad), UsuariosFollows);
                  return JsonList;
              }else{ // no sigue usuarios
               return "{ \"success\": false, \"message\": \"El usuario no sigue a otros usuarios\"}";   
              } 
            } else { // no existe usuario
              return "{ \"success\": false, \"message\": \"Correo no encontrado\" }"; 
            }
        }
              

    private String ListToJsonPhotosFrom(Usuario u, int cant, List<Usuario> UsuariosFollows) {
            String success = "{ \"success\": true";
            String menssage = ", \"message\": \"Usuario encontrado y sigue a otros usuarios\"";

            String usuario = ",\n  \"usuario\": { \"idusuario\": \""+u.getIdUsuario()+"\", \"alias\": \""+u.getAlias()+"\", \"nombre_real\": \""+u.getNombrereal()+"\", \"apellido\": \""+u.getApellido()+"\", \"descripcion\": \""+u.getDescripcion()+"\", \"seguidores\": \""+u.getCantSeguidores()+"\", \"seguidos\": \""+u.getCantSeguidos()+"\", \"cantidad_fotos\": \""+u.getCantFotos()+"\", \"cantidad_albumes\": \""+u.getCantAlbum()+"\"}";

            String json1 = ",\n  \"usuarios\": { \"numusuarios\": \""+UsuariosFollows.size()+"\",\n    \"usuariosfollows\": [\n";
            String json2 = "  ]  }  }";    
            String salida = success+menssage+usuario+json1;

            String Usuarios[] = new String[UsuariosFollows.size()];        
            for(int i = 0; i< UsuariosFollows.size(); i++){
               if(i!= UsuariosFollows.size()-1){
                List<Foto> photosUser = FotoEJB.getLatestPhotosByUser(UsuariosFollows.get(i), cant);
                Usuarios[i] =  "{ \"idusuario\": \""+UsuariosFollows.get(i).getIdUsuario()+"\", \"alias\": \""+UsuariosFollows.get(i).getAlias()+"\", \"numfotos\": \""+photosUser.size()+"\", \"photos\": ";
                FotoJsonFormat fformat = new FotoJsonFormat();
                String photos = fformat.FotosToJsonPrivate(photosUser).toString();
                String usuarioout = " } ,\n";
                Usuarios[i] = Usuarios[i].concat(photos).concat(usuarioout);
               }else{
                List<Foto> photosUser = FotoEJB.getLatestPhotosByUser(UsuariosFollows.get(i), cant);
                Usuarios[i] =  "{ \"idusuario\": \""+UsuariosFollows.get(i).getIdUsuario()+"\", \"alias\": \""+UsuariosFollows.get(i).getAlias()+"\", \"numfotos\": \""+photosUser.size()+"\", \"photos\": ";
                FotoJsonFormat fformat = new FotoJsonFormat();
                String photos;
                photos = fformat.FotosToJsonPrivate(photosUser).toString();
                String usuarioout = " } ";
                Usuarios[i] = Usuarios[i].concat(photos).concat(usuarioout);
               }
                salida = salida.concat(Usuarios[i]);
            }
            salida = salida.concat(json2);
            return salida;
        }
}
