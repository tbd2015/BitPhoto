package EJB;

/**
 *
 * @author elias
 */
import EJB.local.AlbumFotoPKEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.AlbumFotoPKEJBFacade;
import MODEL.AlbumFotoPK;

@Stateless
public class AlbumFotoPKEJB implements AlbumFotoPKEJBLocal{
    @EJB
    AlbumFotoPKEJBFacade AlbumFotoPKFacade;
	
    public AlbumFotoPKEJB() {
       
    }
    
    @Override
    public List<AlbumFotoPK> get() {

            return this.AlbumFotoPKFacade.findAll();
    }

    /*@Override
    public AlbumFotoPK get(int id) {
            AlbumFotoPK c = new AlbumFotoPK();
            c.setIdAlbum(id);
            return this.AlbumFotoPKFacade.find(c);
    }

    @Override
    public void add(AlbumFotoPK album) {

            this.AlbumFotoPKFacade.create(album);

    }

    @Override
    public void update(AlbumFotoPK album) {

            this.AlbumFotoPKFacade.edit(album);

    }
    
    @Override
    public void remove(AlbumFotoPK album) {
        
            this.AlbumFotoPKFacade.remove(album);

    }

    @Override
    public List<AlbumFotoPK> findRange(int[] i) {
        
           return this.AlbumFotoPKFacade.findRange(i);
           
    }

    @Override
    public Object count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         //int id = userid;
         //return this.AlbumFacade.count(AlbumEJB.this.get(id));
    }*/
    
    
}
