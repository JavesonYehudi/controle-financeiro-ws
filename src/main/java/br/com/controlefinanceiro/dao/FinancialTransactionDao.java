package br.com.controlefinanceiro.dao;

import br.com.controlefinanceiro.model.FinancialTransaction;

public class FinancialTransactionDao extends GenericDao<FinancialTransaction> {

	public FinancialTransactionDao() {
		super(FinancialTransaction.class);
	}

	@Override
	public String getFieldUser() {
		return "funds.user.login";
	}
}
