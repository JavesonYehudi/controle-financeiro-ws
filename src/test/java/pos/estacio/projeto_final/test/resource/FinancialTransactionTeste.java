package pos.estacio.projeto_final.test.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import br.com.controlefinanceiro.enumeration.ECalendarPeriod;
import br.com.controlefinanceiro.model.Funds;
import br.com.controlefinanceiro.model.Income;
import br.com.controlefinanceiro.utils.MaturityUtils;

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
		List<LocalDate> list = MaturityUtils.listDates(LocalDate.of(2016, 1, 02), 5, ECalendarPeriod.MONTH);
		list = list.stream().filter(date -> LocalDate.now().getMonth().equals(date.getMonth()) && LocalDate.now().getYear() == date.getYear()).collect(Collectors.toList());
		System.out.println(list.isEmpty());
	}
}