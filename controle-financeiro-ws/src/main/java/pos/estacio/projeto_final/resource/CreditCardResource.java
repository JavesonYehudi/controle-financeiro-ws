package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.CreditCard;

@Path(value = "/credit-card")
public class CreditCardResource extends GenericResource<CreditCard> {

	@Inject
	private GenericDao<CreditCard> creditCardDao;

	@Override
	public Response save(CreditCard creditCard) {
		try {
			return Response.status(201).entity(creditCardDao.create(creditCard)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(creditCardDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}