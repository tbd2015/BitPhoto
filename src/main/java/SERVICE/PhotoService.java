package SERVICE;

//import java.util.List;
import EJB.local.ClasificacionEJBLocal;
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
import MODEL.Clasificacion;
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

@Path("/photo")
@Produces({"application/json"})
public class PhotoService {
    @EJB
    FotoEJBLocal FotoEJB;
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    FavoritosFotoEJBLocal FavoritosFotoEJB;
    @EJB
    ComentarioFotoEJBLocal  ComentarioFotoEJB;
    @EJB
    ClasificacionEJBLocal ClasificacionEJB;
    
        @GET
        @Produces({"application/json"})
        @Path("{correo}/{cantidad}")
        public String getphotosrecientes(@PathParam("correo") String correo, @PathParam("cantidad") String cantidad){
            Usuario u = UsuarioEJB.findUserByEmail(correo);
            List<Foto> fotos = FotoEJB.getLatestPhotosSystem(Integer.parseInt(cantidad));
            if (u != null) {  
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
        
        @GET
        @Produces({"application/json"})
        @Path("{correo}/favoritos")
        public String getphotosfavrecientes(@PathParam("correo") String correo){
            Usuario u = UsuarioEJB.findUserByEmail(correo);
            List<Foto> fotos = FavoritosFotoEJB.getPhotosFavouriteFromUser(u);
            List<FavoritosFoto> fav = FavoritosFotoEJB.getFavouritePhotoFromUser(u);
            if (u != null) {  
              if(!fotos.isEmpty()){
                  String JsonList = ListToJsonFav(fotos,u,fav);
                  return JsonList;
              }else{
               return "{ \"success\": false, \"message\": \"El usuario no posee fotos\"}";   
              } 
            } else {
              return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";
              
            }
        }
        
        @POST
        @Produces({"application/json"})
        @Path("{correo}/favoritofoto")
        @Consumes(MediaType.APPLICATION_JSON)
        public String createFavouritePhoto(@PathParam("correo") String correo ,Foto f){
            try {
                FavoritosFoto fv = new FavoritosFoto();
                fv.setIdUsuario(UsuarioEJB.findUserByEmail(correo));
                int IdPhoto = f.getIdFoto();
                fv.setIdFoto(FotoEJB.getPhoto(IdPhoto));
                FavoritosFotoEJB.addFavourite(fv);
                return "{ \"success\": true, \"message\": \"Operacion de favorito foto exitosa\" , \"usuario\" : \""+UsuarioEJB.findUserByEmail(correo)+"\"}";
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
            }
        }
        
        @POST
        @Produces({"application/json"})
        @Path("{correo}/etiquetar")
        @Consumes(MediaType.APPLICATION_JSON)
        public String createEtiquetaPhoto(Etiqueta e){
            try {
                Etiqueta en = new Etiqueta();
                en.setIdUsuario(UsuarioEJB.getUser(e.getIdUsuario().getIdUsuario()));
                en.setIdFoto(FotoEJB.getPhoto(e.getIdFoto().getIdFoto()));
                FotoEJB.getPhoto(e.getIdFoto().getIdFoto()).getEtiquetaCollection().add(en);
                return "{ \"success\": true, \"message\": \"Operacion de favorito foto exitosa\" }";
            }catch(Exception ex){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+ex.getMessage()+"\" }";
            }
        }
        
        @DELETE
        @Produces({"application/json"})
        @Path("{correo}/desEtiquetar")
        @Consumes(MediaType.APPLICATION_JSON)
        public String deleteEtiquetaPhoto(Etiqueta e){
            try {
                Etiqueta en = new Etiqueta();
                en.setIdUsuario(UsuarioEJB.getUser(e.getIdUsuario().getIdUsuario()));
                en.setIdFoto(FotoEJB.getPhoto(e.getIdFoto().getIdFoto()));
                FotoEJB.getPhoto(e.getIdFoto().getIdFoto()).getEtiquetaCollection().remove(en);
                return "{ \"success\": true, \"message\": \"Operacion de favorito foto exitosa\" }";
            }catch(Exception ex){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+ex.getMessage()+"\" }";
            }
        }
        
        @GET
        @Produces({"application/json"})
        @Path("{correo}/{idphoto}/etiquetas")
        public String getEtiquetasPhotos(@PathParam("correo") String correo, @PathParam("idphoto") String idphoto){
              Foto photo = FotoEJB.getPhoto(Integer.parseInt(idphoto));
              Collection<Etiqueta> collectionEtiquetaPhotos = photo.getEtiquetaCollection();
              List<Usuario> userEtiquetaPhotos = new ArrayList<>();
              for(Etiqueta e: collectionEtiquetaPhotos){
                 userEtiquetaPhotos.add(e.getIdUsuario());
              }
              UsuarioJsonFormat jfu = new UsuarioJsonFormat();
              return jfu.UsuariosToJsonPrivate(userEtiquetaPhotos).toString();
        }
        
        @GET
        @Produces({"application/json"})
        @Path("{correo}/photosEtiquetado")
        public String getPhotosEtiquetas(@PathParam("correo") String correo){
           Collection<Etiqueta> collectionEtiqueta = UsuarioEJB.findUserByEmail(correo).getEtiquetaCollection();
           List<Foto> photosEtiquetaUser = new ArrayList<>();
           for(Etiqueta e :collectionEtiqueta){
              photosEtiquetaUser.add(e.getIdFoto());
           }
           FotoJsonFormat fjf = new FotoJsonFormat();
           return fjf.FotosToJsonPrivate(photosEtiquetaUser).toString();
        }
        
        
        
        @POST
        @Produces({"application/json"})
        @Path("{correo}/comentarioPhoto/{idphoto}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String createCommentPhoto(@PathParam("correo") String correo, @PathParam("idphoto") String idphoto, ComentarioFoto cf){
            try {
                ComentarioFoto cphoto = new ComentarioFoto();
                cphoto.setIdUsuario(UsuarioEJB.findUserByEmail(correo));
                cphoto.setIdFoto(FotoEJB.getPhoto(Integer.parseInt(idphoto)));
                cphoto.setComentarioFoto(cf.getComentarioFoto());
                cphoto.setIdClasificacion(ClasificacionEJB.getClasificacion(correo));
                ComentarioFotoEJB.addCommentPhoto(cphoto);
                return "{ \"success\": true, \"message\": \"Operacion de comentar foto exitosa\" , \"usuario\" : \""+UsuarioEJB.findUserByEmail(correo)+"\"}";
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
            }
        }
        
        @POST
        @Produces({"application/json"})
        @Path("{correo}/comentarioUpd")
        @Consumes(MediaType.APPLICATION_JSON)
        public String deleteCommentPhoto(ComentarioFoto cf){
            try {
                ComentarioFotoEJB.updateCommentPhoto(cf);
                return "{ \"success\": true, \"message\": \"Operacion de actualizacion comentario exitosa\" , \"usuario\" : \""+UsuarioEJB.findUserByAlias(cf.getIdUsuario().getAlias()).getCorreo()+"\"}";
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
            }
        }
        
        @GET
        @Produces({"application/json"})
        @Path("{correo}/comentarioGet/{IdComment}")
        public String GetCommentPhoto(@PathParam("correo") String correo, @PathParam("IdComment") String Idcomment){
            try {
                ComentarioFotoJsonFormat cff = new ComentarioFotoJsonFormat();
                return cff.ComentarioFotoToJsonPrivate(ComentarioFotoEJB.getCommentPhoto(Integer.parseInt(Idcomment))).toString();
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
            }
        }
        
        @GET
        @Produces({"application/json"})
        @Path("{correo}/comentario/{IdPhoto}")
        public String GetAllCommentsPhoto(@PathParam("correo") String correo, @PathParam("IdPhoto") String IdPhoto){
            try {
                ComentarioFotoJsonFormat cff = new ComentarioFotoJsonFormat();
                return cff.ComentariosFotoToJsonPrivate(ComentarioFotoEJB.getCommentsByPhoto(FotoEJB.getPhoto(Integer.parseInt(IdPhoto)))).toString();
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
            }
        }
        
        @GET
        @Produces({"application/json"})
        @Path("/get/{IdPhoto}")
        public String GetPhoto(@PathParam("IdPhoto") String IdPhoto){
            try {
                FotoJsonFormat ff = new FotoJsonFormat();
                return ff.FotoToJsonPrivate(FotoEJB.getPhoto(Integer.parseInt(IdPhoto))).toString();
            }catch(Exception e){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
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
            photos[i] =  "{ \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+",\"owner\": \""+UsuarioEJB.getUser(fotos.get(i).getIdUsuario().getIdUsuario()).getAlias()+"\" } ,\n";
           }else{
            photos[i] =  "{ \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+",\"owner\": \""+UsuarioEJB.getUser(fotos.get(i).getIdUsuario().getIdUsuario()).getAlias()+"\" }";
           }
               salida = salida.concat(photos[i]);
        }
        salida = salida.concat(json2);
        return salida;
        
    }

        private String ListToJsonFav(List<Foto> fotos, Usuario u, List<FavoritosFoto> fav) {
            String success = "{ \"success\": true";
            String menssage = ", \"message\": \"Usuario encontrado y posee fotografias favortias\"";
            String usuario = ",\n  \"usuario\": { \"idusuario\": \""+u.getIdUsuario()+"\", \"alias\": \""+u.getAlias()+"\", \"nombre_real\": \""+u.getNombrereal()+"\", \"apellido\": \""+u.getApellido()+"\", \"descripcion\": \""+u.getDescripcion()+"\", \"seguidores\": \""+u.getCantSeguidores()+"\", \"seguidos\": \""+u.getCantSeguidos()+"\", \"cantidad_fotos\": \""+u.getCantFotos()+"\", \"cantidad_albumes\": \""+u.getCantAlbum()+"\"}";
            String json1 = ",\n  \"photosfav\": { \"numphotosfav\": \""+fotos.size()+"\",\n    \"photo\": [\n";
            String json2 = "  ]  }  }";    
            String salida = success+menssage+usuario+json1;

            String photos[] = new String[fotos.size()];        
            for(int i = 0; i< fotos.size(); i++){
               if(i!= fotos.size()-1){
                photos[i] =  "{ \"fechafav\": \""+getFavouritePhoto(fav,fotos.get(i)).getFechaFavFoto()+"\" ,\"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+", \"owner\": \""+UsuarioEJB.getUser(fotos.get(i).getIdUsuario().getIdUsuario()).getAlias()+"\" } ,\n";
               }else{
                photos[i] =  "{ \"fechafav\": \""+getFavouritePhoto(fav,fotos.get(i)).getFechaFavFoto()+"\" ,\"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+", \"owner\": \""+UsuarioEJB.getUser(fotos.get(i).getIdUsuario().getIdUsuario()).getAlias()+"\" } ";
               }
                   salida = salida.concat(photos[i]);
            }
            salida = salida.concat(json2);
            return salida;  

        }
        
        public FavoritosFoto getFavouritePhoto(List<FavoritosFoto> ff, Foto photo){
             for(FavoritosFoto f : ff){
                 if(f.getIdFoto().equals(photo)){
                    return f;
                 }
             }
             return null;
        }
       
        @POST
        @Produces({"application/json"})
        @Path("{correo}/subir")
        @Consumes(MediaType.APPLICATION_JSON)
        public String createPhoto(@PathParam("correo") String correo, Foto foto){
            try {
                Foto f = new Foto();
                Usuario u = UsuarioEJB.findUserByEmail(correo);
                f.setIdUsuario(u);
                f.setCantCom(0);
                f.setCantFavor(0);
                f.setDescripcion(foto.getDescripcion());
                f.setFechaCarga(null);
                f.setFechaTomada(foto.getFechaTomada());
                f.setFormato(foto.getFormato());
                f.setIdCamara(foto.getIdCamara());
                f.setPuntoLugar(foto.getPuntoLugar());
                f.setTitulo(foto.getTitulo());
                f.setUrl(foto.getUrl());
                f.setVistas(0);
                FotoEJB.addPhoto(f);
                return "{ \"success\": true, \"message\": \"Operacion de favorito foto exitosa\" }";
            }catch(Exception ex){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+ex.getMessage()+"\" }";
            }
        }
        
        @DELETE
        @Produces({"application/json"})
        @Path("{correo}/delphoto")
        @Consumes(MediaType.APPLICATION_JSON)
        public String deleteEtiquetaPhoto(@PathParam("correo") String correo,Foto f){
            try {
                if(UsuarioEJB.findUserByEmail(correo).equals(UsuarioEJB.getUser(f.getIdUsuario().getIdUsuario()))){
                FotoEJB.removePhoto(FotoEJB.getPhoto(f.getIdFoto()));
                return "{ \"success\": true, \"message\": \"Operacion de borrar foto exitosa\" }";
                }else{
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \"Usuario sin permiso de borrar\" }";
                }
            }catch(Exception ex){
                return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+ex.getMessage()+"\" }";
            }
        }
        
}

       
