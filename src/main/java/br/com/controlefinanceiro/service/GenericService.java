package br.com.controlefinanceiro.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.session.UserSession;

public abstract class GenericService<T extends Serializable> {
	@Inject
	protected GenericDao<T> dao;
	@Inject
	protected UserSession userSession;
	
	public abstract T create(T t);

	public abstract T find(ObjectId id);

	public abstract List<T> list();

	public abstract T update(ObjectId id, T t);
}
