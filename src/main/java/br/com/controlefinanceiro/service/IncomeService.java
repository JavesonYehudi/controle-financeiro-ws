package br.com.controlefinanceiro.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.model.Maturity;

@RequestScoped
@Named("income")
public class IncomeService extends GenericService<Income> implements IFinancialTransactionService<Income> {

	@Inject
	private GenericDao<Income> dao;

	@Inject
	private GenericDao<Maturity> maturityDao;

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Income pay(ObjectId id, Maturity payment) throws Exception {
		Income expense = dao.find(id);
		payment = findMaturity(payment);
		payment.setFinancialTransaction(expense);
		return dao.update(expense);
	}

	private Maturity findMaturity(Maturity payment) throws Exception {
		if(payment.getId() != null) {
			Maturity maturity = new Maturity();
			maturity = maturityDao.find(payment.getId());
			maturity.setValuePaid(payment.getValuePaid().abs().negate());
			maturity.setDatePayment(payment.getDatePayment());
			return maturity;
		}else {
			payment.setValue(payment.getValuePaid().abs().negate());
			payment.setDate(payment.getDatePayment());
			return payment;
		}
	}

	@Override
	public Income create(Income income) {
		income.setValueTransaction(income.getValueTransaction().abs());
		income.setFunds(fundsDao.find(income.getFunds().getId()));
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