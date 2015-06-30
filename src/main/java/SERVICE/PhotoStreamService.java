package SERVICE;

//import java.util.List;
import EJB.local.FotoEJBLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import java.util.Date;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import EJB.local.UsuarioEJBLocal;
import MODEL.Foto;
import MODEL.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import javax.json.*;
import com.google.gson.*;
import org.json.simple.JSONArray;


@Path("/photoStream")
@Produces({"application/json"})
public class PhotoStreamService {
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    FotoEJBLocal FotoEJB;
    
	@GET
        @Produces({"application/json"})
        @Path("{correo}")
        public String getphotos(@PathParam("correo") String correo){
            Usuario u = UsuarioEJB.findbycorreo(correo);
            if (u != null) {  
              int id = u.getIdUsuario();
              List<Foto> fotos = FotoEJB.getFotosbyUserId(id);
              if(!fotos.isEmpty()){
                  String JsonList = ListToJson(fotos,u);
                  return JsonList;
              }else{
               return "{ \"success\": false, \"message\": \"El usuario no posee fotos\"}";   
              } 
            } else {
              return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";
              
            }
            
        }

    private String ListToJson(List<Foto> fotos, Usuario u) {
        
        String success = "{ \"success\": true";
        String menssage = ", \"message\": \"Usuario encontrado y posee fotografias\"";
        
        String usuario = ",\n  \"usuario\": { \"idusuario\": \""+u.getIdUsuario()+"\", \"alias\": \""+u.getAlias()+"\", \"nombre_real\": \""+u.getNombrereal()+"\", \"apellido\": \""+u.getApellido()+"\", \"descripcion\": \""+u.getDescripcion()+"\", \"seguidores\": \""+u.getCantSeguidores()+"\", \"seguidos\": \""+u.getCantSeguidos()+"\", \"cantidad_fotos\": \""+u.getCantFotos()+"\", \"cantidad_albumes\": \""+u.getCantAlbum()+"\"}";
        
        String json1 = ",\n  \"photos\": { \"numphotos\": \""+fotos.size()+"\",\n    \"photo\": [\n";
        String json2 = "  ]  }  }";    
        String salida = success+menssage+usuario+json1;
        
        String photos[] = new String[fotos.size()];        
        for(int i = 0; i< fotos.size(); i++){
           if(i!= fotos.size()-1){
            photos[i] =  "{ \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+"} ,\n";
           }else{
            photos[i] =  "{ \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+"}";
           }
               salida = salida.concat(photos[i]);
        }
        salida = salida.concat(json2);
        return salida;
        
    }
    
    
    
}
