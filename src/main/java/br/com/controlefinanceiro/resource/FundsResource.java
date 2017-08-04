package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.service.FundsService;

@Path("funds")
public class FundsResource extends GenericResource<Funds> {

	@Inject
	private FundsService service;

	@Override
	public Response create(Funds funds) {
		try {
			return Response.status(201).entity(service.create(funds)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(service.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(ObjectId id) {
		try {
			return Response.status(200).entity(service.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(ObjectId id, Funds funds) {
		try {
			return Response.status(200).entity(service.update(id, funds)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}