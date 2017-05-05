package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.User;

public class FinancialTransactionDao extends GenericDao<FinancialTransaction> {

	public FinancialTransactionDao() {
		super(FinancialTransaction.class);
	}

	public List<FinancialTransaction> list(User user) {
		return datastore.createQuery(FinancialTransaction.class).filter("user", user).asList();
	}
}
