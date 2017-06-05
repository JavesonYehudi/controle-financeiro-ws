package br.com.controlefinanceiro.service;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.Payment;

public interface IFinancialTransactionService<T extends FinancialTransaction> {
	public T pay(ObjectId id, Payment payment) throws Exception;
}
