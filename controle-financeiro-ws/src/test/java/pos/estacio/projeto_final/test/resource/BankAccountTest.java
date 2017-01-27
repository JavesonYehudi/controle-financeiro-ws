package pos.estacio.projeto_final.test.resource;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import pos.estacio.projeto_final.model.BankAccount;
import pos.estacio.projeto_final.test.model.BankAccountResponse;

public class BankAccountTest {

	@Test
	public void test() {
		//save();
		list();
	}
	
	@SuppressWarnings("unused")
	private void save(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/ws/bank-account/save");
		BankAccount bankAccount = new BankAccount();

		Entity<BankAccount> entity = Entity.entity(bankAccount, MediaType.APPLICATION_JSON);

		BankAccountResponse response = target.request().put(entity, BankAccountResponse.class);
		Assert.assertEquals("Itaú", response.toString());
		System.out.println(response);
	}
	
	private void list(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/ws/bank-account/list");

		BankAccountResponse[] response = target.request().get(BankAccountResponse[].class);
		System.out.println(Arrays.asList(response));
	}

}
