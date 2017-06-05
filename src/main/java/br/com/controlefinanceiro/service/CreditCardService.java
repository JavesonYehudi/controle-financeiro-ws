package br.com.controlefinanceiro.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.CreditCard;

public class CreditCardService extends GenericService<CreditCard> {
	public CreditCard create(CreditCard creditCard) {
		return dao.create(creditCard);
	}

	public List<CreditCard> list() {
		return dao.list();
	}

	public CreditCard find(ObjectId id) {
		return dao.find(id);
	}

	public CreditCard update(ObjectId id, CreditCard creditCard) {
		CreditCard creditCardAux = dao.find(id);
		creditCardAux.setLimitValue(creditCard.getLimitValue());
		creditCardAux.setMaturity(creditCard.getMaturity());
		creditCardAux.setDescription(creditCard.getDescription());
		return dao.update(creditCardAux);
	}
}
