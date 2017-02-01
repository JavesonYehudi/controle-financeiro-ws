package pos.estacio.projeto_final.service;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Expense;

@RequestScoped
@Named("expense")
public class ExpenseService implements IFinancialTransactionService<Expense> {

	@Inject
	private GenericDao<Expense> expenseDao;
	
	@Override
	public Expense execute(int id, BigDecimal valueExecuted) {
		Expense expense = expenseDao.find(id);
		expense.setValueTransaction(valueExecuted.abs().negate());
		expense.setValueExecuted(valueExecuted.abs().negate());
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

}
