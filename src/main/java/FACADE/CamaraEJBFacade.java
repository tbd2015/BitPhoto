package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Camara;

@Stateless
public class CamaraEJBFacade extends AbstractFacade<Camara>{
       
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public CamaraEJBFacade() {
		super(Camara.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
