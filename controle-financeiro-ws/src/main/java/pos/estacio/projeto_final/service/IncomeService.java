package pos.estacio.projeto_final.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
public class IncomeService extends BaseService implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> incomeDao;

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Inject
	private GenericDao<Funds> fundsDao;
	
	@Inject
	private MaturityUtils maturityUtils;

	@Override
	public Income pay(int id, Payment payment) throws Exception {
		Income income = incomeDao.find(id);

		payment.setMaturity(findMaturity(payment, income));
		payment.setValuePaid(payment.getValuePaid().abs());
		payment.setFinancialTransaction(income);

		income.addPayment(payment);

		return incomeDao.update(income);
	}

	private Maturity findMaturity(Payment payment, Income income) throws Exception {
		Maturity maturity;
		if (income.isFixedTransaction() && payment.getMaturity().getId() == null) {
			LocalDate date = payment.getDatePayment();
			date = date.withDayOfMonth(income.getFirstMaturity().getDayOfMonth());
			maturity = new Maturity(income.getValueTransaction(), date, income);
			maturityDao.create(maturity);
		} else {
			try {
				maturity = maturityDao.find(payment.getMaturity().getId());
			} catch (Exception e) {
				throw new Exception("Não foi possivel encontrar o vencimento");
			}
		}

		maturity.setPayment(payment);
		return maturity;
	}

	@Override
	public Income create(Income income) {
		income.setValueTransaction(income.getValueTransaction().abs());
		income.setMaturityList(maturityUtils.maturityListBuilder(income));
		income.setFunds(fundsDao.find(income.getFunds().getId()));
		return incomeDao.create(income);
	}

	@Override
	public List<Income> list() {
		return incomeDao.list(this.getUserSession().getUser());
	}

	@Override
	public Income find(int id) {
		return incomeDao.find(id);
	}

	@Override
	public Income update(int id, Income income) {
		Income incomeAux = incomeDao.find(id);
		incomeAux.setDescription(income.getDescription());
		incomeAux.setFunds(income.getFunds());
		incomeAux.setGroup(income.getGroup());
		incomeAux.setFixedTransaction(income.isFixedTransaction());
		incomeAux.setValueTransaction(income.getValueTransaction());
		incomeAux.setRecurrent(income.getRecurrent());
		Set<Maturity> maturityListBuilder = maturityUtils.maturityListBuilder(incomeAux);
		incomeAux.getMaturityList().removeAll(incomeAux.getMaturityList());
		incomeAux.getMaturityList().addAll(maturityListBuilder);
		
		return incomeDao.update(incomeAux);
	}

	@Override
	public void delete(int id) {
		incomeDao.delete(id);
	}

}