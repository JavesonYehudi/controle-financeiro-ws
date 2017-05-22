package br.com.controlefinanceiro.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.UserDao;
import br.com.controlefinanceiro.enumeration.EExternalConnections;
import br.com.controlefinanceiro.model.ExternalConnection;
import br.com.controlefinanceiro.model.User;
import br.com.controlefinanceiro.utils.TokenUtils;

public class UserService extends BaseService {
	@Inject
	private UserDao userDao;

	public User create(User user) throws UnsupportedEncodingException {
		user = userDao.create(user);
		user.setToken(TokenUtils.generateToken(user));
		return user;
	}

	public User find(ObjectId id) {
		return userDao.find(id);
	}

	public User login(String externalId, EExternalConnections type, User user) throws UnsupportedEncodingException {
		ExternalConnection externalConnection = new ExternalConnection(externalId, type);
		User userAux = userDao.find(externalConnection);

		if (userAux == null)
			return this.create(user);

		userAux.setToken(TokenUtils.generateToken(userAux));
		return userAux;
	}

	public User find(User user) throws Exception {
		try {
			user = userDao.findBy(user.getLogin(), user.getPass());
			user.setToken(TokenUtils.generateToken(user));
			return user;
		} catch (NullPointerException e) {
			throw new Exception("login or pass incorrect");
		}
	}

	public User update(int id, User user) {
		User userAux = userDao.find(id);
		userAux.setLogin(user.getLogin());
		userAux.setPass(user.getPass());
		return userDao.update(userAux);
	}

}