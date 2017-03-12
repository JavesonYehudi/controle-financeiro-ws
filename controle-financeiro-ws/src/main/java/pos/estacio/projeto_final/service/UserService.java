package pos.estacio.projeto_final.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.User;
import pos.estacio.projeto_final.utils.TokenUtils;

public class UserService extends BaseService{
	@Inject
	private GenericDao<User> userDao;

	public User create(User user) throws UnsupportedEncodingException {
		userDao.create(user);
		user.setToken(TokenUtils.generateToken(user.getLogin(), user.getPass()));
		return user;
	}

	public User find(int id) {
		return userDao.find(id);
	}

	public User find(User user) throws Exception {
		User userAux = userDao.findBy(user.getLogin());
		if(!userAux.getPass().equals(user.getPass()))
			throw new Exception("login or pass incorrect");
		return user;
	}

	public User update(int id, User user) {
		User userAux = userDao.find(id);
		userAux.setLogin(user.getLogin());
		userAux.setPass(user.getPass());
		return userDao.update(userAux);
	}

}