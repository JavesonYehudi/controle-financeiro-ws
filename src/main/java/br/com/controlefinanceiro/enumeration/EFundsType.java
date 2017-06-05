package br.com.controlefinanceiro.enumeration;

public enum EFundsType {
	BANK_ACCOUNT(1, "Bank Account"), CREDIT_CARD(2, "Credit Card"), DEFAULT(0, "Default");

	private int id;
	private String name;

	private EFundsType(int id, String name) {
		this.id = id;
		this.setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}