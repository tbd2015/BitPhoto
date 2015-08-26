package SERVICE.JsonFormat;

import javax.json.*;
import MODEL.Tag;
import java.util.List;

public class TagJsonFormat {
     public TagJsonFormat(){
    
    }
    
    public JsonObject TagToJsonPublic(Tag f){
            JsonObjectBuilder FotoBuilder = Json.createObjectBuilder();
            String nulo = null; 
            FotoBuilder
                    .add("IdTag", f.getIdTag())
                    .add("NombreTag", f.getNombreTag());
                    JsonObject FotoJsonObject = FotoBuilder.build();
            return FotoJsonObject;
    }
    
    public JsonObject TagToJsonPrivate(Tag cf){
            return TagToJsonPublic(cf);
    }
    
    public JsonArray TagsToJsonPublic(List<Tag> tags){
            JsonArrayBuilder TagArrayBuilder = Json.createArrayBuilder(); 
            for(Tag f : tags){
            TagArrayBuilder.add(TagToJsonPublic(f));
            }
            return TagArrayBuilder.build();
    }
    
    public JsonArray TagsToJsonPrivate(List<Tag> tags){
            JsonArrayBuilder tagsArrayBuilder = Json.createArrayBuilder(); 
            for(Tag f : tags){
            tagsArrayBuilder.add(TagToJsonPrivate(f));
            }
            return tagsArrayBuilder.build();    
    }
}
