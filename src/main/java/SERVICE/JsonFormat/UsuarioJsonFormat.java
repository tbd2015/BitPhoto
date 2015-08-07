package SERVICE.JsonFormat;

import MODEL.Foto;
import javax.json.*;
import MODEL.Usuario;
import java.util.List;

public class UsuarioJsonFormat {

    public UsuarioJsonFormat(){
    
    }
    
    public JsonObject UsuarioToJsonPublic(Usuario u){
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            usuarioBuilder
                    .add("idUsuario", u.getIdUsuario())
                    .add("correo", u.getCorreo())
                    .add("alias", u.getAlias())
                    .add("descripcion",u.getDescripcion())
                    .add("urlfoto",u.getUrlAvatar())
                    .add("urlperfil",u.getUrlPerfil())
                    .add("cantidad_seguidores",u.getCantSeguidores())
                    .add("cantidad_seguidos",u.getCantSeguidos());
            JsonObject usuarioJsonObject = usuarioBuilder.build();
            return usuarioJsonObject;
    }
    
    public JsonObject UsuarioToJsonPrivate(Usuario u){
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            usuarioBuilder
                    .add("idUsuario", u.getIdUsuario())
                    .add("correo", u.getCorreo())
                    .add("alias", u.getAlias())
                    .add("apellido",u.getApellido())
                    .add("nombre",u.getNombrereal())
                    .add("descripcion",u.getDescripcion())
                    .add("urlfoto",u.getUrlAvatar())
                    .add("urlperfil",u.getUrlPerfil())
                    .add("cantidad_albumes",u.getCantAlbum())
                    .add("cantidad_seguidores",u.getCantSeguidores())
                    .add("cantidad_seguidos",u.getCantSeguidos())
                    .add("fecha_creacion", (String) (u.getFechaCreacion() == null ? "": u.getFechaCreacion().toString()))
                    .add("cantidad_fotos",u.getCantFotos());
            JsonObject usuarioJsonObject = usuarioBuilder.build();
            return usuarioJsonObject;
    }
    
    public JsonObject UsuarioFotosToJsonPrivate(Usuario u, List<Foto> fotos){
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            FotoJsonFormat ft = new FotoJsonFormat();
            usuarioBuilder
                    .add("idUsuario", u.getIdUsuario())
                    .add("correo", u.getCorreo())
                    .add("alias", u.getAlias())
                    .add("apellido",u.getApellido())
                    .add("nombre",u.getNombrereal())
                    .add("descripcion",u.getDescripcion())
                    .add("urlfoto",u.getUrlAvatar())
                    .add("urlperfil",u.getUrlPerfil())
                    .add("cantidad_albumes",u.getCantAlbum())
                    .add("cantidad_seguidores",u.getCantSeguidores())
                    .add("cantidad_seguidos",u.getCantSeguidos())
                    .add("fecha_creacion", (String) (u.getFechaCreacion() == null ? "": u.getFechaCreacion().toString()))
                    .add("cantidad_fotos",u.getCantFotos())
                    .add("fotos", ft.FotosToJsonPrivate(fotos));
            JsonObject usuarioJsonObject = usuarioBuilder.build();
            return usuarioJsonObject;
           
    }
    
    public JsonArray UsuariosToJsonPublic(List<Usuario> usuarios){
            JsonArrayBuilder usuarioArrayBuilder = Json.createArrayBuilder(); 
            for(Usuario u : usuarios){
            usuarioArrayBuilder.add(UsuarioToJsonPublic(u));
            }
            return usuarioArrayBuilder.build();
    
    }
    
    public JsonArray UsuariosToJsonPrivate(List<Usuario> usuarios){
            JsonArrayBuilder usuarioArrayBuilder = Json.createArrayBuilder(); 
            for(Usuario u : usuarios){
            usuarioArrayBuilder.add(UsuarioToJsonPrivate(u));
            }
            return usuarioArrayBuilder.build();
    }
    
     public JsonArray UsuariosFotoToJsonPrivate(List<Usuario> usuarios){
            JsonArrayBuilder usuarioArrayBuilder = Json.createArrayBuilder(); 
            for(Usuario u : usuarios){
            
            usuarioArrayBuilder.add(UsuarioToJsonPrivate(u));
            }
            return usuarioArrayBuilder.build();
    }
}
