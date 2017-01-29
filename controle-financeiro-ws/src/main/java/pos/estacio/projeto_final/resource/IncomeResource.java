package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.executor.IFinancialTransactionExecutor;
import pos.estacio.projeto_final.model.FinancialTransaction;
import pos.estacio.projeto_final.model.Income;

@Path(value = "/financial-transaction")
public class IncomeResource extends GenericResource<Income> {

	@Inject
	@Named("pay")
	private IFinancialTransactionExecutor financialTransactionPayExecutor;

	@Inject
	private GenericDao<FinancialTransaction> financialTransactionDao;

	@Override
	public Response save(Income income) {
		try {
			return Response.status(201).entity(financialTransactionDao.create(income)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(financialTransactionDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@POST
	@Path(value = "/pay/{income.id}/{income.valueExecuted}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pay(Income income) {
		financialTransactionPayExecutor.execute(income);
		return Response.status(201).entity(income).build();
	}

	@POST
	@Path(value = "/deposit/{financialTransaction.id}/{financialTransaction.value}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deposit(FinancialTransaction financialTransaction) {
		return Response.status(201).entity(financialTransaction).build();
	}
}
