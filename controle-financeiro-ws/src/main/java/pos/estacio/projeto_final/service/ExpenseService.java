package pos.estacio.projeto_final.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Expense;
import pos.estacio.projeto_final.model.Payment;

@RequestScoped
@Named("expense")
public class ExpenseService implements IFinancialTransactionService<Expense> {

	@Inject
	private GenericDao<Expense> expenseDao;

	@Override
	public Expense execute(int id, Payment payment) {
		Expense expense = expenseDao.find(id);

		payment.setValuePaid(payment.getValuePaid().abs().negate());
		payment.setFinancialTransaction(expense);

		expense.getPayments().add(payment);

		return expenseDao.update(expense);
	}

	@Override
	public Expense create(Expense expense) {
		expense.setValueTransaction(expense.getValueTransaction().abs().negate());
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

}
