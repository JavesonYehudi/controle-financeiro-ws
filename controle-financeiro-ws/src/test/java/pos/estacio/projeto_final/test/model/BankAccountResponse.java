package pos.estacio.projeto_final.test.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4435628215627819990L;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("description")
	private String description;

	@JsonProperty("account")
	private String account;

	@JsonProperty("agency")
	private String agency;

	@JsonProperty("error")
	private String error;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
