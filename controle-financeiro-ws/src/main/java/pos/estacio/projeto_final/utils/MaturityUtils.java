package pos.estacio.projeto_final.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;

import pos.estacio.projeto_final.dao.GenericDao;
import pos.estacio.projeto_final.enumeration.ECalendarPeriod;
import pos.estacio.projeto_final.model.FinancialTransaction;
import pos.estacio.projeto_final.model.Maturity;

public class MaturityUtils {
	
	@Inject
	private GenericDao<Maturity> maturityDao;
	
	public static List<LocalDate> listDates(LocalDate date, int recurrency, ECalendarPeriod eCalendarPeriod) {
		List<LocalDate> list = new ArrayList<>();

		IntStream.range(0, recurrency).forEach(i -> {
			list.add(date.plus(i, eCalendarPeriod.getChronoUnit()));
		});

		return list;
	}

	public static boolean isMonthMaturity(List<LocalDate> dates) {
		dates = dates.stream().filter(date -> LocalDate.now().getMonth().equals(date.getMonth())
				&& LocalDate.now().getYear() == date.getYear()).collect(Collectors.toList());
		return !dates.isEmpty();
	}

	public Set<Maturity> maturityListBuilder(FinancialTransaction financialTransaction) {
		Set<Maturity> maturityList = new HashSet<>();

		LocalDate date = financialTransaction.getFirstMaturity();
		int recurrency = financialTransaction.getRecurrent();
		ECalendarPeriod eCalendarPeriod = financialTransaction.getCalendarPeriod();
		
		if(!financialTransaction.getMaturityList().isEmpty()){
			maturityList.addAll(financialTransaction.getMaturityList().stream().filter(maturity -> maturity.isPaid()).collect(Collectors.toList()));
			for(Maturity maturity : financialTransaction.getMaturityList())
				maturityDao.delete(maturity.getId());
		}
		
		IntStream.range(0, recurrency).forEach(i -> {
			maturityList.add(new Maturity(financialTransaction.getValueTransaction(), date.plus(i, eCalendarPeriod.getChronoUnit()),
					financialTransaction));
		});

		return maturityList;
	}
}