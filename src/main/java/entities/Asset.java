package entities;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllAssets", query="FROM Asset a ORDER BY a.isAvailable"),
	@NamedQuery(name="getAvailableAssets", query="FROM Asset a WHERE a.isAvailable = true"),
	@NamedQuery(name="getAssetsByName", query="FROM Asset a WHERE a.name = :name"),
	@NamedQuery(name="getAssetsByType", query="FROM Asset a WHERE a.type = :type"),
	@NamedQuery(name="getAssetsByIsAvailable", query="FROM Asset a WHERE a.isAvailable = :isAvailable"),
	@NamedQuery(name="getAssetsByUser", query="SELECT a FROM Asset a INNER JOIN a.user u WHERE u.username=:username")
})
@Table(name = "ASSETS")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idAsset;
	private String name;
	private String type;
	private boolean isAvailable;

	private User user;

	private List<Complaint> complaints;
	private List<Request> requests;
	private List<Transaction> transactions;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ASSET")
	public Long getIdAsset() {
		return idAsset;
	}

	public void setIdAsset(Long idAsset) {
		this.idAsset = idAsset;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "IS_AVAILABLE")
	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "asset")
	@JsonIgnore
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "asset")
	@JsonIgnore
	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "asset")
	@JsonIgnore
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAsset == null) ? 0 : idAsset.hashCode());
		result = prime * result + (isAvailable ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		if (idAsset == null) {
			if (other.idAsset != null)
				return false;
		} else if (!idAsset.equals(other.idAsset))
			return false;
		if (isAvailable != other.isAvailable)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asset [idAsset=" + idAsset + ", name=" + name + ", type=" + type + ", isAvailable=" + isAvailable + "]";
	}


}
