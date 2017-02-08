package pos.estacio.projeto_final.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class GenericResource<T> {

	@POST
	@Path(value = "/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response create(T t);

	@PUT
	@Path(value = "/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response update(@PathParam("id") int id, T t);

	@DELETE
	@Path(value = "/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response delete(@PathParam("id") int id);

	@GET
	@Path(value = "/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response find(@PathParam("id") int id);

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response list();

	protected Response errorMessage(Exception e) {
		String error = "{\"error\" : \"" + e.getMessage() + "\"}";
		return Response.status(200).entity(error).build();
	}
}