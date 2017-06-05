package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

public class Maturity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2252052230847560263L;
	@JsonSerialize(using = NoObjectIdSerializer.class)
	private ObjectId id;
	private BigDecimal value;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private Payment payment;
	@Reference
	@JsonBackReference
	private FinancialTransaction financialTransaction;

	public Maturity() {}

	public Maturity(BigDecimal value, LocalDate date, FinancialTransaction financialTransaction) {
		this.value = value;
		this.date = date;
		this.financialTransaction = financialTransaction;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public FinancialTransaction getFinancialTransaction() {
		return financialTransaction;
	}

	public void setFinancialTransaction(FinancialTransaction financialTransaction) {
		this.financialTransaction = financialTransaction;
	}

	@JsonIgnore
	@JsonProperty("paid")
	public boolean isPaid() {
		return this.getPayment() != null;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("Transacao: ").append(financialTransaction.getDescription()).append(", Vencimento: ")
				.append(this.getDate().toString()).append(", Valor: ").append(this.getValue().toString());
		return stringBuffer.toString();
	}

}
