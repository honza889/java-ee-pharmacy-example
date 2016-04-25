package fit.pis.domain.mediator;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fit.pis.domain.entity.Insurance;

@Stateless
public class InsuranceDao {

	@PersistenceContext
    private EntityManager em;
	
	public void save(Insurance insurance) {
		em.merge(insurance);
	}
	
	public void remove(Insurance insurance) {
		em.remove(em.merge(insurance));
	}
	
	public List<Insurance> findAll() {
		TypedQuery<Insurance> query = em.createQuery("SELECT i FROM Insurance i", Insurance.class);
		return query.getResultList();
	}
	
	public Insurance getById(long id) {
		try {
			TypedQuery<Insurance> query = em.createQuery("SELECT i FROM Insurance i WHERE id = :id", Insurance.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}
