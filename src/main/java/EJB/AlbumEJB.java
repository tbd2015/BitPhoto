package EJB;

import EJB.local.AlbumEJBLocal;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import FACADE.AlbumEJBFacade;
import FACADE.AlbumFotoEJBFacade;

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
	
    public AlbumEJB() {
       
    }
    
    @Override
    public List<Album> getAlbums() {
        
        return this.AlbumFacade.findAll();
    }
	
    @Override
    public List<Album> getAlbumsRange(int[] i) {
        
        return this.AlbumFacade.findRange(i);   
    }
    
    @Override
    public Album getAlbum(int IdAlbum) {
        
        return AlbumFacade.find((Object)IdAlbum);
    }

    @Override
    public void addAlbum(Album album) {

        this.AlbumFacade.create(album);
    }

    @Override
    public void updateAlbum(Album album) {

        this.AlbumFacade.edit(album);
    }
    
    @Override
    public void removeAlbum(Album album) {
        
        this.AlbumFacade.remove(album);
    }

    @Override
    public Integer countAlbums() {
        
        return this.AlbumFacade.findAll().size();
    }
    
    @Override
    public List<Album> getAlbumsByUser(Usuario user) {
         if(user!=null){
            List<Album> listAlbum = new ArrayList(user.getAlbumCollection());
            return listAlbum;
         }else{
            return null; 
         } 
    }

    @Override
    public Foto getPhotoByIdAlbum(int IdAlbum) {
           Album album = AlbumFacade.find(IdAlbum);
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
    public List<Foto> getPhotosByIdAlbum(int IdAlbum) {
           Album album =  this.AlbumFacade.find(IdAlbum);   
           Collection<AlbumFoto> af = album.getAlbumFotoCollection();
           List<Foto> fotos = new ArrayList<Foto>();
           for(AlbumFoto a: af){
               fotos.add(a.getFoto());
           }
           return fotos;
    }

    @Override
    public void addAlbumPhoto(Album album, Foto photo) {
        AlbumFoto af = new AlbumFoto();
        af.setAlbum(album);
        af.setFoto(photo);
        boolean estado = false;
        for(AlbumFoto aphoto : AlbumFotoFacade.findAll()){
            if(aphoto.getAlbum().equals(album) && aphoto.getFoto().equals(photo)){
              estado = true;
              af = aphoto;
              break;
            }
        }
        if(!estado){
            AlbumFotoFacade.create(af);
        }else{
            AlbumFotoFacade.edit(af);
        }
    }
    
}
