package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private Integer id;

	@Column(nullable = false)
	private BigDecimal valuePaid;

	@Column(nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datePayment;

	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
	private Maturity maturity;

	@ManyToOne
	@JsonBackReference
	private FinancialTransaction financialTransaction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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