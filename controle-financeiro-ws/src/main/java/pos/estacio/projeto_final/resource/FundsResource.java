package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Funds;

@Path(value = "/funds")
public class FundsResource extends GenericResource<Funds> {

	@Inject
	private GenericDao<Funds> fundsDao;

	@Override
	public Response create(Funds funds) {
		try {
			return Response.status(201).entity(fundsDao.create(funds)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(fundsDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

}