package pos.estacio.projeto_final.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Income;
import pos.estacio.projeto_final.model.Payment;

@RequestScoped
@Named("income")
public class IncomeService implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> incomeDao;

	@Override
	public Income execute(int id, Payment payment) {
		Income income = incomeDao.find(id);

		payment.setValuePaid(payment.getValuePaid().abs());
		payment.setFinancialTransaction(income);

		income.getPayments().add(payment);

		return incomeDao.update(income);
	}

	@Override
	public Income create(Income income) {
		income.setValueTransaction(income.getValueTransaction().abs());
		return incomeDao.create(income);
	}

	@Override
	public List<Income> list() {
		return incomeDao.list();
	}

	@Override
	public Income find(int id) {
		return incomeDao.find(id);
	}

}