package br.com.controlefinanceiro.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.model.Payment;

@RequestScoped
@Named("income")
public class IncomeService extends GenericService<Income> implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> dao;

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Income pay(ObjectId id, Payment payment) throws Exception {
		Income income = dao.find(id);
		income.addPayment(payment);
		return dao.update(income);
	}

	@Override
	public Income create(Income income) {
		income.setValueTransaction(income.getValueTransaction().abs());
		income.setFunds(fundsDao.find(income.getFunds().getId()));
		income.setLastMaturity(income.getFirstMaturity().plus(income.getRecurrent(), income.getCalendarPeriod().getChronoUnit()));
		return dao.create(income);
	}

	@Override
	public List<Income> list() {
		return dao.list();
	}

	@Override
	public Income find(ObjectId id) {
		return dao.find(id);
	}

	@Override
	public Income update(ObjectId id, Income income) {
		Income incomeAux = dao.find(id);
		incomeAux.setDescription(income.getDescription());
		incomeAux.setFunds(income.getFunds());
		incomeAux.setGroup(income.getGroup());
		incomeAux.setFixedTransaction(income.isFixedTransaction());
		incomeAux.setValueTransaction(income.getValueTransaction());
		incomeAux.setRecurrent(income.getRecurrent());
		
		return dao.update(incomeAux);
	}
}