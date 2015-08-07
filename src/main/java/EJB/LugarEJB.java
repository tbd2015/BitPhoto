package EJB;

import EJB.local.LugarEJBLocal;

import FACADE.LugarEJBFacade;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import MODEL.Lugar;

@Stateless
public class LugarEJB implements LugarEJBLocal{
    @EJB
    LugarEJBFacade lugarEJB;
    
    public LugarEJB(){
    
    }
    
}
