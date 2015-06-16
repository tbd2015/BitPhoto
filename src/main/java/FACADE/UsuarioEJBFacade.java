package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Usuario;

@Stateless
public class UsuarioEJBFacade extends AbstractFacade<Usuario> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public UsuarioEJBFacade() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
        
        
}
