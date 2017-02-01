package pos.estacio.projeto_final.resource;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.Income;
import pos.estacio.projeto_final.service.IFinancialTransactionService;

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
	public Response execute(Integer id, BigDecimal valueExecuted) {
		try {
			return Response.status(201).entity(incomeExecutor.execute(id, valueExecuted)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}
