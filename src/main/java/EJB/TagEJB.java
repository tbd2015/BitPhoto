package EJB;

import EJB.local.TagEJBLocal;

import FACADE.TagEJBFacade;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import MODEL.Tag;

@Stateless
public class TagEJB implements TagEJBLocal{
    @EJB
    TagEJBFacade TagEJB;
    
    public TagEJB(){
    
    }
    
}
