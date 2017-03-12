package pos.estacio.projeto_final.dao;

import java.util.List;

import javax.persistence.Query;

import pos.estacio.projeto_final.model.Expense;
import pos.estacio.projeto_final.model.User;

public class ExpenseDao extends GenericDao<Expense> {

	public ExpenseDao() {
		super(Expense.class);
	}

	@SuppressWarnings("unchecked")
	public List<Expense> list(User user){
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select e from Expense e inner join e.funds f where f.user = :user").setParameter("user", user);
		List<Expense> resultList = query.getResultList();
		this.closeEntityManager();
		return resultList;
	}
}
