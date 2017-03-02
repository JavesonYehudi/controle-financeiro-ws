package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.BankAccount;
import pos.estacio.projeto_final.service.BankAccountService;

@Path(value = "/bank-account")
public class BankAccountResource extends GenericResource<BankAccount> {

	@Inject
	private BankAccountService bankAccountService;

	@Override
	public Response create(BankAccount bankAccount) {
		try {
			return Response.status(201).entity(bankAccountService.create(bankAccount)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(bankAccountService.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(int id) {
		try {
			return Response.status(200).entity(bankAccountService.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(int id, BankAccount bankAccount) {
		try {
			return Response.status(200).entity(bankAccountService.update(id, bankAccount)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response delete(int id) {
		try {
			bankAccountService.delete(id);
			return Response.status(200).entity(true).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}