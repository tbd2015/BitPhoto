package SERVICE.JsonFormat;

import javax.json.*;
import MODEL.ComentarioFoto;
import java.util.List;

public class ComentarioFotoJsonFormat {
    
    public ComentarioFotoJsonFormat(){
    
    }

    public JsonObject ComentarioFotoToJsonPublic(ComentarioFoto cf){
            JsonObjectBuilder ComentarioFotoBuilder = Json.createObjectBuilder();
            ComentarioFotoBuilder
                    .add("IdComentarioFoto", cf.getIdComentarioFoto())
                    .add("comentario", cf.getComentarioFoto())
                    .add("IdUsuario", cf.getIdUsuario().getIdUsuario())
                    .add("fecha_creacion", (String) (cf.getFechaComentarioFoto() == null ? "": cf.getFechaComentarioFoto().toString()))
                    .add("IdClasificacion",(String) (cf.getIdClasificacion() == null ? "": cf.getIdClasificacion().getIdClasificacion()))
                    .add("IdFoto", cf.getIdFoto().getIdFoto());
            JsonObject comentarioFotoJsonObject = ComentarioFotoBuilder.build();
            return comentarioFotoJsonObject;
    }
    
    public JsonObject ComentarioFotoToJsonPrivate(ComentarioFoto cf){
            return ComentarioFotoToJsonPublic(cf);
    }
    
    public JsonArray ComentariosFotoToJsonPublic(List<ComentarioFoto> cfs){
            JsonArrayBuilder comentarioFotoArrayBuilder = Json.createArrayBuilder(); 
            for(ComentarioFoto cf : cfs){
            comentarioFotoArrayBuilder.add(ComentarioFotoToJsonPublic(cf));
            }
            return comentarioFotoArrayBuilder.build();
    
    }
    
    public JsonArray ComentariosFotoToJsonPrivate(List<ComentarioFoto> cfs){
            JsonArrayBuilder comentarioFotoArrayBuilder = Json.createArrayBuilder(); 
            for(ComentarioFoto cf : cfs){
            comentarioFotoArrayBuilder.add(ComentarioFotoToJsonPrivate(cf));
            }
            return comentarioFotoArrayBuilder.build();   
    }
    
    
}
