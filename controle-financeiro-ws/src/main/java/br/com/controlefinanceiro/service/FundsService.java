package br.com.controlefinanceiro.service;

import java.util.List;

import javax.inject.Inject;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Funds;

public class FundsService extends BaseService{
	@Inject
	private GenericDao<Funds> fundsDao;

	public Funds create(Funds funds) {
		funds.setUser(this.getUserSession().getUser());
		return fundsDao.create(funds);
	}

	public List<Funds> list() {
		return fundsDao.list(this.getUserSession().getUser());
	}

	public Funds find(int id) {
		return fundsDao.find(id);
	}

	public Funds update(int id, Funds funds) {
		Funds fundsAux = fundsDao.find(id);
		fundsAux.setDescription(funds.getDescription());
		return fundsDao.update(fundsAux);
	}
}
