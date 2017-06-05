package br.com.controlefinanceiro.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.FinancialTransaction;

public class FinancialTransactionService extends GenericService<FinancialTransaction> {

	@Override
	public FinancialTransaction create(FinancialTransaction t) {
		return null;
	}

	@Override
	public FinancialTransaction find(ObjectId id) {
		return null;
	}

	@Override
	public List<FinancialTransaction> list() {
		return dao.list();
	}

	@Override
	public FinancialTransaction update(ObjectId id, FinancialTransaction t) {
		return null;
	}

}
