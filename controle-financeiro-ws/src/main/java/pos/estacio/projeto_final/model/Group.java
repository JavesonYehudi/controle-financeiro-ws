package pos.estacio.projeto_final.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "group", schema = "financeiro")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7026294608513613215L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String description;

	@OneToMany(mappedBy = "group")
	private List<FinancialTransaction> financialTransactions;

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

	@JsonIgnore
	public List<FinancialTransaction> getFinancialTransactions() {
		return financialTransactions;
	}

	public void setFinancialTransactions(List<FinancialTransaction> financialTransactions) {
		this.financialTransactions = financialTransactions;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}
