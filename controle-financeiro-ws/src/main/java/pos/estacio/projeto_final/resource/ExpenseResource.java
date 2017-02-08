package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.Expense;
import pos.estacio.projeto_final.model.Payment;
import pos.estacio.projeto_final.service.IFinancialTransactionService;

@Path(value = "/expense")
public class ExpenseResource extends GenericResource<Expense> implements IFinancialTransactionResource {

	@Inject
	@Named("expense")
	private IFinancialTransactionService<Expense> expenseExecutor;

	@Override
	public Response create(Expense expense) {
		try {
			return Response.status(201).entity(expenseExecutor.create(expense)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(expenseExecutor.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response pay(int id, Payment payment) {
		try {
			return Response.status(201).entity(expenseExecutor.pay(id, payment)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(int id) {
		try {
			return Response.status(200).entity(expenseExecutor.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, Expense expense) {
		try {
			return Response.status(200).entity(expenseExecutor.update(id, expense)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response delete(int id) {
		try {
			expenseExecutor.delete(id);
			return Response.status(200).entity(true).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}
