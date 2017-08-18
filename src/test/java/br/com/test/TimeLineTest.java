package br.com.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.Payment;
import br.com.controlefinanceiro.model.TimeLineDate;
import br.com.controlefinanceiro.service.TimeLineService;

public class TimeLineTest {
	
	@Test
	public void test() {
		List<FinancialTransaction> financialTransactions = new ArrayList<>(); 
		Expense expense1 = new Expense();
		expense1.setId(new ObjectId("5996e6a81e2c1821f4781cd9"));
		expense1.setDescription("expense1");
		expense1.setFixedTransaction(true);
		expense1.setValueTransaction(BigDecimal.TEN);
		expense1.setFirstMaturity(LocalDate.parse("2017-05-05"));
		
		Payment payment1 = new Payment();
		payment1.setDate(LocalDate.parse("2017-05-06"));
		payment1.setValue(new BigDecimal(15));

		expense1.addPayment(payment1);
		
		Payment payment2 = new Payment();
		payment2.setDate(LocalDate.parse("2017-06-06"));
		payment2.setValue(new BigDecimal(15));

		expense1.addPayment(payment2);
		
		Expense expense2 = new Expense();
		expense2.setId(new ObjectId("5996e6a81e2c1821f4781cd5"));
		expense2.setDescription("expense2");
		expense2.setRecurrent(3);
		expense2.setValueTransaction(BigDecimal.TEN);
		expense2.setFirstMaturity(LocalDate.parse("2017-05-07"));
		expense2.setLastMaturity(LocalDate.parse("2017-08-07"));
		
		financialTransactions.add(expense1);
		financialTransactions.add(expense2);

		List<TimeLineDate> timeLineDates = new TimeLineService().new TimeLine(financialTransactions, LocalDate.parse("2017-05-05"), LocalDate.parse("2017-10-10")).build();
		System.out.println(timeLineDates);

		/*timeLineDates.forEach(timeLineDate -> {
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-05-04")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd9"));
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getValue(), new BigDecimal(15));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-06-05")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd9"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-07-05")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd9"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-08-05")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd9"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-09-05")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd9"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-10-05")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd9"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-05-07")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd5"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-06-07")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd5"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-07-07")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd5"));
			}
			
			if(timeLineDate.equals(new TimeLineDate(LocalDate.parse("2017-08-07")))) {
				assertEquals(timeLineDate.getTimeLineDateItems().size(), 1);
				assertEquals(timeLineDate.getTimeLineDateItems().get(0).getId() , new ObjectId("5996e6a81e2c1821f4781cd5"));
			}
		});*/
	}

}


