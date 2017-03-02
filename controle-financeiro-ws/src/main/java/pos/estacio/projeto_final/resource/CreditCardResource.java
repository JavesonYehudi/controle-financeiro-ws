package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.CreditCard;
import pos.estacio.projeto_final.service.CreditCardService;

@Path(value = "/credit-card")
public class CreditCardResource extends GenericResource<CreditCard> {

	@Inject
	private CreditCardService creditCardService;

	@Override
	public Response create(CreditCard creditCard) {
		try {
			return Response.status(201).entity(creditCardService.create(creditCard)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(creditCardService.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(int id) {
		try {
			return Response.status(200).entity(creditCardService.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, CreditCard creditCard) {
		creditCard.setId(id);
		try {
			return Response.status(200).entity(creditCardService.update(id, creditCard)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response delete(int id) {
		try {
			creditCardService.delete(id);
			return Response.status(200).entity(true).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}