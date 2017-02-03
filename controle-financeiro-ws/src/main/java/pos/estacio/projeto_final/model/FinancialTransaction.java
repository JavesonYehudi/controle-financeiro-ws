package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import pos.estacio.projeto_final.enumeration.ECalendarPeriod;
import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;

@Entity
@Table(name = "financial_transaction", schema = "financeiro")
public class FinancialTransaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3629603514965142970L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	protected Integer id;

	@Column(nullable = true)
	@JsonProperty("description")
	protected String description;

	@Column(nullable = true)
	@JsonProperty("valueTransaction")
	protected BigDecimal valueTransaction;

	@JsonProperty("valueExecuted")
	protected BigDecimal valueExecuted;

	@ManyToOne
	@JsonProperty("funds")
	protected Funds funds;

	@Column(nullable = true)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonProperty("dayOfMaturity")
	protected LocalDate dayOfMaturity;

	@JsonProperty("fixedTransaction")
	protected boolean fixedTransaction;

	@Column(columnDefinition = "default '0'")
	@JsonProperty("recurrent")
	protected int recurrent;

	@JsonProperty("period")
	protected ECalendarPeriod calendarPeriod;

	@Transient
	@JsonProperty("financialTransactionType")
	private EFinancialTransactionType eFinancialTransactionType;

	public FinancialTransaction() {
		setValueExecuted(BigDecimal.ZERO);
		setCalendarPeriod(ECalendarPeriod.MONTH);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValueTransaction() {
		return valueTransaction;
	}

	public void setValueTransaction(BigDecimal value) {
		this.valueTransaction = value;
	}

	public BigDecimal getValueExecuted() {
		return valueExecuted;
	}

	public void setValueExecuted(BigDecimal valueExecuted) {
		this.valueExecuted = valueExecuted;
	}

	public Funds getFunds() {
		return funds;
	}

	public void setFunds(Funds funds) {
		this.funds = funds;
	}

	public LocalDate getDayOfMaturity() {
		return dayOfMaturity;
	}

	public void setDayOfMaturity(LocalDate dayOfMaturity) {
		this.dayOfMaturity = dayOfMaturity;
	}

	public boolean isFixedTransaction() {
		return fixedTransaction;
	}

	public void setFixedTransaction(boolean fixedTransaction) {
		this.fixedTransaction = fixedTransaction;
	}

	public int getRecurrent() {
		return recurrent;
	}

	public void setRecurrent(int recurrent) {
		this.recurrent = recurrent;
	}

	public ECalendarPeriod getCalendarPeriod() {
		return calendarPeriod;
	}

	public void setCalendarPeriod(ECalendarPeriod calendarPeriod) {
		this.calendarPeriod = calendarPeriod;
	}

	public EFinancialTransactionType getEFinancialTransactionType() {
		return eFinancialTransactionType;
	}

	public void setEFinancialTransactionType(EFinancialTransactionType eFinancialTransactionType) {
		this.eFinancialTransactionType = eFinancialTransactionType;
	}

	//@JsonSerialize(using = LocalDateSerializer[].class)
	@JsonProperty("maturityList")
	public List<LocalDate> getMaturityList() {
		List<LocalDate> list = new ArrayList<>();

		IntStream.range(0, this.getRecurrent()).forEach(i -> {
			list.add(this.getDayOfMaturity().plus(i, this.getCalendarPeriod().getChronoUnit()));
		});

		return list;
	}
	/*
	 * public boolean isMaturityOfTheMonth() {
	 * 
	 * Calendar today = DatesUtils.dayWithoutHours(Calendar.getInstance());
	 * Calendar thisPeriod =
	 * DatesUtils.dayWithoutHours(this.getDayOfMaturityCalendar()); Calendar
	 * thisPeriodWithRecurrent =
	 * DatesUtils.dayWithoutHours(this.getDayOfMaturityCalendar());
	 * 
	 * thisPeriodWithRecurrent.add(this.getCalendarPeriod().getPeriod(),
	 * getRecurrent());
	 * 
	 * boolean t = thisPeriod.get(Calendar.MONTH) == today.get(Calendar.MONTH)
	 * && thisPeriod.get(Calendar.YEAR) == today.get(Calendar.YEAR);
	 * 
	 * return thisPeriod.compareTo(today) >= 0 &&
	 * thisPeriodWithRecurrent.compareTo(today) <= 0; }
	 */
}