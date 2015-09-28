package EJB;

import EJB.local.FavoritosFotoEJBLocal;

import FACADE.FavoritosFotoEJBFacade;

import MODEL.FavoritosFoto;
import MODEL.Usuario;
import MODEL.Foto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class FavoritosFotoEJB implements FavoritosFotoEJBLocal{
    @EJB
    FavoritosFotoEJBFacade FavoritosFotoFacade;
    
    public FavoritosFotoEJB(){
       
    }

    @Override
    public void addFavourite(FavoritosFoto photoFavourites) {
        boolean estado = false;
        for(FavoritosFoto f: FavoritosFotoFacade.findAll()){
           if (f.getIdFoto().equals(photoFavourites.getIdFoto()) && f.getIdUsuario().equals(photoFavourites.getIdUsuario())){
           estado = true;
           }
        }
        if(estado != true){
           this.FavoritosFotoFacade.create(photoFavourites);
        }
    }

    @Override
    public FavoritosFoto getFavourite(int IdFavoritosFoto) {
        return this.FavoritosFotoFacade.find(IdFavoritosFoto);
    }

    @Override
    public void removeFavourite(FavoritosFoto photoFavourite) {
        this.FavoritosFotoFacade.remove(photoFavourite);
    }

    @Override
    public List<Usuario> getFavouriteUsersPhoto(Foto photo) {
        Collection<FavoritosFoto> CollectionPhotoFavourite = photo.getFavoritosFotoCollection();
        List<Usuario> users = new ArrayList<>();
        for(FavoritosFoto ff: CollectionPhotoFavourite){
            if(!users.contains(ff.getIdUsuario()) && ff.getIdUsuario()!= null){
                users.add(ff.getIdUsuario());
            }
        }
        return users;
    }

    @Override
    public List<Foto> getPhotosFavouriteFromUser(Usuario user) {
           List<FavoritosFoto> favourite = FavoritosFotoFacade.findAll();
           List<Foto> favouriteByUser = new ArrayList<>();
           for (FavoritosFoto f : favourite){
              if(f.getIdUsuario().equals(user)){
                  favouriteByUser.add(f.getIdFoto());
              }
           }
           return favouriteByUser;
    }

    @Override
    public List<FavoritosFoto> getFavouritePhotoFromUser(Usuario user) {
             Collection<FavoritosFoto> collectionFavouritePhoto =  FavoritosFotoFacade.findAll();
             List<FavoritosFoto> listFavouritePhotoUser = new ArrayList<>();
             for(FavoritosFoto ff : collectionFavouritePhoto){
                 if(ff.getIdUsuario().equals(user)){
                     listFavouritePhotoUser.add(ff);
                 }
             }
           return listFavouritePhotoUser;
    }
    
}
