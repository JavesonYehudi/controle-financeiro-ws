package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import pos.estacio.projeto_final.enumeration.EFundsType;

@Entity
@Table(name = "funds", schema = "financeiro")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3769024446632244523L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	protected Integer id;

	@Column(nullable = false)
	@JsonProperty("description")
	protected String description;

	@OneToMany(mappedBy = "funds")
	@JsonBackReference
	@JsonProperty("financialTransactions")
	protected List<FinancialTransaction> financialTransactions;

	public Funds() {
		financialTransactions = new ArrayList<>();
	}

	public Funds(String description) {
		this.description = description;
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

	public List<FinancialTransaction> getFinancialTransactions() {
		return financialTransactions;
	}

	public void setFinancialTransactions(List<FinancialTransaction> financialTransactions) {
		this.financialTransactions = financialTransactions;
	}

	@JsonProperty("fundsType")
	public EFundsType getEFundsType() {
		return EFundsType.DEFAULT;
	}

	@JsonProperty("totalIncomePaid")
	public BigDecimal getCurrentIncomePaid() {
		Predicate<? super FinancialTransaction> predicate = transaction -> transaction.getClass().equals(Income.class);
		return this.getFinancialTransactions().stream().filter(predicate).map(FinancialTransaction::getTotalPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@JsonProperty("totalExpensePaid")
	public BigDecimal getCurrentExpensePaid() {
		Predicate<? super FinancialTransaction> predicate = transaction -> transaction.getClass().equals(Expense.class);
		return this.getFinancialTransactions().stream().filter(predicate).map(FinancialTransaction::getTotalPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@JsonProperty("balance")
	public BigDecimal getCurrentBalance() {
		return this.getFinancialTransactions().stream().map(FinancialTransaction::getTotalPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(this.getEFundsType().getName()).append(": ").append(this.getDescription());
		stringBuffer.append(" Current Balance: ").append(this.getCurrentBalance());
		return stringBuffer.toString();
	}

}
