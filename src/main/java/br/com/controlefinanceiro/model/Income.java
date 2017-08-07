package br.com.controlefinanceiro.model;

import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;

public class Income extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241377989916269665L;

	public Income() {
		financialTransactionType = EFinancialTransactionType.INCOME.ordinal();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
