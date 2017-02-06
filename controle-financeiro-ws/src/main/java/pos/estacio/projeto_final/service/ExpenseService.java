package pos.estacio.projeto_final.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Expense;
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

	@Override
	public Expense execute(int id, Payment payment) {
		Expense expense = expenseDao.find(id);

		Maturity maturity = maturityDao.find(payment.getMaturity().getId());
		maturity.setDate(payment.getDatePayment());
		maturity.setValue(payment.getValuePaid().abs().negate());
		maturity.setPayment(payment);

		payment.setMaturity(maturity);
		payment.setValuePaid(payment.getValuePaid().abs().negate());
		payment.setFinancialTransaction(expense);

		expense.addPayment(payment);

		return expenseDao.update(expense);
	}

	@Override
	public Expense create(Expense expense) {
		expense.setValueTransaction(expense.getValueTransaction().abs().negate());
		expense.setMaturityList(MaturityUtils.maturityListBuilder(expense));
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
