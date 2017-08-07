package br.com.controlefinanceiro.model;

import br.com.controlefinanceiro.enumeration.EFinancialTransactionType;

public class Expense extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097783993436489212L;

	public Expense() {
		financialTransactionType = EFinancialTransactionType.EXPENSE.ordinal();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
