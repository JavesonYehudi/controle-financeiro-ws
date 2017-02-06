package pos.estacio.projeto_final.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Income;
import pos.estacio.projeto_final.model.Maturity;
import pos.estacio.projeto_final.model.Payment;

@RequestScoped
@Named("income")
public class IncomeService implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> incomeDao;

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Override
	public Income execute(int id, Payment payment) {
		Income income = incomeDao.find(id);

		Maturity maturity = maturityDao.find(payment.getMaturity().getId());
		maturity.setDate(payment.getDatePayment());
		maturity.setValue(payment.getValuePaid().abs().negate());
		maturity.setPayment(payment);

		payment.setMaturity(maturity);
		payment.setValuePaid(payment.getValuePaid().abs().negate());
		payment.setFinancialTransaction(income);

		income.addPayment(payment);

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