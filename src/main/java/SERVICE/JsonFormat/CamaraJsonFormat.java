package SERVICE.JsonFormat;

import MODEL.Camara;
import java.math.BigDecimal;
import java.util.List;
import javax.json.*;

public class CamaraJsonFormat {
    
    public CamaraJsonFormat(){
    
    }
    
     public JsonObject CamaraToJsonPublic(Camara c){
            JsonObjectBuilder camaraBuilder = Json.createObjectBuilder();
            camaraBuilder
                    .add("IdCamara", c.getIdCamara())
                    .add("nombre", c.getNombre())
                    .add("modelo", c.getModelo())
                    .add("megapixeles", c.getMegapixeles())
                    .add("urlfoto", c.getUrlFotoCamara())
                    .add("zoom",c.getZoom())
                    .add("cantidad_fotos",c.getCantFotos());
            JsonObject camaraJsonObject = camaraBuilder.build();
            return camaraJsonObject;
    }
    
    public JsonObject CamaraToJsonPrivate(Camara c){
            return CamaraToJsonPublic(c);
    }
    
    public JsonArray CamarasToJsonPublic(List<Camara> camaras){
            JsonArrayBuilder camaraArrayBuilder = Json.createArrayBuilder(); 
            for(Camara c : camaras){
            camaraArrayBuilder.add(CamaraToJsonPublic(c));
            }
            return camaraArrayBuilder.build();
            
    }
    
    public JsonArray CamarasToJsonPrivate(List<Camara> camaras){
            JsonArrayBuilder camaraArrayBuilder = Json.createArrayBuilder(); 
            for(Camara c : camaras){
            camaraArrayBuilder.add(CamaraToJsonPrivate(c));
            }
            return camaraArrayBuilder.build();   
    }
    
}
