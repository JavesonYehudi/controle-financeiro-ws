package pos.estacio.projeto_final.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Expense;
import pos.estacio.projeto_final.model.Funds;
import pos.estacio.projeto_final.model.Maturity;
import pos.estacio.projeto_final.model.Payment;
import pos.estacio.projeto_final.utils.MaturityUtils;

@RequestScoped
@Named("expense")
public class ExpenseService implements IFinancialTransactionService<Expense> {

	@Inject
	private GenericDao<Expense> expenseDao;

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Expense pay(int id, Payment payment) throws Exception {
		Expense expense = expenseDao.find(id);

		payment.setMaturity(findMaturity(payment, expense));
		payment.setValuePaid(payment.getValuePaid().abs().negate());
		payment.setFinancialTransaction(expense);

		expense.addPayment(payment);

		return expenseDao.update(expense);
	}

	private Maturity findMaturity(Payment payment, Expense expense) throws Exception {
		Maturity maturity;
		if (expense.isFixedTransaction() && payment.getMaturity().getId() == null) {
			LocalDate date = payment.getDatePayment();
			date.withDayOfMonth(expense.getFirstMaturity().getDayOfMonth());
			maturity = new Maturity(expense.getValueTransaction(), date, expense);
		} else {
			try {
				maturity = maturityDao.find(payment.getMaturity().getId());
			} catch (Exception e) {
				throw new Exception();
			}
		}

		maturity.setPayment(payment);
		return maturity;
	}

	@Override
	public Expense create(Expense expense) {
		expense.setValueTransaction(expense.getValueTransaction().abs().negate());
		expense.setMaturityList(MaturityUtils.maturityListBuilder(expense));
		expense.setFunds(fundsDao.find(expense.getFunds().getId()));
		return expenseDao.create(expense);
	}

	@Override
	public List<Expense> list() {
		return expenseDao.list();
	}

	@Override
	public Expense find(int id) {
		return expenseDao.find(id);
	}

	@Override
	public Expense update(int id, Expense expense) {
		expense.setId(id);
		return expenseDao.update(expense);
	}

	@Override
	public void delete(int id) {
		expenseDao.delete(id);
	}

}