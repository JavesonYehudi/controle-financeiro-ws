package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(catalog = "blumar", schema = "financeiro", name = "credit_card")
public class CreditCard extends Funds implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3219833919121792274L;

	@Column(nullable = false)
	@JsonProperty("maturity")
	private Integer maturity;

	@Column(nullable = false)
	@JsonProperty("limitValue")
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

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("Credit Card: ");
		stringBuffer.append(super.getDescription());
		return stringBuffer.toString();
	}
}