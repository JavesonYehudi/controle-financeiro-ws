package br.com.controlefinanceiro.session;

import javax.enterprise.context.RequestScoped;

import br.com.controlefinanceiro.model.User;

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
