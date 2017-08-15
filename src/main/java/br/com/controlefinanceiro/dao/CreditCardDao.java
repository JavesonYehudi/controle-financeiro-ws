package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.enumeration.EFundsType;
import br.com.controlefinanceiro.model.CreditCard;

public class CreditCardDao extends GenericDao<CreditCard> {

	public CreditCardDao() {
		super(CreditCard.class);
	}

	@Override
	public List<CreditCard> list() {
		return getQuery().filter("fundsType", EFundsType.CREDIT_CARD.getId()).asList();
	}
}