package br.com.controlefinanceiro.service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.controlefinanceiro.dao.FinancialTransactionDao;
import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.TimelineItem;

public class TimelineService {

	@Inject
	private FinancialTransactionDao dao;
	private List<TimelineItem> timelineItems = new ArrayList<>();

	public List<TimelineItem> buildTimelineItems(LocalDate start, LocalDate end) {
		List<FinancialTransaction> financialTransactions = dao.getFinancialTransactions(start, end);
		List<Thread> threads = new ArrayList<>();

		for (FinancialTransaction financialTransaction : financialTransactions)
			threads.add(new Thread(new BuildTimeLineItem(financialTransaction, start, end)));

		threads.forEach(thread -> {
			thread.start();
		});

		threads.forEach(thread -> {
			while (thread.isAlive()) {
			}
		});

		return timelineItems;
	}

	public class BuildTimeLineItem implements Runnable {

		private FinancialTransaction financialTransaction;
		private LocalDate start;
		private LocalDate end;

		public BuildTimeLineItem(FinancialTransaction financialTransaction, LocalDate start, LocalDate end) {
			this.financialTransaction = financialTransaction;
			this.start = start;
			this.end = end;
		}

		public synchronized void buildTimelineItem() {
			start = start.withDayOfMonth(financialTransaction.getFirstMaturity().get(ChronoField.DAY_OF_MONTH));
			do {
				if(isValide(start, end, financialTransaction)) {
					TimelineItem.Transaction transaction = new TimelineItem.Transaction(financialTransaction);
					TimelineItem timelineItem = new TimelineItem(start);
					System.out.println(start);
					System.out.println(start);
	
					if (timelineItems.contains(timelineItem)) {
						final LocalDate date = start;
						timelineItems.listIterator().forEachRemaining((timelineItemAction) -> {
							if (timelineItemAction.getDate().equals(date))
								timelineItemAction.addTransaction(transaction);
						});
					} else {
						timelineItem.addTransaction(transaction);
						timelineItems.add(timelineItem);
					}
						
				}
				start = start.plus(1, financialTransaction.getCalendarPeriod().getChronoUnit());
			}while (isValideDate(start, end));
		}

		private boolean isValideDate(LocalDate start, LocalDate end) {
			return start.isBefore(end) || start.equals(end);
		}
		
		private boolean isValide(LocalDate start, LocalDate end, FinancialTransaction financialTransaction) {
			return isValideDate(start, end)
					&& (start.isAfter(financialTransaction.getFirstMaturity()) || start.equals(financialTransaction.getFirstMaturity()))
					&& (start.isBefore(financialTransaction.getLastMaturity()) || start.equals(financialTransaction.getLastMaturity()) || financialTransaction.isFixedTransaction());
		}
		
		@Override
		public void run() {
			buildTimelineItem();
		}

	}
}