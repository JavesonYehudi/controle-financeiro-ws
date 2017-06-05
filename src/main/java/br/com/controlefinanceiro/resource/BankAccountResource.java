package br.com.controlefinanceiro.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import br.com.controlefinanceiro.model.BankAccount;
import br.com.controlefinanceiro.service.BankAccountService;

@Path(value = "/bank-account")
public class BankAccountResource extends GenericResource<BankAccount> {

	@Inject
	private BankAccountService bankAccountService;

	@Override
	public Response create(BankAccount bankAccount) {
		try {
			return Response.status(201).entity(bankAccountService.create(bankAccount)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response list() {
		try {
			return Response.status(200).entity(bankAccountService.list()).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response find(ObjectId id) {
		try {
			return Response.status(200).entity(bankAccountService.find(id)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}

	@Override
	public Response update(ObjectId id, BankAccount bankAccount) {
		try {
			return Response.status(200).entity(bankAccountService.update(id, bankAccount)).build();
		} catch (Exception e) {
			return errorMessage(e);
		}
	}
}