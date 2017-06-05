package br.com.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Maturity;
import br.com.controlefinanceiro.model.Payment;

@RequestScoped
@Named("expense")
public class ExpenseService extends GenericService<Expense> implements IFinancialTransactionService<Expense> {

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Expense pay(ObjectId id, Payment payment) throws Exception {
		Expense expense = dao.find(id);

		payment.setMaturity(findMaturity(payment, expense));
		payment.setValuePaid(payment.getValuePaid().abs().negate());
		payment.setFinancialTransaction(expense);

		expense.addPayment(payment);

		return dao.update(expense);
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
		return dao.create(expense);
	}

	@Override
	public List<Expense> list() {
		return dao.list();
	}

	@Override
	public Expense find(ObjectId id) {
		return dao.find(id);
	}

	@Override
	public Expense update(ObjectId id, Expense expense) {
		Expense expenseAux = dao.find(id);
		expenseAux.setDescription(expense.getDescription());
		expenseAux.setFunds(expense.getFunds());
		expenseAux.setGroup(expense.getGroup());
		expenseAux.setFixedTransaction(expense.isFixedTransaction());
		expenseAux.setValueTransaction(expense.getValueTransaction());
		expenseAux.setRecurrent(expense.getRecurrent());
		
		return dao.update(expenseAux);
	}
}