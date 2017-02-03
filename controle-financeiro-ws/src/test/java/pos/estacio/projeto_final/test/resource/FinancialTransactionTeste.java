package pos.estacio.projeto_final.test.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import pos.estacio.projeto_final.enumeration.ECalendarPeriod;
import pos.estacio.projeto_final.model.FinancialTransaction;
import pos.estacio.projeto_final.model.Funds;
import pos.estacio.projeto_final.model.Income;

public class FinancialTransactionTeste {

	// @Test
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

		// income.setDayOfMaturity(calendar.getTime());

		Entity<Income> entity = Entity.entity(income, MediaType.APPLICATION_JSON);

		Response response = target.request().put(entity, Response.class);
		Income incomeResponse = response.readEntity(Income.class);
		System.out.println(incomeResponse);
	}

	// @Test
	public void list() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/controle-financeiro-ws/income/list");

		Response response = target.request().get(Response.class);
		Income[] income = response.readEntity(Income[].class);

		System.out.println(Arrays.asList(income));
	}

	@Test
	public void testClass() {
		System.out.println(ECalendarPeriod.MONTH.getChronoUnit());
		// date.plus(recurrency, eCalendarPeriod.getChronoUnit());
	}

	public List<Calendar> getList(Calendar calendar, int qtd) {
		List<Calendar> calendars = new ArrayList<>();
		// calendars.stream().reduce(calendar, accumulator)
		for (int i = 0; i < qtd; i++) {
			Calendar c = (Calendar) calendar.clone();
			c.add(Calendar.MONTH, i);
			calendars.add(c);
		}
		return calendars;
	}

}