package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.Payment;
import br.com.controlefinanceiro.service.ExpenseService;

@Path(value = "/expense")
public class ExpenseResource extends GenericResource<Expense> implements IFinancialTransactionResource {

	@Inject
	private ExpenseService service;

	@Override
	public Response create(Expense expense) {
		try {
			return Response.status(201).entity(service.create(expense)).build();
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
	public Response update(ObjectId id, Expense expense) {
		try {
			return Response.status(200).entity(service.update(id, expense)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}
