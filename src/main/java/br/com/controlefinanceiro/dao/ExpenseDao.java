package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.User;

public class ExpenseDao extends GenericDao<Expense> {

	public ExpenseDao() {
		super(Expense.class);
	}

	@Override
	public List<Expense> list(User user) {
		return datastore.find(Expense.class).field("funds.user").equal(user).asList();
	}
}
