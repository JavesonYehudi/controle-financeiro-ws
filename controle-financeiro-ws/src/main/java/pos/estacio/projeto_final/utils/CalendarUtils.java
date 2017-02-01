package pos.estacio.projeto_final.utils;

import java.util.Calendar;

public class CalendarUtils {
	public static Calendar dayWithoutHours(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}
}
