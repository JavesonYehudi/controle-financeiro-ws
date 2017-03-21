package pos.estacio.projeto_final.dao;

import java.util.List;

import javax.persistence.Query;

import pos.estacio.projeto_final.model.FinancialTransaction;
import pos.estacio.projeto_final.model.User;

public class FinancialTransactionDao extends GenericDao<FinancialTransaction> {

	public FinancialTransactionDao() {
		super(FinancialTransaction.class);
	}

	@SuppressWarnings("unchecked")
	public List<FinancialTransaction> list(User user) {
		Query query = entityManager
				.createQuery("select ft from FinancialTransaction ft inner join ft.funds f where f.user = :user")
				.setParameter("user", user);
		return query.getResultList();
	}

}
