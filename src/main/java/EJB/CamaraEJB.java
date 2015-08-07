package EJB;

import EJB.local.CamaraEJBLocal;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import FACADE.CamaraEJBFacade;
import FACADE.UsuarioEJBFacade;

import MODEL.Camara;
import MODEL.Foto;
import MODEL.Usuario;

import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class CamaraEJB implements CamaraEJBLocal{
    @EJB
    CamaraEJBFacade CamaraEJB;
    @EJB
    UsuarioEJBFacade UsuarioEJB;
    
    public CamaraEJB(){
    
    }

    @Override
    public List<Camara> getCameras() {
        return CamaraEJB.findAll();
    }

    @Override
    public List<Camara> getCamerasRange(int[] i) {
        return CamaraEJB.findRange(i);
    }

    @Override
    public Camara getCamera(int IdCamara) {
        return CamaraEJB.find(IdCamara);
    }

    @Override
    public void addCamera(Camara camera) {
        this.CamaraEJB.create(camera);
    }

    @Override
    public void updateCamera(Camara camera) {
        this.CamaraEJB.edit(camera);
    }

    @Override
    public void removeCamera(Camara camera) {
        this.CamaraEJB.remove(camera);
    }

    @Override
    public List<Foto> getPhotosByCamera(Camara camera) {
        Collection<Foto> CollectionPhoto = camera.getFotoCollection();
        List<Foto> ListPhotos  = new ArrayList<Foto>();
        for(Foto f: CollectionPhoto){
            ListPhotos.add(f);
        }
        return ListPhotos;
    }

    @Override
    public List<Camara> getCamerasByUser(Usuario user) {
        Collection<Foto> CollectionPhoto =  user.getFotoCollection();
        List<Camara> allCameras = new ArrayList<>();
        for(Foto f: CollectionPhoto){
           allCameras.add(f.getIdCamara());
        }
        List<Camara> filterCameras = new ArrayList<>();
        for(int i = 0; i<allCameras.size(); i++){
            if(!filterCameras.contains(allCameras.get(i)) && allCameras.get(i) != null){
                filterCameras.add(allCameras.get(i));
            }
        }
        return filterCameras;
    }

    @Override
    public List<Usuario> getUsersByCamera(Camara camera) {
       Collection<Foto> CollectionPhoto =  camera.getFotoCollection();
       List<Integer> allUsers = new ArrayList<>();
       for(Foto f: CollectionPhoto){
           if(f.getIdUsuario().getIdUsuario()!= null && !allUsers.contains(f.getIdUsuario().getIdUsuario())){
              allUsers.add(f.getIdUsuario().getIdUsuario());
           }
       }
       List<Usuario> filterUsers = new ArrayList<>();
       for(Integer u: allUsers){
           if(!filterUsers.contains(UsuarioEJB.find(u)) && UsuarioEJB.find(u) != null){
              filterUsers.add(UsuarioEJB.find(u));
           }
       }  
       return filterUsers;
    }
}
