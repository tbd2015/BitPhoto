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
import MODEL.Album;



@Path("/albumes")
public class AlbumService {
        @EJB
	AlbumEJBLocal albumEJB;
	
	@GET
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
        
        
	
}
