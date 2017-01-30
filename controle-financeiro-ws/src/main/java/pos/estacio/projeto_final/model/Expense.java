package pos.estacio.projeto_final.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;

@Entity
public class Expense extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097783993436489212L;

	@Override
	@JsonProperty("financialTransactionType")
	public EFinancialTransactionType getEFinancialTransactionType() {
		return EFinancialTransactionType.EXPENSE;
	}
}
