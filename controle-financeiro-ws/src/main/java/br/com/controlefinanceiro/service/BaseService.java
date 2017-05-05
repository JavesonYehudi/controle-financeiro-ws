package br.com.controlefinanceiro.service;

import javax.inject.Inject;

import br.com.controlefinanceiro.session.UserSession;

public class BaseService {
	@Inject
	private UserSession userSession;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
