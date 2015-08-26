package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Tag;

@Stateless
public class TagEJBFacade extends AbstractFacade<Tag> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public TagEJBFacade() {
		super(Tag.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
