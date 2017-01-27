package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.BankAccount;

@Path(value = "/bank-account")
public class BankAccountResource {

	@Inject
	private GenericDao<BankAccount> bankAccountDao;

	@POST
	@Path(value = "/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(BankAccount bankAccount) {
		try {
			return Response.status(201).entity(bankAccountDao.create(bankAccount)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@GET
	@Path(value = "/list")
	@Produces("application/json")
	public Response list() {
		try {
			return Response.status(200).entity(bankAccountDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	private Response errorMessage(Exception e) {
		String error = "{\"error\" : \"" + e.getMessage() + "\"}";
		return Response.status(200).entity(error).build();
	}

}
