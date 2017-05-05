package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.User;

public class ExpenseDao extends GenericDao<Expense> {

	public ExpenseDao() {
		super(Expense.class);
	}

	public List<Expense> list(User user) {
		return datastore.createQuery(Expense.class).filter("user", user).asList();
	}
}
