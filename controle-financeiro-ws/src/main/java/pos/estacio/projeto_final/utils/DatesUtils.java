package pos.estacio.projeto_final.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pos.estacio.projeto_final.enumeration.ECalendarPeriod;

public class DatesUtils {
	public static List<LocalDate> listDates(LocalDate date, int recurrency, ECalendarPeriod eCalendarPeriod) {
		List<LocalDate> list = new ArrayList<>();
		date.plus(recurrency, eCalendarPeriod.getChronoUnit());
		return list;
	}
}
