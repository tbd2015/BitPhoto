package EJB;

/**
 *
 * @author elias
 */
import EJB.local.AlbumEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.AlbumEJBFacade;
import MODEL.Album;

@Stateless
public class AlbumEJB implements AlbumEJBLocal{
        @EJB
	AlbumEJBFacade AlbumFacade;
	
    public AlbumEJB() {
       
    }
    
    @Override
    public List<Album> get() {

            return this.AlbumFacade.findAll();
    }
	
    @Override
    public Album get(int id) {
            Album c = new Album();
            c.setIdAlbum(id);
            return this.AlbumFacade.find(c);
    }

    @Override
    public void add(Album album) {

            this.AlbumFacade.create(album);

    }

    @Override
    public void update(Album album) {

            this.AlbumFacade.edit(album);

    }
    
    @Override
    public void remove(Album album) {
        
            this.AlbumFacade.remove(album);

    }

    @Override
    public List<Album> findRange(int[] i) {
        
           return this.AlbumFacade.findRange(i);
           
    }

    @Override
    public Object count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         //int id = userid;
         //return this.AlbumFacade.count(AlbumEJB.this.get(id));
    }
    
    
}
