package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.TagFotoPK;

public class TagFotoPKEJBFacade extends AbstractFacade<TagFotoPK> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public TagFotoPKEJBFacade() {
		super(TagFotoPK.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
