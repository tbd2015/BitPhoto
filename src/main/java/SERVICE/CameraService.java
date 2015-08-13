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
import EJB.local.CamaraEJBLocal;

import MODEL.*;

import SERVICE.JsonFormat.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;

@Path("/camara")
@Produces({"application/json"})
public class CameraService {
    @EJB
    UsuarioEJBLocal UsuarioEJB;
    @EJB
    FotoEJBLocal FotoEJB;
    @EJB
    CamaraEJBLocal CamaraEJB;
    
    @GET
    @Path("{idphoto}")
    @Produces({"application/json"})
    public String getCameraInfobyPhotoId(@PathParam("idphoto") String Idphoto){
        try{
            Foto f = FotoEJB.getPhoto(Integer.parseInt(Idphoto));
            if (f != null && f.getIdCamara() != null){
                CamaraJsonFormat cjf = new CamaraJsonFormat();
                return "{ \"success\": true, \"message\": \"foto encontrada\", \"Camara\": "+
                        cjf.CamaraToJsonPrivate(f.getIdCamara())+"}";
            }
            return "{ \"success\": false, \"message\": \"foto no encontrada\" }";
        }catch(Exception e){
            return "{ \"success\": false, \"message\": \"Error interno\" }";
        }   
    }


    
}
