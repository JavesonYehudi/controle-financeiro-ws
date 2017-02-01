package pos.estacio.projeto_final.service;

import java.math.BigDecimal;
import java.util.List;

import pos.estacio.projeto_final.model.FinancialTransaction;

public interface IFinancialTransactionService<T extends FinancialTransaction> {
	public T execute(int id, BigDecimal valueExecuted);
	public T create(T t);
	public List<T> list();
}
