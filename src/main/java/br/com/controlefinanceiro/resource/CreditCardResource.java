package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.CreditCard;
import br.com.controlefinanceiro.service.CreditCardService;

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
	public Response find(ObjectId id) {
		try {
			return Response.status(200).entity(creditCardService.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(ObjectId id, CreditCard creditCard) {
		try {
			return Response.status(200).entity(creditCardService.update(id, creditCard)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}