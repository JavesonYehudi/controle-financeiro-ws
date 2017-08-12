package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Maturity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6382941825346573672L;
	private BigDecimal value;
	private BigDecimal valuePaid;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datePayment;

	public Maturity() {
	}

	public Maturity(BigDecimal value, LocalDate date) {
		this.value = value;
		this.date = date;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValuePaid() {
		return valuePaid;
	}

	public void setValuePaid(BigDecimal valuePaid) {
		this.valuePaid = valuePaid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDate datePayment) {
		this.datePayment = datePayment;
	}

	/*
	 * public Payment getPayment() { return payment; }
	 * 
	 * public void setPayment(Payment payment) { this.payment = payment; }
	 */

	/*
	 * @JsonIgnore
	 * 
	 * @JsonProperty("paid") public boolean isPaid() { return this.getPayment() !=
	 * null; }
	 */

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("Transacao: ").append(", Vencimento: ").append(this.getDate().toString())
				.append(", Valor: ").append(this.getValue().toString());
		return stringBuffer.toString();
	}

}
