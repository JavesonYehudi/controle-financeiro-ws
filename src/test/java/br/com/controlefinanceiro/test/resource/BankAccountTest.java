package br.com.controlefinanceiro.test.resource;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import br.com.controlefinanceiro.model.BankAccount;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.test.model.BankAccountResponse;

@SuppressWarnings("unused")
public class BankAccountTest {

	@Test
	public void test() {
		// save();
		list();
	}

	private void save() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/ws/bank-account/save");
		BankAccount bankAccount = new BankAccount();

		Entity<BankAccount> entity = Entity.entity(bankAccount, MediaType.APPLICATION_JSON);

		BankAccountResponse response = target.request().put(entity, BankAccountResponse.class);
		Assert.assertEquals("Itaú", response.toString());
	}

	private void list() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/funds/list");

		Response response = target.request().get(Response.class);
		Funds[] funds = response.readEntity(Funds[].class);
		System.out.println(Arrays.asList(funds));
	}

}