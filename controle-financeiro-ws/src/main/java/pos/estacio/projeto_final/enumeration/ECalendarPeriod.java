package pos.estacio.projeto_final.enumeration;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public enum ECalendarPeriod {
	DAY(Calendar.DAY_OF_MONTH, ChronoUnit.DAYS), WEEK(Calendar.WEEK_OF_MONTH, ChronoUnit.WEEKS), MONTH(Calendar.MONTH, ChronoUnit.MONTHS), YEAR(Calendar.YEAR, ChronoUnit.YEARS);

	private int period;
	private ChronoUnit chronoUnit;

	private ECalendarPeriod(int period, ChronoUnit chronoUnit) {
		this.setPeriod(period);
		this.setChronoUnit(chronoUnit);
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public ChronoUnit getChronoUnit() {
		return chronoUnit;
	}

	public void setChronoUnit(ChronoUnit chronoUnit) {
		this.chronoUnit = chronoUnit;
	}
}
