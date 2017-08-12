package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;
import br.com.controlefinanceiro.model.Expense;

public class ExpenseDao extends GenericDao<Expense> {

	public ExpenseDao() {
		super(Expense.class);
	}

	@Override
	public String getFieldUser() {
		return "funds.user.login";
	}
	
	@Override
	public List<Expense> list() {
		return getQuery().filter("financialTransactionType", EFinancialTransactionType.EXPENSE.ordinal()).asList();
	}

}
