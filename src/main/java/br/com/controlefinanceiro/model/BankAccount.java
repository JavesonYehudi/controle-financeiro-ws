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
		eFundsType = EFundsType.BANK_ACCOUNT;
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
	public EFundsType getEFundsType() {
		return this.eFundsType;
	}
}