package pos.estacio.projeto_final.service;

import java.util.List;

import javax.inject.Inject;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.BankAccount;

public class BankAccountService {
	@Inject
	private GenericDao<BankAccount> bankAccountDao;

	public BankAccount create(BankAccount bankAccount) {
			return bankAccountDao.create(bankAccount);
	}

	public List<BankAccount> list() {
			return bankAccountDao.list();
	}

	public BankAccount find(int id) {
		return bankAccountDao.find(id);
	}

	public BankAccount update(int id, BankAccount bankAccount) {
		BankAccount bankAccountAux = bankAccountDao.find(id);
		bankAccountAux.setAccount(bankAccount.getAccount());
		bankAccountAux.setAgency(bankAccount.getAgency());
		bankAccountAux.setDescription(bankAccount.getDescription());
		return bankAccountDao.update(bankAccountAux);
	}

	public void delete(int id) {
		bankAccountDao.delete(id);
	}
}
