package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import pos.estacio.projeto_final.enumeration.ECalendarPeriod;
import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;
import pos.estacio.projeto_final.utils.DateUtils;

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

	@OneToMany(mappedBy = "financialTransaction", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonProperty("payments")
	protected List<Payment> payments;

	@ManyToOne
	@JsonProperty("funds")
	protected Funds funds;

	@Column(nullable = true)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("dayOfMaturity")
	protected LocalDate dayOfMaturity;

	@JsonProperty("fixedTransaction")
	protected boolean fixedTransaction;

	@JsonProperty("recurrent")
	protected int recurrent;

	@JsonProperty("period")
	protected ECalendarPeriod calendarPeriod;

	@Transient
	@JsonProperty("financialTransactionType")
	private EFinancialTransactionType eFinancialTransactionType;

	public FinancialTransaction() {
		setValueExecuted(BigDecimal.ZERO);
		setRecurrent(1);
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

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
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

	@JsonSerialize(contentUsing = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("maturityList")
	public List<LocalDate> getMaturityList() {
		return DateUtils.listDates(getDayOfMaturity(), getRecurrent(), getCalendarPeriod());
	}

	@JsonProperty("isMonthMaturiy")
	public boolean isMonthMaturiy() {
		return DateUtils.isMonthMaturity(getMaturityList()) || isFixedTransaction();
	}

	public BigDecimal getTotalPaid() {
		return this.getPayments().stream().map(Payment::getValuePaid).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}