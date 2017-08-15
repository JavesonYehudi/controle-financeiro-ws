package br.com.controlefinanceiro.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.BankAccount;

public class BankAccountService extends GenericService<BankAccount> {

	public BankAccount create(BankAccount bankAccount) {
		bankAccount.setUser(userSession.getUser());
		return dao.create(bankAccount);
	}

	public List<BankAccount> list() {
		return dao.list();
	}

	public BankAccount find(ObjectId id) {
		return dao.find(id);
	}

	public BankAccount update(ObjectId id, BankAccount bankAccount) {
		BankAccount bankAccountAux = dao.find(id);
		bankAccountAux.setAccount(bankAccount.getAccount());
		bankAccountAux.setAgency(bankAccount.getAgency());
		bankAccountAux.setDescription(bankAccount.getDescription());
		return dao.update(bankAccountAux);
	}

}
