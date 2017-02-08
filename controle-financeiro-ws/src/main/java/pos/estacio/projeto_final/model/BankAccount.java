package pos.estacio.projeto_final.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import pos.estacio.projeto_final.enumeration.EFundsType;

@Entity
@Table(name = "bank_account", schema = "financeiro")
public class BankAccount extends Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4435628215627819990L;

	private String account;

	private String agency;

	public BankAccount() {
	}

	public BankAccount(String description) {
		super(description);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@JsonProperty("fundsType")
	public EFundsType getEFundsType() {
		return EFundsType.BANK_ACCOUNT;
	}
}