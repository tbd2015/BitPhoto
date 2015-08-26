package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.TagUsuario;

@Stateless
public class TagUsuarioEJBFacade extends AbstractFacade<TagUsuario> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public TagUsuarioEJBFacade() {
		super(TagUsuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
