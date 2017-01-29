package pos.estacio.projeto_final.enumeration;

public enum EFundsType {
	BANK_ACCOUNT("Bank Account"), CREDIT_CARD("Credit Card");
	private String name;

	private EFundsType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}