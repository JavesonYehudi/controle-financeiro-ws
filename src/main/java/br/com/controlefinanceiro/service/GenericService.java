package br.com.controlefinanceiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.session.UserSession;

public abstract class GenericService<T extends Serializable> {
	@Inject
	protected UserSession userSession;
	@Inject
	protected GenericDao<T> dao;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
