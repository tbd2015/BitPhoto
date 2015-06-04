package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.AlbumFoto;

@Stateless
public class AlbumFotoEJBFacade extends AbstractFacade<AlbumFoto>{
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public AlbumFotoEJBFacade() {
		super(AlbumFoto.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
