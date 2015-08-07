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
import EJB.local.UsuarioEJBLocal;
import FACADE.AlbumFotoEJBFacade;
import MODEL.Album;
import MODEL.AlbumFoto;
import MODEL.Foto;
import MODEL.Usuario;
import SERVICE.JsonFormat.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;



@Path("/albumes")
@Produces({"application/json"})
public class AlbumService {
    @EJB
    AlbumEJBLocal AlbumEJB;
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    FotoEJBLocal FotoEJB;

    @GET
    @Path("{correo}")
    @Produces({"application/json"})
    public String getAlbumesbyCorreo(@PathParam("correo") String email){
          Usuario u = UsuarioEJB.findUserByEmail(email);
          List<Album> albums = AlbumEJB.getAlbumsByUser(u);
          List<Foto>  photoAlbum = new ArrayList<>();
          for(Album a: albums){
                photoAlbum.add(AlbumEJB.getPhotoByIdAlbum(a.getIdAlbum()));
          }

      if(u != null){
          if (albums != null){
              if(!photoAlbum.isEmpty()){
                    String JsonOut = JsonOut(u,albums,photoAlbum);
                    return JsonOut;

              }else{
                    String JsonOut = JsonOut(u,albums);
                    return JsonOut;

              }
          }else{
              return "{ \"success\": false, \"message\": \"El usuario no posee albumes\" }";

          }
      }else{
          return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";
      }

    }


    @GET
    @Path("{correo}/{idAlbum}")
    @Produces({"application/json"})
    public String getFotosAlbumbyIdAlbum(@PathParam("correo") String email, @PathParam("idAlbum") Integer IdAlbum){
          Usuario u = UsuarioEJB.findUserByEmail(email);
          Album album = AlbumEJB.getAlbum(IdAlbum);
          List<Foto> photos = AlbumEJB.getPhotosByIdAlbum(IdAlbum);       
            if(u != null){
                if (photos != null){
                    String JsonOut = JsonOutAlbum(u,album,photos);
                    return JsonOut;
                }else{
                    String JsonOut = JsonOutAlbum(u,album, null);
                    return JsonOut;
                }
            }else{
                return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";
            }
    }
    
    @POST
    @Path("{correo}/AlbumFoto")
    @Produces({"application/json"})
    @Consumes(MediaType.APPLICATION_JSON)
    public String createAlbumPhoto(AlbumFoto af){
        try {
            Foto foto = FotoEJB.getPhoto(af.getFoto().getIdFoto());
            AlbumEJB.addAlbumPhoto(AlbumEJB.getAlbum(af.getAlbum().getIdAlbum()), foto);
            return "{ \"success\": true, \"message\": \"Operacion de favorito foto exitosa\" }";
        }catch(Exception e){
            return "{ \"success\": false, \"message\": \"Hay problemas en el sistema\", \"trackerror\": \""+e.getMessage()+"\" }";
        }
    }
    
    
    private String JsonOut(Usuario u, List<Album> albumes) {

        String success = "{ \"success\": true";
        String menssage = ", \"message\": \"El usuario tiene albunes sin fotos\"";

        String usuario = ",\n  \"usuario\": { \"idusuario\": \""+u.getIdUsuario()+"\", \"alias\": \""+u.getAlias()+"\", \"nombre_real\": \""+u.getNombrereal()+"\", \"apellido\": \""+u.getApellido()+"\", \"descripcion\": \""+u.getDescripcion()+"\", \"seguidores\": \""+u.getCantSeguidores()+"\", \"seguidos\": \""+u.getCantSeguidos()+"\", \"cantidad_fotos\": \""+u.getCantFotos()+"\", \"cantidad_albumes\": \""+u.getCantAlbum()+"\"}";

        String json1 = ",\n  \"albumes\": { \"numalbumes\": \""+albumes.size()+"\",\n    \"album\": [\n";
        String json2 = "  ]  }  }";    
        String salida = success+menssage+usuario+json1;

            String album[] = new String[albumes.size()];        
            for(int i = 0; i< albumes.size(); i++){
               if(i!= albumes.size()-1){
                album[i] =  "{ \"idalbum\": \""+albumes.get(i).getIdAlbum()+"\", \"nombre_album\": \""+albumes.get(i).getNombre()+"\", \"descripcion\": \""+albumes.get(i).getDescripcion()+"\", \"cantidad_fotos\": \""+albumes.get(i).getCantFoto()+"\", \"fecha_creacion\": \""+albumes.get(i).getFechaCreado()+"\"} ,\n";
               }else{
                album[i] =  "{ \"idalbum\": \""+albumes.get(i).getIdAlbum()+"\", \"nombre_album\": \""+albumes.get(i).getNombre()+"\", \"descripcion\": \""+albumes.get(i).getDescripcion()+"\", \"cantidad_fotos\": \""+albumes.get(i).getCantFoto()+"\", \"fecha_creacion\": \""+albumes.get(i).getFechaCreado()+"\" }";
               }
                   salida = salida.concat(album[i]);
            }
            salida = salida.concat(json2);
            return salida;

    }

