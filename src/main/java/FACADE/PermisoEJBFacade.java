package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Permiso;

@Stateless
public class PermisoEJBFacade extends AbstractFacade<Permiso> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public PermisoEJBFacade() {
		super(Permiso.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
