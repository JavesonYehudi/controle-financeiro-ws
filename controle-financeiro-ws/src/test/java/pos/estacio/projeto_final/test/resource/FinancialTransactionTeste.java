package pos.estacio.projeto_final.test.resource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import pos.estacio.projeto_final.model.Funds;
import pos.estacio.projeto_final.model.Income;
import pos.estacio.projeto_final.utils.CalendarUtils;

public class FinancialTransactionTeste {

	//@Test
	public void save() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/income/create");
		
		Income income = new Income();
		income.setDescription("Faculdade");
		income.setValueTransaction(new BigDecimal(550));
		income.setRecurrent(10);
		
		Funds funds = new Funds();
		funds.setId(827493);
		
		income.setFunds(funds);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 12, 05);
		
		income.setDayOfMaturity(calendar.getTime());

		Entity<Income> entity = Entity.entity(income, MediaType.APPLICATION_JSON);

		Response response = target.request().put(entity, Response.class);
		Income incomeResponse = response.readEntity(Income.class);
		System.out.println(incomeResponse);
	}
	
	//@Test
	public void list(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/income/list");

		Response response = target.request().get(Response.class);
		Income[] income = response.readEntity(Income[].class);

		System.out.println(Arrays.asList(income));
	}

	@Test
	public void testClass(){
		Calendar calendar = CalendarUtils.dayWithoutHours(Calendar.getInstance());
		calendar.add(Calendar.DATE, 1);
		//calendar.add(Calendar.MONTH, 5);
		System.out.println(calendar.getTime());
		
		System.out.println(calendar.compareTo(CalendarUtils.dayWithoutHours(Calendar.getInstance())));
	}
}
