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
	private Integer closingDate;
	private BigDecimal limitValue;

	public CreditCard() {
		super();
		this.fundsType = EFundsType.CREDIT_CARD.getId();
	}

	public CreditCard(String description, Integer maturity, BigDecimal limitValue) {
		super(description);
		this.fundsType = EFundsType.CREDIT_CARD.getId();
		this.maturity = maturity;
		this.limitValue = limitValue;
	}

	public Integer getMaturity() {
		return maturity;
	}

	public void setMaturity(Integer maturity) {
		this.maturity = maturity;
	}

	public Integer getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Integer closingDate) {
		this.closingDate = closingDate;
	}

	public BigDecimal getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(BigDecimal limitValue) {
		this.limitValue = limitValue;
	}

	@JsonProperty("fundsType")
	@Override
	public int getFundsType() {
		return this.fundsType;
	}
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(super.toString());
		stringBuffer.append(", Dia de fechamento: ").append(this.closingDate);
		stringBuffer.append(", Dia de vencimento: ").append(this.maturity);
		stringBuffer.append(", Limite da fatura: ").append(this.limitValue);
		return stringBuffer.toString();
	}

}