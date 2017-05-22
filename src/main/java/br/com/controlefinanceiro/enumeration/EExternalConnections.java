package br.com.controlefinanceiro.enumeration;

public enum EExternalConnections {
	FACEBOOK("F"), GOOGLE("G");

	private String id;

	private EExternalConnections(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
