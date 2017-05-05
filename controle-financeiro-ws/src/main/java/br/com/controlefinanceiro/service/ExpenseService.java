package br.com.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Maturity;
import br.com.controlefinanceiro.model.Payment;

@RequestScoped
@Named("expense")
public class ExpenseService extends BaseService implements IFinancialTransactionService<Expense> {

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
		if (payment.getMaturity().getId() == null) {
			LocalDate date = payment.getDatePayment();
			date = date.withDayOfMonth(expense.getFirstMaturity().getDayOfMonth());
			maturity = new Maturity(expense.getValueTransaction(), date, expense);
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
	public Expense create(Expense expense) {
		expense.setValueTransaction(expense.getValueTransaction().abs().negate());
		expense.setFunds(fundsDao.find(expense.getFunds().getId()));
		return expenseDao.create(expense);
	}

	@Override
	public List<Expense> list() {
		return expenseDao.list(this.getUserSession().getUser());
	}

	@Override
	public Expense find(int id) {
		return expenseDao.find(id);
	}

	@Override
	public Expense update(int id, Expense expense) {
		Expense expenseAux = expenseDao.find(id);
		expenseAux.setDescription(expense.getDescription());
		expenseAux.setFunds(expense.getFunds());
		expenseAux.setGroup(expense.getGroup());
		expenseAux.setFixedTransaction(expense.isFixedTransaction());
		expenseAux.setValueTransaction(expense.getValueTransaction());
		expenseAux.setRecurrent(expense.getRecurrent());
		
		return expenseDao.update(expenseAux);
	}
}