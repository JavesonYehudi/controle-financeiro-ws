package br.com.controlefinanceiro.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.mongodb.morphia.Datastore;

import br.com.controlefinanceiro.model.User;

public class GenericDao<T extends Serializable>{

	private Class<T> entityClass;

	@Inject
	protected Datastore datastore;

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T create(T entity) {
		datastore.save(entity);
		return entity;
	}

	public T find(Object id) {
		return datastore.get(entityClass, id);
	}
	
	public T update(T entity) {
		datastore.merge(entity);
		return entity;
	}

	public List<T> list(User user) {
		return datastore.createQuery(entityClass).asList();
	}
	
	public T findBy(Object...objects){
		return null;
	}
}