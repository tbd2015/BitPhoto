package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.UsuarioUsuario;

@Stateless
public class UsuarioUsuarioEJBFacade extends AbstractFacade<UsuarioUsuario> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public UsuarioUsuarioEJBFacade() {
		super(UsuarioUsuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}

