package EJB;

import EJB.local.TagEJBLocal;
import FACADE.FotoEJBFacade;

import FACADE.TagEJBFacade;
import FACADE.TagFotoEJBFacade;
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

@Stateless
public class TagEJB implements TagEJBLocal{
    @EJB
    TagEJBFacade TagEJB;
    @EJB
    TagFotoEJBFacade TagFotoEJB;
    @EJB
    FotoEJBFacade FotoEJB;
    
    public TagEJB(){
    
    }

    @Override
    public boolean createTag(Tag tag) {    
           List<Tag> listTag = TagEJB.findAll();
           for(Tag t : listTag){
              if(t.getNombreTag().equals(tag.getNombreTag())){
                return true;
              }
           }
           TagEJB.create(tag);
           return true;
    }

    @Override
    public List<Tag> getPhotoTags(int IdPhoto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTagPhoto(Tag t, Foto f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTagPhoto(Tag t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
