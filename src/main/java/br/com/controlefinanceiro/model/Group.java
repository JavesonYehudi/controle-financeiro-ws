package br.com.controlefinanceiro.model;

import java.io.Serializable;

public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7026294608513613215L;

	private String description;

	public Group() {
	}

	public Group(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}