    private String JsonOut(Usuario u, List<Album> albumes, List<Foto> fotos) {
        String success = "{ \"success\": true";
        String menssage = ", \"message\": \"El usuario tiene albunes con fotos\"";

        String usuario = ",\n  \"usuario\": { \"idusuario\": \""+u.getIdUsuario()+"\", \"alias\": \""+u.getAlias()+"\", \"nombre_real\": \""+u.getNombrereal()+"\", \"apellido\": \""+u.getApellido()+"\", \"descripcion\": \""+u.getDescripcion()+"\", \"seguidores\": \""+u.getCantSeguidores()+"\", \"seguidos\": \""+u.getCantSeguidos()+"\", \"cantidad_fotos\": \""+u.getCantFotos()+"\", \"cantidad_albumes\": \""+u.getCantAlbum()+"\"}";

        String json1 = ",\n  \"albumes\": { \"numalbumes\": \""+albumes.size()+"\",\n    \"album\": [\n";
        String json2 = "  ]  }  }";    
        String salida = success+menssage+usuario+json1;

            String album[] = new String[albumes.size()];
            String photos[] = new String[fotos.size()];
            for(int i = 0; i< albumes.size(); i++){
               if(i!= albumes.size()-1){
                album[i] =  "{ \"idalbum\": \""+albumes.get(i).getIdAlbum()+"\", \"nombre_album\": \""+albumes.get(i).getNombre()+"\", \"descripcion\": \""+albumes.get(i).getDescripcion()+"\", \"cantidad_fotos\": \""+albumes.get(i).getCantFoto()+"\", \"fecha_creacion\": \""+albumes.get(i).getFechaCreado()+"\", \"foto_portada\": {\n   ";
                if(fotos.get(i) != null){
                photos[i] = " \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+"} } ,\n ";
                }else{
                photos[i] = " \"idfoto\": \"null\" } } ,\n ";
                }

               }else{
                album[i] =  "{ \"idalbum\": \""+albumes.get(i).getIdAlbum()+"\", \"nombre_album\": \""+albumes.get(i).getNombre()+"\", \"descripcion\": \""+albumes.get(i).getDescripcion()+"\", \"cantidad_fotos\": \""+albumes.get(i).getCantFoto()+"\", \"fecha_creacion\": \""+albumes.get(i).getFechaCreado()+"\", \"foto_portada\": {\n   ";
                if(fotos.get(i)!= null){
                photos[i] = " \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+"} }  ";
                }else{
                photos[i] = " \"idfoto\": \"null\" } } \n ";
                }


               }
                   salida = salida.concat(album[i]);
                   salida = salida.concat(photos[i]);
            }
            salida = salida.concat(json2);
            return salida;    

    }

    private String JsonOutAlbum(Usuario u, Album album, List<Foto> fotos) {
        String success = "{ \"success\": true";
        String menssage1 = ", \"message\": \"El album posee fotos\"";
        String menssage2 = ", \"message\": \"El album no posee fotos\"";
        String usuario = ",\n  \"usuario\": { \"idusuario\": \""+u.getIdUsuario()+"\", \"alias\": \""+u.getAlias()+"\", \"nombre_real\": \""+u.getNombrereal()+"\", \"apellido\": \""+u.getApellido()+"\", \"descripcion\": \""+u.getDescripcion()+"\", \"seguidores\": \""+u.getCantSeguidores()+"\", \"seguidos\": \""+u.getCantSeguidos()+"\", \"cantidad_fotos\": \""+u.getCantFotos()+"\", \"cantidad_albumes\": \""+u.getCantAlbum()+"\"}";        
        String json1 = ", \"album\": {\n \"idalbum\": \""+album.getIdAlbum()+"\", \"nombre_album\": \""+album.getNombre()+"\", \"descripcion\": \""+album.getDescripcion()+"\", \"cantidad_fotos\": \""+album.getCantFoto()+"\", \"fecha_creacion\": \""+album.getFechaCreado()+"\", \"fotos\": [\n    ";

        String json2 = "  ]  }  }"; 

        String salida;

        if(fotos != null){
            salida = success+menssage1+usuario+json1;
            String photos[] = new String[fotos.size()];        
            for(int i = 0; i< fotos.size(); i++){
               if(i!= fotos.size()-1){
                photos[i] =  "{ \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+"} ,\n    ";
               }else{
                photos[i] =  "{ \"idfoto\": \""+fotos.get(i).getIdFoto()+"\", \"titulo\": \""+fotos.get(i).getTitulo()+"\", \"descripcion\": \""+fotos.get(i).getDescripcion()+"\", \"formato\": \""+fotos.get(i).getFormato()+"\", \"urlserver\": \""+fotos.get(i).getUrl()+"\", \"idCamara\": \""+fotos.get(i).getIdCamara()+"\", \"fecha_carga\": \""+fotos.get(i).getFechaCarga().toString()+"\", \"fecha_toma\": \""+fotos.get(i).getFechaTomada()+"\", \"views\": "+fotos.get(i).getVistas()+", \"favoritos\": "+fotos.get(i).getCantFavor()+", \"comentarios\": "+fotos.get(i).getCantCom()+"} ";
               }
                   salida = salida.concat(photos[i]);
            }
        }else{
           salida = success+menssage2+usuario+json1;
        }    

        salida = salida.concat(json2);
        return salida;
    }
        
}
