package EJB.local;

import java.util.List;
import javax.ejb.Local;

import MODEL.Tag;
import MODEL.Foto;
import MODEL.Usuario;

@Local
public interface TagEJBLocal {
    Tag createTag(Tag tag);
    List<Tag> getPhotoTags(int IdPhoto);
    void addTagPhoto(Tag t, Foto f);
    void addTagUsuario(Tag t, Usuario u);
    void deleteTagPhoto(Tag t, Foto f);
    Tag getTag(String nombre);
}
