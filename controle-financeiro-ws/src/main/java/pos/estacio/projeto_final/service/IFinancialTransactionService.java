package pos.estacio.projeto_final.service;

import java.util.List;

import pos.estacio.projeto_final.model.FinancialTransaction;
import pos.estacio.projeto_final.model.Payment;

public interface IFinancialTransactionService<T extends FinancialTransaction> {
	public T pay(int id, Payment payment);

	public T create(T t);

	public void delete(int id);

	public T find(int id);

	public List<T> list();

	public T update(int id, T t);
}
