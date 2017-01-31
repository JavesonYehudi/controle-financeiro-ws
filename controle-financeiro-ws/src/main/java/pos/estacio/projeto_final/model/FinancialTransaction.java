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

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Column(nullable = true)
	@JsonProperty("description")
	protected String description;

	@Column(nullable = true)
	@JsonProperty("valueTransaction")
	protected BigDecimal valueTransaction;

	@JsonProperty("valueExecuted")
	protected BigDecimal valueExecuted;

	@ManyToOne
	@JsonProperty("funds")
	protected Funds funds;

	@Column(nullable = true)
	@JsonProperty("dayOfMaturity")
	protected Date dayOfMaturity;

	@JsonProperty("fixedTransaction")
	protected boolean fixedTransaction;

	@JsonProperty("recurrent")
	protected int recurrent;

	@Transient
	@JsonProperty("financialTransactionType")
	private EFinancialTransactionType eFinancialTransactionType;

	public FinancialTransaction() {
		setRecurrent(1);
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

	public EFinancialTransactionType getEFinancialTransactionType() {
		return eFinancialTransactionType;
	}

	public void setEFinancialTransactionType(EFinancialTransactionType eFinancialTransactionType) {
		this.eFinancialTransactionType = eFinancialTransactionType;
	}
}
