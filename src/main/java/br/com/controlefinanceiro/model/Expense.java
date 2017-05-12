package br.com.controlefinanceiro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;

public class Expense extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097783993436489212L;

	public Expense() {
		eFinancialTransactionType = EFinancialTransactionType.EXPENSE;
	}
	
	@Override
	@JsonProperty("financialTransactionType")
	public EFinancialTransactionType getEFinancialTransactionType() {
		return EFinancialTransactionType.EXPENSE;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
