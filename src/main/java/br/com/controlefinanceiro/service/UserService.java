package br.com.controlefinanceiro.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.User;
import br.com.controlefinanceiro.utils.TokenUtils;

public class UserService extends BaseService{
	@Inject
	private GenericDao<User> userDao;

	public User create(User user) throws UnsupportedEncodingException {
		userDao.create(user);
		user.setToken(TokenUtils.generateToken(user));
		return user;
	}

	public User find(int id) {
		return userDao.find(id);
	}

	public User find(User user) throws Exception {
		User userAux = userDao.findBy(user.getLogin());
		if(!userAux.getPass().equals(user.getPass()))
			throw new Exception("login or pass incorrect");
		return userAux;
	}

	public User update(int id, User user) {
		User userAux = userDao.find(id);
		userAux.setLogin(user.getLogin());
		userAux.setPass(user.getPass());
		return userDao.update(userAux);
	}

}