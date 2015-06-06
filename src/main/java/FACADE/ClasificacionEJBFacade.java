package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Clasificacion;

@Stateless
public class ClasificacionEJBFacade extends AbstractFacade<Clasificacion>{
       
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public ClasificacionEJBFacade() {
		super(Clasificacion.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
