package SERVICE.JsonFormat;

import MODEL.Album;
import java.util.List;
import javax.json.*;

public class AlbumJsonFormat {
    
    public AlbumJsonFormat(){
    
    }
    
     public JsonObject AlbumToJsonPublic(Album a){
            JsonObjectBuilder albumBuilder = Json.createObjectBuilder();
            albumBuilder
                    .add("IdAlbum", a.getIdAlbum())
                    .add("nombre_album", a.getNombre())
                    .add("descripcion_album", a.getDescripcion())
                    .add("url_foto_album",a.getUrlfotoalbum())
                    .add("fecha_creacion",(String) (a.getFechaCreado()== null ? "": a.getFechaCreado().toString()))
                    .add("owner",a.getIdUsuario().getCorreo())
                    .add("cantidad_fotos",a.getCantFoto())
                    .add("cantidad_comentarios",a.getCantComentarios())
                    .add("cantidad_favoritos",a.getCantFav());
            JsonObject albumJsonObject = albumBuilder.build();
            return albumJsonObject;
    }
    
    public JsonObject AlbumToJsonPrivate(Album a){
            return AlbumToJsonPublic(a);
    }
    
    public JsonArray AlbumsToJsonPublic(List<Album> albumes){
            JsonArrayBuilder albumArrayBuilder = Json.createArrayBuilder(); 
            for(Album a : albumes){
            albumArrayBuilder.add(AlbumToJsonPublic(a));
            }
            return albumArrayBuilder.build();
    }
    
    public JsonArray AlbumsToJsonPrivate(List<Album> albumes){
           JsonArrayBuilder albumArrayBuilder = Json.createArrayBuilder(); 
            for(Album a : albumes){
            albumArrayBuilder.add(AlbumToJsonPrivate(a));
            }
            return albumArrayBuilder.build();
    }
    
}
