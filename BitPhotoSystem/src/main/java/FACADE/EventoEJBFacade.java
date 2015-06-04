package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.Evento;

@Stateless
public class EventoEJBFacade extends AbstractFacade<Evento> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public EventoEJBFacade() {
		super(Evento.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
