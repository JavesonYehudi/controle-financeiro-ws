package br.com.controlefinanceiro.service;

import java.util.List;

import br.com.controlefinanceiro.model.CreditCard;

public class CreditCardService extends GenericService<CreditCard> {
	public CreditCard create(CreditCard creditCard) {
		creditCard.setUser(this.getUserSession().getUser());
		return dao.create(creditCard);
	}

	public List<CreditCard> list() {
		return dao.list(this.getUserSession().getUser());
	}

	public CreditCard find(int id) {
		return dao.find(id);
	}

	public CreditCard update(int id, CreditCard creditCard) {
		CreditCard creditCardAux = dao.find(id);
		creditCardAux.setLimitValue(creditCard.getLimitValue());
		creditCardAux.setMaturity(creditCard.getMaturity());
		creditCardAux.setDescription(creditCard.getDescription());
		return dao.update(creditCardAux);
	}
}
