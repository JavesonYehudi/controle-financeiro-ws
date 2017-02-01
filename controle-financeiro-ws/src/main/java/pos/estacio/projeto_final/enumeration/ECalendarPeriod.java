package pos.estacio.projeto_final.enumeration;

import java.util.Calendar;

public enum ECalendarPeriod {
	DAY(Calendar.DAY_OF_MONTH), WEEK(Calendar.WEEK_OF_MONTH), MONTH(Calendar.MONTH), YEAR(Calendar.YEAR);

	private int period;

	private ECalendarPeriod(int period) {
		this.setPeriod(period);
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
}
