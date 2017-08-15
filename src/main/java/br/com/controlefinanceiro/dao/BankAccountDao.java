package br.com.controlefinanceiro.dao;

import java.util.List;

import br.com.controlefinanceiro.enumeration.EFundsType;
import br.com.controlefinanceiro.model.BankAccount;
import br.com.controlefinanceiro.model.User;

public class BankAccountDao extends GenericDao<BankAccount> {

	public BankAccountDao() {
		super(BankAccount.class);
	}

	public List<BankAccount> list(User user) {
		return getQuery().filter("fundsType", EFundsType.BANK_ACCOUNT.getId()).asList();
	}
}
