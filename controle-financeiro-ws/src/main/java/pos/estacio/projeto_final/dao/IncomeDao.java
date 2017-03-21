package pos.estacio.projeto_final.dao;

import java.util.List;

import javax.persistence.Query;

import pos.estacio.projeto_final.model.Income;
import pos.estacio.projeto_final.model.User;

public class IncomeDao extends GenericDao<Income> {

	public IncomeDao() {
		super(Income.class);
	}

	@SuppressWarnings("unchecked")
	public List<Income> list(User user) {
		Query query = entityManager.createQuery("select i from Income i inner join i.funds f where f.user = :user")
				.setParameter("user", user);
		return query.getResultList();
	}
}
