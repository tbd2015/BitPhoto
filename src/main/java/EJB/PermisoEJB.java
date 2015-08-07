package EJB;

import EJB.local.PermisoEJBLocal;

import FACADE.PermisoEJBFacade;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import MODEL.Permiso;

@Stateless
public class PermisoEJB implements PermisoEJBLocal{
    @EJB
    PermisoEJBFacade permisoEJB;
    
    public PermisoEJB(){
    
    }
    
}
