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
public class ExpenseService implements IService<Expense> {

	@Inject
	private GenericDao<Expense> expenseDao;
	
	@Override
	public Expense execute(int id, BigDecimal valueExecuted) {
		Expense expense = expenseDao.find(id);
		expense.setValueTransaction(valueExecuted.abs());
		expense.setValueExecuted(valueExecuted.abs());
		return expenseDao.update(expense);
	}

	@Override
	public Expense create(Expense expense) {
		expense.setValueTransaction(expense.getValueTransaction().abs());
		return expenseDao.create(expense);
	}

	@Override
	public List<Expense> list() {
		return expenseDao.list();
	}

}
