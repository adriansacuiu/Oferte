package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllQuotes", query="FROM Quote q ORDER BY q.quoteId"),
	@NamedQuery(name="getQuoteByClientId", query="FROM Quote q WHERE q.clientId = :clientId"),
	@NamedQuery(name="getQuoteByUserId", query="FROM Quote q WHERE q.userId = :userId")
})
@Table(name = "Quotes")
public class Quote implements Serializable {

	private static final long serialVersionUID = -1542075655513067234L;
	
	private long quoteId;
	private long clientId;
	private String request;
	private long createdBy;
	private List<Product> products;
	
	public Quote() {
	}
	
	public Quote(long clientId, String request, long createdBy) {
		this.clientId = clientId;
		this.request = request;
		this.createdBy = createdBy;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_QUOTE")
	public long getQuoteId() {
		return quoteId;
	}
	
	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT")
	@JsonIgnore
	public long getClientId() {
		return clientId;
	}
	
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	@Column(name = "REQUEST")
	public String getRequest() {
		return request;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
	
	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	@JsonIgnore
	public long getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(long userId) {
		this.createdBy = userId;
	}
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Quote_Products", 
        joinColumns = { @JoinColumn(name = "ID_QUOTE") }, 
        inverseJoinColumns = { @JoinColumn(name = "ID_PRODUCT") }
    )
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + (int) (quoteId ^ (quoteId >>> 32));
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + (int) (createdBy ^ (createdBy >>> 32));
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
		Quote other = (Quote) obj;
		if (clientId != other.clientId)
			return false;
		if (quoteId != other.quoteId)
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (createdBy != other.createdBy)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Quote [quoteId=" + quoteId + ", clientId=" + clientId + ", request=" + request + ", createdBy=" + createdBy + "]";
	}
}
