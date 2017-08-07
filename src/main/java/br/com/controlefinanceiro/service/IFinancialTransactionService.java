package br.com.controlefinanceiro.service;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.Maturity;

public interface IFinancialTransactionService<T extends FinancialTransaction> {
	public T pay(ObjectId id, Maturity payment) throws Exception;
}
