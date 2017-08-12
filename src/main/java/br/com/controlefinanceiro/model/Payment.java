package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 180233392066903390L;
	@JsonSerialize(using = NoObjectIdSerializer.class)
	private ObjectId id;
	private BigDecimal value;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	public Payment() {
		this.setId(new ObjectId());
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append(", Data do pagamento: ").append(this.getDate().toString());
		stringBuffer.append(", Valor do pagamento: ").append(this.getValue());
		return stringBuffer.toString();
	}
}