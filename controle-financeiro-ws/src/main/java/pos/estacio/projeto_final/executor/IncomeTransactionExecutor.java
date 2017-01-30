package pos.estacio.projeto_final.executor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.FinancialTransaction;

@RequestScoped
@Named("pay")
public class IncomeTransactionExecutor implements IFinancialTransactionExecutor {

	@Inject
	private GenericDao<FinancialTransaction> financialTransactionDao;

	@Override
	public void execute(FinancialTransaction financialTransaction) {
		financialTransaction.setValueTransaction(financialTransaction.getValueTransaction());
		financialTransaction.setValueExecuted(financialTransaction.getValueTransaction().negate());
		financialTransactionDao.update(financialTransaction);
	}

}