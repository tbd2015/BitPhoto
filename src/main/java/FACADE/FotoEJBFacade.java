package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Foto;
import javax.ejb.Singleton;

@Stateless
public class FotoEJBFacade extends AbstractFacade<Foto> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public FotoEJBFacade() {
		super(Foto.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
