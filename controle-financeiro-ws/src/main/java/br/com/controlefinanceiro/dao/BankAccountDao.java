package br.com.controlefinanceiro.dao;

import br.com.controlefinanceiro.model.BankAccount;

public class BankAccountDao extends GenericDao<BankAccount> {

	public BankAccountDao() {
		super(BankAccount.class);
	}

}
