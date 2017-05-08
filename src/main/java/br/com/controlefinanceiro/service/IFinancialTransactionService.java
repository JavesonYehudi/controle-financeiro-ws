package br.com.controlefinanceiro.service;

import java.util.List;

import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.Payment;

public interface IFinancialTransactionService<T extends FinancialTransaction> {
	public T pay(int id, Payment payment) throws Exception;

	public T create(T t);

	public T find(int id);

	public List<T> list();

	public T update(int id, T t);
}
