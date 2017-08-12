package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;
import br.com.controlefinanceiro.model.Income;

public class IncomeDao extends GenericDao<Income> {

	public IncomeDao() {
		super(Income.class);
	}

	@Override
	public String getFieldUser() {
		return "funds.user.login";
	}

	@Override
	public List<Income> list() {
		return getQuery().filter("financialTransactionType", EFinancialTransactionType.INCOME.ordinal()).asList();
	}
}
