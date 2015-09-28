package FACADE;
/**
 *
 * @author elias
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import MODEL.ComentarioFoto;
import javax.ejb.*;

@Stateless
public class ComentarioFotoEJBFacade extends AbstractFacade<ComentarioFoto> {
        
        @PersistenceContext(unitName = "bitphoto")
	private EntityManager em;
	
	public ComentarioFotoEJBFacade() {
		super(ComentarioFoto.class);
	}
        
        @TransactionAttribute
        public int insertTrade(ComentarioFoto cf) throws Exception {
        em.persist(cf);
        return cf.getIdComentarioFoto();
        }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	} 
}
