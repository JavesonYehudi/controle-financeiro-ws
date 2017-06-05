package br.com.controlefinanceiro.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Funds;

public class FundsService extends GenericService<Funds>{

	public Funds create(Funds funds) {
		return dao.create(funds);
	}

	public List<Funds> list() {
		return dao.list();
	}

	public Funds find(ObjectId id) {
		return dao.find(id);
	}

	public Funds update(ObjectId id, Funds funds) {
		Funds fundsAux = dao.find(id);
		fundsAux.setDescription(funds.getDescription());
		return dao.update(fundsAux);
	}
}
