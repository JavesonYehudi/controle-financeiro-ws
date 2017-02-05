package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "payment", schema = "financeiro")
public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 180233392066903390L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private int id;

	@Column(nullable = false)
	@JsonProperty("valuePaid")
	private BigDecimal valuePaid;

	@Column(nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("datePayment")
	private LocalDate datePayment;

	@ManyToOne
	@JsonBackReference
	@JsonProperty("financialTransaction")
	private FinancialTransaction financialTransaction;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public FinancialTransaction getFinancialTransaction() {
		return financialTransaction;
	}

	public void setFinancialTransaction(FinancialTransaction financialTransaction) {
		this.financialTransaction = financialTransaction;
	}
}