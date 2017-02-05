package pos.estacio.projeto_final.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pos.estacio.projeto_final.model.Payment;

public interface IFinancialTransactionResource{
	
	@PUT
	@Path(value = "/execute/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response execute(@PathParam("id") Integer id, Payment payment);
}
