package EJB;

import EJB.local.FotoEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import FACADE.FotoEJBFacade;
import MODEL.Foto;
import java.util.ArrayList;


@Stateless
public class FotoEJB implements FotoEJBLocal{
    
    @EJB
    FotoEJBFacade FotoFacade;
	
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
}
