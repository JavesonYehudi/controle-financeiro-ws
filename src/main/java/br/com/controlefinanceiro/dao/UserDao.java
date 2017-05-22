package br.com.controlefinanceiro.dao;

import br.com.controlefinanceiro.model.ExternalConnection;
import br.com.controlefinanceiro.model.User;

public class UserDao extends GenericDao<User> {

	public UserDao() {
		super(User.class);
	}

	@Override
	public User findBy(Object... objects) {
		return this.datastore.createQuery(User.class).filter("login", objects[0]).filter("pass", objects[1]).get();
	}
	
	public User find(ExternalConnection connection){
		return this.datastore.createQuery(User.class).field("connections").equal(connection).get();
	}
}
