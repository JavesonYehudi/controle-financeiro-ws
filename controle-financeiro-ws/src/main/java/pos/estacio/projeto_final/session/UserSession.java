package pos.estacio.projeto_final.session;

import javax.enterprise.context.ApplicationScoped;

import pos.estacio.projeto_final.model.User;

@ApplicationScoped
public class UserSession {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
