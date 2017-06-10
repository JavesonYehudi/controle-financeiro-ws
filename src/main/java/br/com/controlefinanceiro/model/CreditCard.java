package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controlefinanceiro.enumeration.EFundsType;

public class CreditCard extends Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3219833919121792274L;

	private Integer maturity;
	private BigDecimal limitValue;

	public CreditCard() {
		super();
		this.eFundsType = EFundsType.CREDIT_CARD.getId();
	}

	public CreditCard(String description, Integer maturity, BigDecimal limitValue) {
		super(description);
		this.eFundsType = EFundsType.CREDIT_CARD.getId();
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

	@JsonProperty("fundsType")
	@Override
	public int getEFundsType() {
		return this.eFundsType;
	}

}