package EJB;

import EJB.local.TagEJBLocal;
import FACADE.FotoEJBFacade;

import FACADE.TagEJBFacade;
import FACADE.TagFotoEJBFacade;
import FACADE.TagUsuarioEJBFacade;
import MODEL.Foto;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import MODEL.Tag;
import MODEL.TagFoto;
import MODEL.TagUsuario;
import MODEL.Usuario;

@Stateless
public class TagEJB implements TagEJBLocal{
    @EJB
    TagEJBFacade TagEJB;
    @EJB
    TagFotoEJBFacade TagFotoEJB;
    @EJB
    TagUsuarioEJBFacade TagUsuarioEJB;
    @EJB
    FotoEJBFacade FotoEJB;
    
    public TagEJB(){
    
    }

    @Override
    public Tag createTag(Tag tag) {    
           List<Tag> listTag = TagEJB.findAll();
           for(Tag t : listTag){
              if(t.getNombreTag().equals(tag.getNombreTag())){
                return t;
              }
           }
           TagEJB.create(tag);
           return tag;
    }

    @Override
    public List<Tag> getPhotoTags(int IdPhoto) {
        Collection<TagFoto> collectionTagPhoto = FotoEJB.find(IdPhoto).getTagFotoCollection();
        List<Tag> tags = new ArrayList<>();
        for(TagFoto tf : collectionTagPhoto){
           tags.add(tf.getIdTag());
        }
        return tags;
    }

    @Override
    public void addTagPhoto(Tag t, Foto f) {
        TagFoto tagphoto = new TagFoto();
        tagphoto.setIdFoto(FotoEJB.find(f.getIdFoto()));
        tagphoto.setIdTag(createTag(t));
        TagFotoEJB.create(tagphoto);
    }

    @Override
    public void deleteTagPhoto(Tag t, Foto f) {
        TagFoto ts = null;
        for(TagFoto tf: TagFotoEJB.findAll()){
            if(tf.getIdFoto().getIdFoto().equals(f.getIdFoto()) && tf.getIdTag().getNombreTag().equals(t.getNombreTag())){
                ts = tf;
             break;
            }
        }
        TagFotoEJB.remove(ts);
    }

    @Override
    public Tag getTag(String nombre) {
        List<Tag> tags = TagEJB.findAll();
        for(Tag t: tags){
           if(t.getNombreTag().equals(nombre)){
           return t;
           }
        }
        return null;
    }

    @Override
    public void addTagUsuario(Tag t, Usuario u) {
        TagUsuario tu = new TagUsuario();
        tu.setIdTag(t);
        tu.setIdUsuario(u);
        TagUsuarioEJB.create(tu);
    }
    
}
