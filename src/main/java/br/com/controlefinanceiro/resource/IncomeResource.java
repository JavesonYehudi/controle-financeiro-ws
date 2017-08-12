package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.model.Payment;
import br.com.controlefinanceiro.service.IncomeService;

@Path(value = "/income")
public class IncomeResource extends GenericResource<Income> implements IFinancialTransactionResource {

	@Inject
	private IncomeService service;

	@Override
	public Response create(Income income) {
		try {
			return Response.status(201).entity(service.create(income)).build();
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
	public Response pay(ObjectId id, Payment payment) {
		try {
			return Response.status(201).entity(service.pay(id, payment)).build();
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
	public Response update(ObjectId id, Income income) {
		try {
			return Response.status(200).entity(service.update(id, income)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}
