package br.com.controlefinanceiro.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import br.com.controlefinanceiro.model.User;
import br.com.controlefinanceiro.session.UserSession;

public class GenericDao<T extends Serializable>{

	private Class<T> entityClass;

	@Inject
	private UserSession userSession;
	@Inject
	protected Datastore datastore;

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T create(T entity) {
		datastore.save(entity);
		return entity;
	}

	public T find(ObjectId id) {
		return datastore.get(entityClass, id);
	}
	
	public T update(T entity) {
		datastore.merge(entity);
		return entity;
	}

	public List<T> list() {
		return getQuery().asList();
	}
	
	public T findBy(Object...objects){
		return null;
	}

	public User getUser(){
		return userSession.getUser();
	}
	
	public String getFieldUser(){
		return "user.login";
	}

	public Query<T> getQuery() {
		return datastore.find(entityClass).filter(getFieldUser(), getUser().getLogin());
	}
}