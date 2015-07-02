package EJB;

import EJB.local.FotoEJBLocal;
import FACADE.AlbumFotoEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.FotoEJBFacade;
import MODEL.Album;
import MODEL.Foto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@Stateless
public class FotoEJB implements FotoEJBLocal{
    
    @EJB
    FotoEJBFacade FotoFacade;
    @EJB 
    AlbumFotoEJBFacade AlbumFotoFacade;
	
    public FotoEJB() {
       
    }

    @Override
    public Foto get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Foto foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Foto foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Foto foto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Foto> findRange(int[] i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Foto> getFotosbyUserId(int idUser) {   
        List<Foto> photos = FotoFacade.findAll();
        List<Foto> photosUser = new ArrayList<Foto>();
        for (Foto f : photos) {
            if(f.getIdUsuario() == idUser){
                photosUser.add(f);
            }
        }       
        return photosUser;

    }

    @Override
    public List<Foto> getFotosRecientes(int cantidad) {
         List<Foto> fotos = FotoFacade.findAll();
         Collections.sort(fotos, new Comparator<Foto>() {
         @Override
         public int compare(Foto f1, Foto f2) {
         return f2.getFechaCarga().compareTo(f1.getFechaCarga());
         }});
           
       return fotos.subList(0,cantidad);
    }

    
}
