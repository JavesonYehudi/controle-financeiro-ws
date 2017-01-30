package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;

@Entity
@Table(name = "financial_transaction", schema = "financeiro")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@financialTransactionId")
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
	@JsonManagedReference
	@JsonProperty("funds")
	protected Funds funds;

	@JsonProperty("dayOfMaturity")
	protected Date dayOfMaturity;

	@Transient
	@JsonProperty("financialTransactionType")
	private EFinancialTransactionType eFinancialTransactionType;

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

	public Date getDayOfMaturity() {
		return dayOfMaturity;
	}

	public void setDayOfMaturity(Date dayOfMaturity) {
		this.dayOfMaturity = dayOfMaturity;
	}

	public EFinancialTransactionType getEFinancialTransactionType() {
		return eFinancialTransactionType;
	}

	public void setEFinancialTransactionType(EFinancialTransactionType eFinancialTransactionType) {
		this.eFinancialTransactionType = eFinancialTransactionType;
	}
}
