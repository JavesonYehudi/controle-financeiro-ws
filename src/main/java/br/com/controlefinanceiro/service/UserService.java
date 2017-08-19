package br.com.controlefinanceiro.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.FundsDao;
import br.com.controlefinanceiro.dao.UserDao;
import br.com.controlefinanceiro.enumeration.EExternalConnections;
import br.com.controlefinanceiro.model.ExternalConnection;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.User;
import br.com.controlefinanceiro.utils.TokenUtils;

public class UserService extends GenericService<User> {
	@Inject
	private UserDao dao;
	@Inject
	private FundsDao fundsDao;
	
	private final static String chaveSimetrica = "SimetricKeyHintCash@#ForPassword";

	public User create(User user) throws Exception{
		SecretKey key = new SecretKeySpec(chaveSimetrica.getBytes(), "AES");
		
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			try {
				user.setPassword(new String(cipher.doFinal(user.getPassword().getBytes())));
			}catch (NullPointerException e) {}
		}catch (Exception e) {
			throw e;
		}
		user = dao.create(user);
		fundsDao.create(new Funds(user));
		user.setToken(TokenUtils.generateToken(user));
		return user;
	}

	public User find(ObjectId id) {
		return dao.find(id);
	}

	public User login(String externalId, EExternalConnections type, User user) throws UnsupportedEncodingException, Exception {
		User userAux = dao.find(user.getConnections().get(0));

		if (userAux == null)
			return this.create(user);

		userAux.setToken(TokenUtils.generateToken(userAux));
		return userAux;
	}

	public User find(User user) throws Exception {
		try {
			user = dao.findBy(user.getLogin(), user.getPassword());
			user.setToken(TokenUtils.generateToken(user));
			return user;
		} catch (NullPointerException e) {
			throw new Exception("login or pass incorrect");
		}
	}

	public User update(ObjectId id, User user) {
		User userAux = dao.find(id);
		userAux.setLogin(user.getLogin());
		userAux.setPassword(user.getPassword());
		return dao.update(userAux);
	}

	@Override
	public List<User> list() {
		return dao.list();
	}

}