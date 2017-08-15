package br.com.controlefinanceiro.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.UserDao;
import br.com.controlefinanceiro.enumeration.EExternalConnections;
import br.com.controlefinanceiro.model.ExternalConnection;
import br.com.controlefinanceiro.model.User;
import br.com.controlefinanceiro.utils.TokenUtils;

public class UserService extends GenericService<User> {
	@Inject
	private UserDao dao;

	public User create(User user) {
		user = dao.create(user);
		user.setToken(TokenUtils.generateToken(user));
		return user;
	}

	public User find(ObjectId id) {
		return dao.find(id);
	}

	public User login(String externalId, EExternalConnections type, User user) throws UnsupportedEncodingException {
		ExternalConnection externalConnection = new ExternalConnection(externalId, type);
		user.addConnection(externalConnection);
		User userAux = dao.find(externalConnection);

		if (userAux == null)
			return this.create(user);

		userAux.setToken(TokenUtils.generateToken(userAux));
		return userAux;
	}

	public User find(User user) throws Exception {
		try {
			user = dao.findBy(user.getLogin(), user.getPass());
			user.setToken(TokenUtils.generateToken(user));
			return user;
		} catch (NullPointerException e) {
			throw new Exception("login or pass incorrect");
		}
	}

	public User update(ObjectId id, User user) {
		User userAux = dao.find(id);
		userAux.setLogin(user.getLogin());
		userAux.setPass(user.getPass());
		return dao.update(userAux);
	}

	@Override
	public List<User> list() {
		return dao.list();
	}

}