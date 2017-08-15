package br.com.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import br.com.controlefinanceiro.model.Expense;
import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.service.TimeLineService;
import br.com.controlefinanceiro.service.TimeLineService.TimeLine;

public class ThreadTest {
	
	@Test
	public void test() {
		List<FinancialTransaction> financialTransactions = new ArrayList<>(); 
		Expense expense1 = new Expense();
		expense1.setId(ObjectId.get());
		expense1.setDescription("expense1");
		expense1.setFixedTransaction(true);
		expense1.setValueTransaction(BigDecimal.TEN);
		expense1.setFirstMaturity(LocalDate.parse("2017-05-05"));
		
		
		Expense expense2 = new Expense();
		expense2.setId(ObjectId.get());
		expense2.setDescription("expense2");
		expense2.setRecurrent(3);
		expense2.setValueTransaction(BigDecimal.TEN);
		expense2.setFirstMaturity(LocalDate.parse("2017-05-07"));
		expense2.setLastMaturity(LocalDate.parse("2017-08-07"));
		
		financialTransactions.add(expense1);
		financialTransactions.add(expense2);

		TimeLineService timelineService = new TimeLineService();
		/*, LocalDate.parse("2017-05-05"), LocalDate.parse("2017-09-05")*/
		TimeLine timeLineItem =  timelineService.new TimeLine(financialTransactions, LocalDate.parse("2017-05-05"), LocalDate.parse("2017-10-10"));
		
		
		System.out.println(timeLineItem.build());
	}

}


