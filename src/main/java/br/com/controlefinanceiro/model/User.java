package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

@Entity(noClassnameStored = true, value = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3311120569192779288L;

	@Id
	@JsonSerialize(using = NoObjectIdSerializer.class)
	private ObjectId id;
	private List<ExternalConnection> connections;
	private String name;
	private String email;
	private String login;
	private String password;
	@Transient
	private String token;

	public User() {
		this.connections = new ArrayList<>();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<ExternalConnection> getConnections() {
		return connections;
	}

	public void setConnections(List<ExternalConnection> connections) {
		this.connections = connections;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String pass) {
		this.password = pass;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void addConnection(ExternalConnection externalConnection) {
		this.getConnections().add(externalConnection);
	}

	@Override
	public String toString() {
		return this.login;
	}
}
