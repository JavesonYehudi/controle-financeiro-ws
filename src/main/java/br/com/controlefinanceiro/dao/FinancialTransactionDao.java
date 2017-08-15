package br.com.controlefinanceiro.dao;

import java.time.LocalDate;
import java.util.List;

import br.com.controlefinanceiro.model.FinancialTransaction;

public class FinancialTransactionDao extends GenericDao<FinancialTransaction> {

	public FinancialTransactionDao() {
		super(FinancialTransaction.class);
	}

	public List<FinancialTransaction> getFinancialTransactions(LocalDate start, LocalDate end) {
		this.getQuery().and(getQuery().criteria("firstMaturity").greaterThanOrEq(start));
		this.getQuery().or(getQuery().criteria("lastMaturity").lessThanOrEq(end), getQuery().criteria("fixedTransaction").equal(true)); 
		return this.getQuery().asList();
	}
	
	@Override
	public String getFieldUser() {
		return "funds.user.id";
	}
}
