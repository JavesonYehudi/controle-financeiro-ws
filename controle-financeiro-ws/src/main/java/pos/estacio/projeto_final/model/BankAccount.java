package pos.estacio.projeto_final.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(catalog = "blumar", schema = "financeiro", name = "bank_account")
public class BankAccount extends Funds implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4435628215627819990L;

	@JsonProperty("account")
	private String account;

	@JsonProperty("agency")
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

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("Bank Account: ");
		stringBuffer.append(super.getDescription());
		return stringBuffer.toString();
	}

}
