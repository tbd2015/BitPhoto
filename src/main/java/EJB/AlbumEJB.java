package EJB;

/**
 *
 * @author elias
 */
import EJB.local.AlbumEJBLocal;
import EJB.local.UsuarioEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.AlbumEJBFacade;
import FACADE.AlbumFotoEJBFacade;
import FACADE.UsuarioEJBFacade;
import MODEL.Album;
import MODEL.AlbumFoto;
import MODEL.Foto;
import MODEL.Usuario;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class AlbumEJB implements AlbumEJBLocal{
        @EJB
	AlbumEJBFacade AlbumFacade;
        @EJB
        AlbumFotoEJBFacade AlbumFotoFacade;
        @EJB
        UsuarioEJBLocal UsuarioEJB;
	
    public AlbumEJB() {
       
    }
    
    @Override
    public List<Album> get() {

            return this.AlbumFacade.findAll();
    }
	
    @Override
    public Album getAlbum(int id) {
            return AlbumFacade.find((Object)id);
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

    @Override
    public List<Album> getAlbumesbycorreo(String correo) {
         Usuario u = UsuarioEJB.findbycorreo(correo);
         if(u!=null){
         List<Album> listAlbum = new ArrayList(u.getAlbumCollection());
         return listAlbum;
         }
         return null;    
    }

    @Override
    public Foto getFotobyIdAlbum(int idAlbum) {
           Album album = AlbumFacade.find(idAlbum);
           Collection<AlbumFoto> af = album.getAlbumFotoCollection();
           Foto primerafoto = null;
           if(!af.isEmpty()){
            for(AlbumFoto a: af){
                primerafoto = a.getFoto();
                if(primerafoto != null) break;
            }
           }
           return primerafoto;
    }

    @Override
    public List<Foto> getFotosbyIdAlbum(int idAlbum) {
           Album album =  this.AlbumFacade.find(idAlbum);   
           Collection<AlbumFoto> af = album.getAlbumFotoCollection();
           List<Foto> fotos = new ArrayList<Foto>();
           for(AlbumFoto a: af){
               fotos.add(a.getFoto());
           }
           return fotos;
    
    }
    
}
