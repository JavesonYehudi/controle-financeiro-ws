package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.model.User;

public class IncomeDao extends GenericDao<Income> {

	public IncomeDao() {
		super(Income.class);
	}

	public List<Income> list(User user) {
		return datastore.createQuery(Income.class).filter("user", user).asList();
	}
}
