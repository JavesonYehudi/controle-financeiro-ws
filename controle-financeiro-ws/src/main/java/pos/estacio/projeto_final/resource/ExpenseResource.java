package pos.estacio.projeto_final.resource;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.Expense;
import pos.estacio.projeto_final.service.IFinancialTransactionService;

@Path(value = "/expense")
public class ExpenseResource extends GenericResource<Expense> implements IFinancialTransactionResource{

	@Inject
	@Named("expense")
	private IFinancialTransactionService<Expense> expenseExecutor;
	
	@Override
	public Response create(Expense t) {
		try {
			return Response.status(201).entity(expenseExecutor.create(t)).build();
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
	public Response execute(Integer id, BigDecimal valueExecuted) {
		try {
			return Response.status(201).entity(expenseExecutor.execute(id, valueExecuted)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}
