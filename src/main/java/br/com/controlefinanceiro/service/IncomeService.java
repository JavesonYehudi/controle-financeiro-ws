package br.com.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.dao.GenericDao;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.model.Maturity;
import br.com.controlefinanceiro.model.Payment;

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
	public Income pay(ObjectId id, Payment payment) throws Exception {
		Income income = dao.find(id);

		payment.setMaturity(findMaturity(payment, income));
		payment.setValuePaid(payment.getValuePaid().abs());
		payment.setFinancialTransaction(income);

		income.addPayment(payment);

		return dao.update(income);
	}

	private Maturity findMaturity(Payment payment, Income income) throws Exception {
		Maturity maturity;
		if (payment.getMaturity().getId() == null) {
			LocalDate date = payment.getDatePayment();
			date = date.withDayOfMonth(income.getFirstMaturity().getDayOfMonth());
			maturity = new Maturity(income.getValueTransaction(), date, income);
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