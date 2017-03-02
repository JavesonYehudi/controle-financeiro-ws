package pos.estacio.projeto_final.service;

import java.util.List;

import javax.inject.Inject;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.CreditCard;

public class CreditCardService {
	@Inject
	private GenericDao<CreditCard> creditCardDao;

	public CreditCard create(CreditCard creditCard) {
			return creditCardDao.create(creditCard);
	}

	public List<CreditCard> list() {
			return creditCardDao.list();
	}

	public CreditCard find(int id) {
		return creditCardDao.find(id);
	}

	public CreditCard update(int id, CreditCard creditCard) {
		CreditCard creditCardAux = creditCardDao.find(id);
		creditCardAux.setLimitValue(creditCard.getLimitValue());
		creditCardAux.setMaturity(creditCard.getMaturity());
		creditCardAux.setDescription(creditCard.getDescription());
		return creditCardDao.update(creditCardAux);
	}

	public void delete(int id) {
		creditCardDao.delete(id);
	}
}
