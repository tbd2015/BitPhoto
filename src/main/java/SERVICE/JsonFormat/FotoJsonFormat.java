package SERVICE.JsonFormat;

import javax.json.*;
import MODEL.Foto;
import java.util.List;

public class FotoJsonFormat {
    
    public FotoJsonFormat(){
    
    }
    
    public JsonObject FotoToJsonPublic(Foto f){
            JsonObjectBuilder FotoBuilder = Json.createObjectBuilder();
            String nulo = null; 
            FotoBuilder
                    .add("IdFoto", f.getIdFoto())
                    .add("IdUsuario", f.getIdUsuario().getIdUsuario())
                    .add("Formato", f.getFormato())
                    .add("Descripcion", f.getDescripcion())
                    .add("Titulo", f.getTitulo())
                    .add("Url", f.getUrl())
                    .add("Vistas", f.getVistas())
                    .add("Cantidad_comentarios", f.getCantCom())
                    .add("Cantidad_favoritos", f.getCantFavor())
                    .add("fecha_carga", (String) ((f.getFechaCarga() == null) ? "" : f.getFechaCarga().toString()))
                    .add("fecha_toma", (String) (f.getFechaTomada() == null ?  "" :f.getFechaTomada().toString()))
                    .add("IdCamara", (String) (f.getIdCamara() == null ? "" : String.valueOf(f.getIdCamara().getIdCamara())));
            JsonObject FotoJsonObject = FotoBuilder.build();
            return FotoJsonObject;
    }
    
    public JsonObject FotoToJsonPrivate(Foto cf){
            return FotoToJsonPublic(cf);
    }
    
    public JsonArray FotosToJsonPublic(List<Foto> fotos){
            JsonArrayBuilder fotoArrayBuilder = Json.createArrayBuilder(); 
            for(Foto f : fotos){
            fotoArrayBuilder.add(FotoToJsonPublic(f));
            }
            return fotoArrayBuilder.build();
    }
    
    public JsonArray FotosToJsonPrivate(List<Foto> fotos){
            JsonArrayBuilder fotoArrayBuilder = Json.createArrayBuilder(); 
            for(Foto f : fotos){
            fotoArrayBuilder.add(FotoToJsonPrivate(f));
            }
            return fotoArrayBuilder.build();    
    }
    
}
