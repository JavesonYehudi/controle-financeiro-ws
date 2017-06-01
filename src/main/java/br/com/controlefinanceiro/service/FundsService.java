package br.com.controlefinanceiro.service;

import java.util.List;

import br.com.controlefinanceiro.model.Funds;

public class FundsService extends GenericService<Funds>{

	public Funds create(Funds funds) {
		funds.setUser(this.getUserSession().getUser());
		return dao.create(funds);
	}

	public List<Funds> list() {
		return dao.list(this.getUserSession().getUser());
	}

	public Funds find(int id) {
		return dao.find(id);
	}

	public Funds update(int id, Funds funds) {
		Funds fundsAux = dao.find(id);
		fundsAux.setDescription(funds.getDescription());
		return dao.update(fundsAux);
	}
}
