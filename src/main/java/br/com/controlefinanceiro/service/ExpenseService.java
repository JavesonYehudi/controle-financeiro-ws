package br.com.controlefinanceiro.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Maturity;

@RequestScoped
@Named("expense")
public class ExpenseService extends GenericService<Expense> implements IFinancialTransactionService<Expense> {

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Expense pay(ObjectId id, Maturity payment) throws Exception {
		Expense expense = dao.find(id);
		payment.setFinancialTransaction(expense);
		payment = findMaturity(payment);
		expense.getMaturityList().add(payment);
		return dao.update(expense);
	}

	private Maturity findMaturity(Maturity payment) throws Exception {
		if(payment.getId() != null) {
			Maturity maturity = maturityDao.find(payment.getId());
			maturity.setValuePaid(payment.getValuePaid().abs().negate());
			maturity.setDatePayment(payment.getDatePayment());
			return maturityDao.update(maturity);
		}else {
			payment.setValue(payment.getValuePaid().abs().negate());
			payment.setValuePaid(payment.getValuePaid().abs().negate());
			payment.setDate(payment.getDatePayment());
			return maturityDao.create(payment);
		}
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