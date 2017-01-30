package pos.estacio.projeto_final.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import pos.estacio.projeto_final.enumeration.EFinancialTransactionType;

@Entity
public class Income extends FinancialTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241377989916269665L;

	@Override
	@JsonProperty("financialTransactionType")
	public EFinancialTransactionType getEFinancialTransactionType() {
		return EFinancialTransactionType.INCOME;
	}
}
