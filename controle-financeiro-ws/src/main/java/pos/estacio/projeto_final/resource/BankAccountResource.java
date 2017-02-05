package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.BankAccount;

@Path(value = "/bank-account")
public class BankAccountResource extends GenericResource<BankAccount> {

	@Inject
	private GenericDao<BankAccount> bankAccountDao;

	@Override
	public Response create(BankAccount bankAccount) {
		try {
			return Response.status(201).entity(bankAccountDao.create(bankAccount)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(bankAccountDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(Integer id) {
		try{
			return Response.status(200).entity(bankAccountDao.find(id)).build();
		}catch (Exception e) {
			return errorMessage(e);
		}
	}

}