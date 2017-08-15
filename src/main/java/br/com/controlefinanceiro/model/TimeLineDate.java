package br.com.controlefinanceiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.controlefinanceiro.serializer.NoObjectIdSerializer;

public class TimeLineDate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4761472224089958636L;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private List<TimeLineDateItem> timeLineDateItems;

	public TimeLineDate(LocalDate date) {
		this.date = date;
		this.timeLineDateItems = new ArrayList<>();
	}
	
	public TimeLineDate(LocalDate date, List<TimeLineDateItem> transactions) {
		this.date = date;
		this.timeLineDateItems = transactions;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<TimeLineDateItem> getTimeLineDateItems() {
		return timeLineDateItems;
	}

	public void setTimeLineDateItems(List<TimeLineDateItem> timeLineDateItem) {
		this.timeLineDateItems = timeLineDateItem;
	}

	public void addTimeLineDateItem(TimeLineDateItem timeLineDateItem) {
		this.getTimeLineDateItems().add(timeLineDateItem);
	}

	@Override
	public boolean equals(Object obj) {
		return ((TimeLineDate) obj).getDate().equals(this.getDate()) ? true : super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Data: ").append(date.toString());
		stringBuffer.append(", TimeLineDateItems: ").append(timeLineDateItems);
		return stringBuffer.toString();
	}

	public static class TimeLineDateItem implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8235098279675518497L;
		@JsonSerialize(using = NoObjectIdSerializer.class)
		private ObjectId id;
		private String description;
		private BigDecimal value;
		private int type;

		public TimeLineDateItem(FinancialTransaction financialTransaction) {
			this.id = financialTransaction.getId();
			this.description = financialTransaction.getDescription();
			this.value = financialTransaction.getValueTransaction();
			this.type = financialTransaction.getFinancialTransactionType();
		}

		public TimeLineDateItem(ObjectId id, String description, BigDecimal value, int type) {	
			this.id = id;
			this.description = description;
			this.value = value;
			this.type = type;

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

		public BigDecimal getValue() {
			return value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		@Override
		public String toString() {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("id: ").append(id);
			stringBuffer.append(", descrição: ").append(description);
			stringBuffer.append(", valor: ").append(value);
			stringBuffer.append(", tipo: ").append(type);
			return stringBuffer.toString();
		}
	}
}
