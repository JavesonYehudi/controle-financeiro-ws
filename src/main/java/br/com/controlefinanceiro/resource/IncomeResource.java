package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.model.Payment;
import br.com.controlefinanceiro.service.IFinancialTransactionService;

@Path(value = "/income")
public class IncomeResource extends GenericResource<Income> implements IFinancialTransactionResource {

	@Inject
	@Named("income")
	private IFinancialTransactionService<Income> incomeExecutor;

	@Override
	public Response create(Income income) {
		try {
			return Response.status(201).entity(incomeExecutor.create(income)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(incomeExecutor.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response pay(int id, Payment payment) {
		try {
			return Response.status(201).entity(incomeExecutor.pay(id, payment)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(int id) {
		try {
			return Response.status(200).entity(incomeExecutor.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, Income income) {
		try {
			return Response.status(200).entity(incomeExecutor.update(id, income)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}
