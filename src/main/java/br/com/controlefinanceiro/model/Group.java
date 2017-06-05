package br.com.controlefinanceiro.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7026294608513613215L;

	@JsonSerialize(using = NoObjectIdSerializer.class)
	private ObjectId id;
	private String description;

	public Group() {
	}

	public Group(String description) {
		this.description = description;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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
