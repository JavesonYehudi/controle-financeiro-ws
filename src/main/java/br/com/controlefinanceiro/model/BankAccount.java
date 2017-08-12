package br.com.controlefinanceiro.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controlefinanceiro.enumeration.EFundsType;

public class BankAccount extends Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4435628215627819990L;
	private String account;
	private String agency;

	public BankAccount() {
		fundsType = EFundsType.BANK_ACCOUNT.getId();
	}

	public BankAccount(String description) {
		super(description);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@JsonProperty("fundsType")
	@Override
	public int getFundsType() {
		return this.fundsType;
	}
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer
		.append("account: ").append(account)
		.append(", agency: ").append(agency);

		return stringBuffer.toString();
	}
}