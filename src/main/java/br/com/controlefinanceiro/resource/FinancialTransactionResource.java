package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.service.FinancialTransactionService;

@Path("financial-transaction")
public class FinancialTransactionResource extends GenericResource<FinancialTransaction> {

	@Inject
	private FinancialTransactionService service;
	
	@Override
	public Response create(FinancialTransaction t) {
		return null;
	}

	@Override
	public Response update(ObjectId id, FinancialTransaction t) {
		return null;
	}

	@Override
	public Response find(ObjectId id) {
		return null;
	}

	@Override
	public Response list() {
		try{
			return Response.status(200).entity(service.list()).build();			
		}catch (Exception e) {
			return errorMessage(e);
		}
	}

}
