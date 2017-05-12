package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 180233392066903390L;
	private ObjectId id;
	private BigDecimal valuePaid;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datePayment;
	private Maturity maturity;
	@JsonBackReference
	private FinancialTransaction financialTransaction;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public BigDecimal getValuePaid() {
		return valuePaid;
	}

	public void setValuePaid(BigDecimal valuePaid) {
		this.valuePaid = valuePaid;
	}

	public LocalDate getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(LocalDate datePayment) {
		this.datePayment = datePayment;
	}

	@JsonIgnore
	public Maturity getMaturity() {
		return maturity;
	}

	@JsonProperty("maturity")
	public void setMaturity(Maturity maturity) {
		this.maturity = maturity;
	}

	public FinancialTransaction getFinancialTransaction() {
		return financialTransaction;
	}

	public void setFinancialTransaction(FinancialTransaction financialTransaction) {
		this.financialTransaction = financialTransaction;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("Transacao: ").append(financialTransaction.getDescription()).append(", Vencimento: ")
				.append(maturity.getDate().toString()).append(", Valor do vencimento: ")
				.append(maturity.getValue().toString()).append(", Data do pagamento: ")
				.append(this.getDatePayment().toString()).append(", Valor do pagamento: ").append(this.getValuePaid());
		return super.toString();
	}
}