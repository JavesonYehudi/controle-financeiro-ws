package pos.estacio.projeto_final.session;

import javax.enterprise.context.RequestScoped;

import pos.estacio.projeto_final.model.User;

@RequestScoped
public class UserSession {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
