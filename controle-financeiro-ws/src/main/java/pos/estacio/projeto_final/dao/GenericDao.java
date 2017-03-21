package pos.estacio.projeto_final.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pos.estacio.projeto_final.model.User;

public class GenericDao<T extends Serializable>{

	private Class<T> entityClass;

	@Inject
	protected EntityManager entityManager;

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public long count() {
		String entity = entityClass.getSimpleName();
		final StringBuffer queryString = new StringBuffer("select count(ent) from " + entity + " ent");
		final Query query = this.entityManager.createQuery(queryString.toString());
		return (Long) query.getSingleResult();
	}

	public T create(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		closeEntityManager();
		return entity;
	}

	public void delete(Object id) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.getReference(entityClass, id));
		closeEntityManager();
	}

	public T find(Object id) {
		return entityManager.find(entityClass, id);
	}
	
	public T update(T entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		closeEntityManager();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(User user) {
		Query query = entityManager.createQuery("from " + entityClass.getName() + " where user = :user").setParameter("user", user);
		List<T> resultList = query.getResultList();
		return resultList;
	}

	protected void closeEntityManager() {
		entityManager.getTransaction().commit();
	}
	
	public T findBy(Object...objects){
		return null;
	}
}