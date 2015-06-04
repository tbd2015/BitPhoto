package FACADE;
/**
 *
 * @author elias
 */

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Album;

@Stateless
public class AlbumEJBFacade extends AbstractFacade<Album> {
      
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public AlbumEJBFacade() {
		super(Album.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
