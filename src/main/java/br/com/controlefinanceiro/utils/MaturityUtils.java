package br.com.controlefinanceiro.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.controlefinanceiro.enumeration.ECalendarPeriod;

public class MaturityUtils {
	
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
}