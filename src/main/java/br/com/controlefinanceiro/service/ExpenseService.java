package br.com.controlefinanceiro.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Payment;

@RequestScoped
@Named("expense")
public class ExpenseService extends GenericService<Expense> implements IFinancialTransactionService<Expense> {

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Expense pay(ObjectId id, Payment payment) {
		Expense expense = dao.find(id);
		expense.addPayment(payment);
		return dao.update(expense);
	}

	@Override
	public Expense create(Expense expense) {
		expense.setValueTransaction(expense.getValueTransaction().abs().negate());
		expense.setFunds(fundsDao.find(expense.getFunds().getId()));
		expense.setLastMaturity(expense.getFirstMaturity().plus(expense.getRecurrent(), expense.getCalendarPeriod().getChronoUnit()));
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