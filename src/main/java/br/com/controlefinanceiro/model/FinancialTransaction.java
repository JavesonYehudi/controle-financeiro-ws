package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.controlefinanceiro.enumeration.ECalendarPeriod;
import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

@Entity(noClassnameStored = true, value = "financialTransaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialTransaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3629603514965142970L;
	@Id
	@JsonSerialize(using = NoObjectIdSerializer.class)
	protected ObjectId id;
	protected String description;
	protected BigDecimal valueTransaction;
	@Embedded
	protected Set<Payment> payments;
	protected Funds funds;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected LocalDate firstMaturity;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected LocalDate lastMaturity;
	protected boolean fixedTransaction;
	protected int recurrent;
	protected ECalendarPeriod calendarPeriod;
	protected Group group;
	protected int financialTransactionType;

	public FinancialTransaction() {
		setRecurrent(0);
		setCalendarPeriod(ECalendarPeriod.MONTH);
		setPayments(new HashSet<>());
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
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
	
	public LocalDate getLastMaturity() {
		return lastMaturity;
	}

	public void setLastMaturity(LocalDate lastMaturity) {
		this.lastMaturity = lastMaturity;
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

	public int getFinancialTransactionType() {
		return financialTransactionType;
	}

	public void addPayment(Payment payment) {
		this.getPayments().add(payment);
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("description: ").append(description);
		stringBuffer.append(", value: ").append(valueTransaction);
		stringBuffer.append(", firstMaturity: ").append(firstMaturity);
		stringBuffer.append(", lastMaturity: ").append(lastMaturity);
		return stringBuffer.toString();
	}
}
