package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.FavoritosFoto;

@Stateless
public class FavoritosFotoEJBFacade extends AbstractFacade<FavoritosFoto> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public FavoritosFotoEJBFacade() {
		super(FavoritosFoto.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
