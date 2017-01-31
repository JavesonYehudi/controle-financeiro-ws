package pos.estacio.projeto_final.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class GenericResource<T> {

	@POST
	@Path(value = "/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response create(T t);

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response list();

	protected Response errorMessage(Exception e) {
		String error = "{\"error\" : \"" + e.getMessage() + "\"}";
		return Response.status(200).entity(error).build();
	}
}