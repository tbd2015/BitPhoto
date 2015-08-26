package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.TagFoto;

@Stateless
public class TagFotoEJBFacade extends AbstractFacade<TagFoto> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public TagFotoEJBFacade() {
		super(TagFoto.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
