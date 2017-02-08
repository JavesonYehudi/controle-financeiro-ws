package pos.estacio.projeto_final.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Funds;
import pos.estacio.projeto_final.model.Income;
import pos.estacio.projeto_final.model.Maturity;
import pos.estacio.projeto_final.model.Payment;
import pos.estacio.projeto_final.utils.MaturityUtils;

@RequestScoped
@Named("income")
public class IncomeService implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> incomeDao;

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Income pay(int id, Payment payment) {
		Income income = incomeDao.find(id);

		payment.setMaturity(findMaturity(payment, income));
		payment.setValuePaid(payment.getValuePaid().abs());
		payment.setFinancialTransaction(income);

		income.addPayment(payment);

		return incomeDao.update(income);
	}

	private Maturity findMaturity(Payment payment, Income income) {
		Maturity maturity;

		if (income.isFixedTransaction()) {
			LocalDate date = payment.getDatePayment();
			date.withDayOfMonth(income.getFirstMaturity().getDayOfMonth());
			maturity = new Maturity(income.getValueTransaction(), date, income);
		} else {
			maturity = maturityDao.find(payment.getMaturity().getId());
		}

		maturity.setPayment(payment);
		return maturity;
	}

	@Override
	public Income create(Income income) {
		income.setValueTransaction(income.getValueTransaction().abs());
		income.setMaturityList(MaturityUtils.maturityListBuilder(income));
		income.setFunds(fundsDao.find(income.getFunds().getId()));
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

	@Override
	public Income update(int id, Income income) {
		income.setId(id);
		return incomeDao.update(income);
	}

	@Override
	public void delete(int id) {
		incomeDao.delete(id);
	}

}