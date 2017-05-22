package br.com.controlefinanceiro.model;

import java.io.Serializable;

import br.com.controlefinanceiro.enumeration.EExternalConnections;

public class ExternalConnection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6946077664911159035L;
	private String id;
	private EExternalConnections type;

	public ExternalConnection() {
	}

	public ExternalConnection(String id, EExternalConnections type) {
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EExternalConnections getType() {
		return type;
	}

	public void setType(EExternalConnections type) {
		this.type = type;
	}
}