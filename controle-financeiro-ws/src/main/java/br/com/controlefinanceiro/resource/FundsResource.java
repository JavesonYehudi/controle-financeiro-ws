package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.service.FundsService;

@Path(value = "/funds")
public class FundsResource extends GenericResource<Funds> {

	@Inject
	private FundsService fundsService;

	@Override
	public Response create(Funds funds) {
		try {
			return Response.status(201).entity(fundsService.create(funds)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(fundsService.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(int id) {
		try {
			return Response.status(200).entity(fundsService.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, Funds funds) {
		try {
			return Response.status(200).entity(fundsService.update(id, funds)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}