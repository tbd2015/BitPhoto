package SERVICE;
import java.io.File;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/images")
@ApplicationPath("/")
public class ImageService {
    
    
    @GET
    @Path("png")
    @Produces( "image/jpg" )
    public File getImage() {
    File repositoryFile = new File("resources/photos/Por-qué-los-gatos-ronronean.jpg");
    
    return repositoryFile;
    }
  
    
    
}
