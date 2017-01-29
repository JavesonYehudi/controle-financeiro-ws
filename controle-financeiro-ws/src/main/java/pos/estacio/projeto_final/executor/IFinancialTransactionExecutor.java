package pos.estacio.projeto_final.executor;

import pos.estacio.projeto_final.model.FinancialTransaction;

public interface IFinancialTransactionExecutor {
	public void execute(FinancialTransaction financialTransaction);
}
