package FACADE;
/**
 *
 * @author elias
 */

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.AlbumFotoPK;

@Stateless
public class AlbumFotoPKEJBFacade extends AbstractFacade<AlbumFotoPK>{
       
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public AlbumFotoPKEJBFacade() {
		super(AlbumFotoPK.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
