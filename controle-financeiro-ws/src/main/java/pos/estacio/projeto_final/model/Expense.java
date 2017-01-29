package pos.estacio.projeto_final.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;

@Entity
@Table(name = "expense")
public class Expense extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097783993436489212L;

	public EFinancialTransactionType geEFinancialTransactionType() {
		return EFinancialTransactionType.EXPENSE;
	}
}
