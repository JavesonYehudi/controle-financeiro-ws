package pos.estacio.projeto_final.resource;

import java.math.BigDecimal;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface IFinancialTransactionResource{
	
	@PUT
	@Path(value = "/execute/{id}/{valueExecuted}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response execute(@PathParam("id") Integer id, @PathParam("valueExecuted") BigDecimal valueExecuted);
}
