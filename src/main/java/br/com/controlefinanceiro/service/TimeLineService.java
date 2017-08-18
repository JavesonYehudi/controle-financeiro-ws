
package br.com.controlefinanceiro.service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.controlefinanceiro.dao.FinancialTransactionDao;
import br.com.controlefinanceiro.model.FinancialTransaction;
import br.com.controlefinanceiro.model.TimeLineDate;
import br.com.controlefinanceiro.model.TimeLineDate.TimeLineDateItem;

public class TimeLineService {

	@Inject
	private FinancialTransactionDao dao;

	public List<TimeLineDate> getTimeLine(LocalDate start, LocalDate end) {
		return new TimeLine(dao.getFinancialTransactions(start, end), start, end).build();
	}

	public class TimeLine {

		private List<FinancialTransaction> financialTransactions;
		private LocalDate start;
		private LocalDate end;

		public TimeLine(List<FinancialTransaction> financialTransactions, LocalDate start, LocalDate end) {
			this.financialTransactions = financialTransactions;
			this.start = start;
			this.end = end;
		}

		public List<TimeLineDate> build() {
			List<TimeLineDate> timeLineDates = new ArrayList<>();
			financialTransactions.forEach(financialTransaction -> {
				LocalDate start = this.start.withDayOfMonth(financialTransaction.getFirstMaturity().get(ChronoField.DAY_OF_MONTH));

				do {
					if (dateIsBetweenTransactionDates(start, financialTransaction)) {
						TimeLineDate timeLineDate = new TimeLineDate(start);

						financialTransaction.getPayments().stream()
								.filter(payment -> payment.getDate().get(ChronoField.MONTH_OF_YEAR) == timeLineDate.getDate().get(ChronoField.MONTH_OF_YEAR))
								.forEach(payment -> {
									TimeLineDateItem timeLineDateItem = timeLineDate.new TimeLineDateItem(financialTransaction.getId(), financialTransaction.getDescription(), payment.getValue(), financialTransaction.getFinancialTransactionType());
									timeLineDate.addTimeLineDateItem(timeLineDateItem);
									timeLineDate.setDate(payment.getDate());
								});

						if(timeLineDate.getTimeLineDateItems().isEmpty())
							timeLineDate.addTimeLineDateItem(timeLineDate.new TimeLineDateItem(financialTransaction));

						if (timeLineDates.contains(timeLineDate)) {
							timeLineDates.listIterator().forEachRemaining((timelineItemAction) -> {
								if (timelineItemAction.getDate().equals(timeLineDate.getDate())) {
									timelineItemAction.getTimeLineDateItems().addAll(timeLineDate.getTimeLineDateItems());
								}
							});

						} else {
							timeLineDates.add(timeLineDate);
						}
					}

					start = start.plus(1, financialTransaction.getCalendarPeriod().getChronoUnit());
				} while (isValideDate(start, end));
			});

			return timeLineDates;
		}

		private boolean isValideDate(LocalDate start, LocalDate end) {
			return start.isBefore(end) || start.equals(end);
		}

		private boolean dateIsBetweenTransactionDates(LocalDate date, FinancialTransaction financialTransaction) {
			return (date.isAfter(financialTransaction.getFirstMaturity())
					|| date.equals(financialTransaction.getFirstMaturity()))
					&& (financialTransaction.isFixedTransaction()
							|| date.isBefore(financialTransaction.getLastMaturity())
							|| date.equals(financialTransaction.getLastMaturity()));
		}

	}
}