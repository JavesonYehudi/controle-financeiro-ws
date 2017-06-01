package br.com.controlefinanceiro.service;

import java.util.List;

import br.com.controlefinanceiro.model.BankAccount;

public class BankAccountService extends GenericService<BankAccount> {

	public BankAccount create(BankAccount bankAccount) {
		bankAccount.setUser(this.getUserSession().getUser());
		return dao.create(bankAccount);
	}

	public List<BankAccount> list() {
		return dao.list(this.getUserSession().getUser());
	}

	public BankAccount find(int id) {
		return dao.find(id);
	}

	public BankAccount update(int id, BankAccount bankAccount) {
		BankAccount bankAccountAux = dao.find(id);
		bankAccountAux.setAccount(bankAccount.getAccount());
		bankAccountAux.setAgency(bankAccount.getAgency());
		bankAccountAux.setDescription(bankAccount.getDescription());
		return dao.update(bankAccountAux);
	}

}
