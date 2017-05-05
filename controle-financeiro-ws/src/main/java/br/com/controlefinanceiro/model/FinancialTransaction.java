package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.controlefinanceiro.enumeration.ECalendarPeriod;
import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;

@Entity(noClassnameStored = true, value = "financialTransaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialTransaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3629603514965142970L;
	@Id
	protected Integer id;
	protected String description;
	protected BigDecimal valueTransaction;
	@JsonManagedReference
	protected Set<Maturity> maturityList;
	@JsonIgnore
	protected List<Payment> payments;
	protected Funds funds;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected LocalDate firstMaturity;
	protected boolean fixedTransaction;
	protected int recurrent;
	protected ECalendarPeriod calendarPeriod;
	protected Group group;
	@Transient
	private EFinancialTransactionType eFinancialTransactionType;

	public FinancialTransaction() {
		setRecurrent(1);
		setCalendarPeriod(ECalendarPeriod.MONTH);
		setMaturityList(new HashSet<>());
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

	public Set<Maturity> getMaturityList() {
		return maturityList;
	}

	public void setMaturityList(Set<Maturity> maturityList) {
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@JsonProperty("financialTransactionType")
	public EFinancialTransactionType getEFinancialTransactionType() {
		return eFinancialTransactionType;
	}

	@JsonIgnore
	public void setEFinancialTransactionType(EFinancialTransactionType eFinancialTransactionType) {
		this.eFinancialTransactionType = eFinancialTransactionType;
	}

	public void addMaturity(Maturity maturity) {
		this.getMaturityList().add(maturity);
	}

	public void addPayment(Payment payment) {
		this.getPayments().add(payment);
	}

	@JsonIgnore
	public BigDecimal getTotalPaid() {
		return this.getPayments().stream().map(Payment::getValuePaid).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}