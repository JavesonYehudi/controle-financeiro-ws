package pos.estacio.projeto_final.resource;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.model.Payment;

public class PaymentResource extends GenericResource<Payment> {

	@Inject
	private GenericDao<Payment> paymentDao;

	@Override
	public Response create(Payment payment) {
		try {
			return Response.status(201).entity(paymentDao.create(payment)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(paymentDao.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(Integer id) {
		try{
			return Response.status(200).entity(paymentDao.find(id)).build();
		}catch (Exception e) {
			return errorMessage(e);
		}
	}

}
