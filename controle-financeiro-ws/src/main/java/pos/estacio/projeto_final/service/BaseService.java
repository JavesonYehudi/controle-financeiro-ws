package pos.estacio.projeto_final.service;

import javax.inject.Inject;

import pos.estacio.projeto_final.session.UserSession;

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
