package EJB.local;

import java.util.List;
import javax.ejb.Local;

import MODEL.Album;
import MODEL.Foto;
import MODEL.Usuario;

@Local
public interface AlbumEJBLocal {
        
        List<Album> getAlbums(); // Get all system albums
        List<Album> getAlbumsRange(int[] i); // Get a range of system albums
        Album getAlbum(int IdAlbum); // Get an album by IdAlbum
        void addAlbum(Album album); // Add an album in the system
	void updateAlbum(Album album); // Update an album in the system
        void removeAlbum(Album album); // Remove an album in the system
        Integer countAlbums(); // count all the albums of the system
        List<Album> getAlbumsByUser(Usuario user); // Get all albums from user email
        Foto getPhotoByIdAlbum(int IdAlbum); // Get the album cover
        List<Foto> getPhotosByIdAlbum(int IdAlbum); // Get all the photos of an album
        void addAlbumPhoto(Album album, Foto photo); // Add relation albumfoto to the system
 
}
