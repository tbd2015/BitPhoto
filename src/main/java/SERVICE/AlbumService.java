package SERVICE;

/**
 *
 * @author elias
 */
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
import EJB.local.UsuarioEJBLocal;
import MODEL.Album;
import MODEL.Foto;
import MODEL.Usuario;
import java.util.ArrayList;



@Path("/albumes")
@Produces({"application/json"})
public class AlbumService {
        @EJB
	AlbumEJBLocal AlbumEJB;
	@EJB
	UsuarioEJBLocal UsuarioEJB;
	
        /*@GET
	@Produces({"application/json"})
	public List<Album> findAll(){
		return albumEJB.get();
	}
	
	@GET
        @Path("{id}")
        @Produces({"application/json"})
        public Album find(@PathParam("id") Integer id) {
                return albumEJB.get(id);
        }
        
        @GET
        @Path("{from}/{to}")
        @Produces({"application/json"})
        public List<Album> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
            return albumEJB.findRange(new int[]{from, to});
        }
        
        @GET
        @Path("count")
        @Produces("text/plain")
        public String countREST() {
            return String.valueOf(albumEJB.count());
        }
	
	@POST
        @Consumes({"application/json"})
        public void create(Album entity) {
                albumEJB.add(entity);
        }

        @PUT
        @Path("{id}")
        @Consumes({"application/json"})
        public void edit(@PathParam("id") Integer id, Album entity) {
                albumEJB.update(entity);
        }
        
        @DELETE
        @Path("{id}")
        public void remove(@PathParam("id") Integer id, Album entity) {
               entity = AlbumService.this.find(id);
               albumEJB.remove(entity);
        }
        */
        
        @GET
        @Path("{correo}")
        @Produces({"application/json"})
        public String getAlbumesbyCorreo(@PathParam("correo") String correo){
              Usuario u = UsuarioEJB.findbycorreo(correo);
              List<Album> albumes = AlbumEJB.getAlbumesbycorreo(correo);
              List<Foto>  fotoalbum = new ArrayList<Foto>();
              for(Album a: albumes){
                    fotoalbum.add(AlbumEJB.getFotobyIdAlbum(a.getIdAlbum()));
              }
              
          if(u != null){
              if (albumes != null){
                  if(fotoalbum.size() != 0){
                        String JsonOut = JsonOut(u,albumes,fotoalbum);
                        return JsonOut;
                       
                  }else{
                        String JsonOut = JsonOut(u,albumes);
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
	public String getFotosAlbumbyIdAlbum(@PathParam("correo") String correo, @PathParam("idAlbum") Integer idAlbum){
              Usuario u = UsuarioEJB.findbycorreo(correo);
              Album album = AlbumEJB.getAlbum(idAlbum);
              List<Foto> fotos = AlbumEJB.getFotosbyIdAlbum(idAlbum);       
                if(u != null){
                    if (fotos != null){
                        String JsonOut = JsonOutAlbum(u,album,fotos);
                        return JsonOut;
                    }else{
                        String JsonOut = JsonOutAlbum(u,album, null);
                        return JsonOut;
                    }
                }else{
                    return "{ \"success\": false, \"message\": \"Correo no encontrado\" }";
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
