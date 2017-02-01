package pos.estacio.projeto_final.service;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Income;

@RequestScoped
@Named("income")
public class IncomeService implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> incomeDao;

	@Override
	public Income execute(int id, BigDecimal valueExecuted) {
		Income income = incomeDao.find(id);
		income.setValueTransaction(valueExecuted.abs());
		income.setValueExecuted(valueExecuted.abs());
		return incomeDao.update(income);
	}

	@Override
	public Income create(Income income) {
		income.setValueTransaction(income.getValueTransaction().abs());
		return incomeDao.create(income);
	}

	@Override
	public List<Income> list() {
		return incomeDao.list();
	}

}