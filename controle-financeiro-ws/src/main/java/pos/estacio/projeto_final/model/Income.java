package pos.estacio.projeto_final.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;

@Entity
@Table(name = "income")
public class Income extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241377989916269665L;

	public EFinancialTransactionType geEFinancialTransactionType() {
		return EFinancialTransactionType.INCOME;
	}
}
