package br.com.controlefinanceiro.model;

import java.io.Serializable;

public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7026294608513613215L;

	private Integer id;
	private String description;

	public Group() {
	}

	public Group(String description) {
		this.description = description;
	}

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

	@Override
	public String toString() {
		return this.getDescription();
	}
}
