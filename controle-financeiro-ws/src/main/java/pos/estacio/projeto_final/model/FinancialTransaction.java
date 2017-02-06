package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@Column(nullable = false)
	@JsonProperty("description")
	protected String description;

	@Column(nullable = false)
	@JsonProperty("valueTransaction")
	protected BigDecimal valueTransaction;

	@OneToMany(mappedBy = "financialTransaction", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonProperty("maturityList")
	protected List<Maturity> maturityList;

	@OneToMany(mappedBy = "financialTransaction", cascade = CascadeType.ALL)
	@JsonIgnore
	protected List<Payment> payments;

	@ManyToOne
	@JsonProperty("funds")
	protected Funds funds;

	@Column(nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("firstMaturity")
	protected LocalDate firstMaturity;

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
		setRecurrent(1);
		setCalendarPeriod(ECalendarPeriod.MONTH);
		setMaturityList(new ArrayList<>());
		setPayments(new ArrayList<>());
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

	public List<Maturity> getMaturityList() {
		return maturityList;
	}

	public void setMaturityList(List<Maturity> maturityList) {
		this.maturityList = maturityList;
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

	public LocalDate getFirstMaturity() {
		return firstMaturity;
	}

	public void setFirstMaturity(LocalDate firstMaturity) {
		this.firstMaturity = firstMaturity;
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

	public void addMaturity(Maturity maturity) {
		this.getMaturityList().add(maturity);
	}

	public void addPayment(Payment payment) {
		this.getPayments().add(payment);
	}

	public BigDecimal getTotalPaid() {
		return this.getPayments().stream().map(Payment::getValuePaid).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}