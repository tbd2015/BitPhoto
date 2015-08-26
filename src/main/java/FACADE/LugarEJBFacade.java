package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Lugar;

@Stateless
public class LugarEJBFacade extends AbstractFacade<Lugar> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public LugarEJBFacade() {
		super(Lugar.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
