package EJB;
/**
 *
 * @author elias
 */
import EJB.local.AlbumFotoEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.AlbumFotoEJBFacade;
import MODEL.AlbumFoto;

@Stateless
public class AlbumFotoEJB implements AlbumFotoEJBLocal{
        @EJB
	AlbumFotoEJBFacade AlbumFotoFacade;
	
    public AlbumFotoEJB() {
       
    }
    
    @Override
    public List<AlbumFoto> get() {

            return this.AlbumFotoFacade.findAll();
    }
	
    //@Override
    //public AlbumFoto get(int id) {
    //        AlbumFoto c = new AlbumFoto();
    //        c.AlbumFoto(id);
    //        return this.AlbumFacade.find(c);
    //}

    /*@Override
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
    }*/
    
    
}
