package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controlefinanceiro.enumeration.EFundsType;

@Entity(noClassnameStored = true, value = "funds")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3769024446632244523L;

	@Id
	protected ObjectId id;
	protected String description;
	@JsonBackReference
	protected List<FinancialTransaction> financialTransactions;
	@Embedded
	protected User user;
	protected EFundsType eFundsType;

	public Funds() {
		financialTransactions = new ArrayList<>();
		eFundsType = EFundsType.DEFAULT;
	}

	public Funds(String description) {
		this.description = description;
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

	public List<FinancialTransaction> getFinancialTransactions() {
		return financialTransactions;
	}

	public void setFinancialTransactions(List<FinancialTransaction> financialTransactions) {
		this.financialTransactions = financialTransactions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty("fundsType")
	public EFundsType getEFundsType() {
		return this.eFundsType;
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
		return this.getFinancialTransactions().stream().map(FinancialTransaction::getTotalPaid).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(this.getEFundsType().getName()).append(": ").append(this.getDescription());
		stringBuffer.append(" Current Balance: ").append(this.getCurrentBalance());
		return stringBuffer.toString();
	}

}
