package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pos.estacio.projeto_final.enumeration.EFundsType;

@Entity
@Table(name = "credit_card", schema = "financeiro")
public class CreditCard extends Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3219833919121792274L;

	@Column(nullable = false)
	private Integer maturity;

	@Column(nullable = false)
	private BigDecimal limitValue;

	public CreditCard() {
	}

	public CreditCard(String description, Integer maturity, BigDecimal limitValue) {
		super(description);
		this.maturity = maturity;
		this.limitValue = limitValue;
	}

	public Integer getMaturity() {
		return maturity;
	}

	public void setMaturity(Integer maturity) {
		this.maturity = maturity;
	}

	public BigDecimal getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(BigDecimal limitValue) {
		this.limitValue = limitValue;
	}

	public EFundsType getEFundsType() {
		return EFundsType.CREDIT_CARD;
	}
}