package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controlefinanceiro.enumeration.EFundsType;
import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

@Entity(noClassnameStored = true, value = "funds")
@JsonIgnoreProperties(ignoreUnknown = true)
@Indexes({ @Index("user") })
public class Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3769024446632244523L;

	@Id
	@JsonSerialize(using = NoObjectIdSerializer.class)
	protected ObjectId id;
	protected String description;
	@JsonBackReference
	protected List<FinancialTransaction> financialTransactions;
	@Embedded
	protected User user;
	protected int fundsType;

	public Funds() {
		financialTransactions = new ArrayList<>();
		fundsType = EFundsType.DEFAULT.getId();
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
	public int getFundsType() {
		return this.fundsType;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(this.getFundsType()).append(": ").append(this.getDescription());
		return stringBuffer.toString();
	}

}
