package br.com.controlefinanceiro.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.Maturity;

public interface IFinancialTransactionResource{
	
	@PUT
	@Path(value = "/pay/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pay(@PathParam("id") ObjectId id, Maturity payment);
}
