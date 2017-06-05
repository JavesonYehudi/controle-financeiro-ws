package br.com.controlefinanceiro.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

public abstract class GenericResource<T> {

	@POST
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response create(T t);

	@PUT
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response update(@PathParam("id") ObjectId id, T t);

/*	@DELETE
	@Path(value = "/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response delete(@PathParam("id") ObjectId id);
*/
	@GET
	@Path(value = "{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response find(@PathParam("id") ObjectId id);
 
	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response list();

	protected Response errorMessage(Exception e) {
		String error = "{\"error\" : \"" + e.getMessage() + "\"}";
		return Response.status(403).entity(error).build();
	}
}