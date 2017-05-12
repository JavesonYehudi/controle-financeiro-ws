package br.com.controlefinanceiro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;

public class Income extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241377989916269665L;

	public Income() {
		eFinancialTransactionType = EFinancialTransactionType.INCOME;
	}
	
	@Override
	@JsonProperty("financialTransactionType")
	public EFinancialTransactionType getEFinancialTransactionType() {
		return EFinancialTransactionType.INCOME;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
