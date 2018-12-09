package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllProducts", query="FROM Product p ORDER BY p.productId"),
	@NamedQuery(name="getProductsByType", query="FROM Product p WHERE p.type = :type"),
	@NamedQuery(name="getProductsByName", query="FROM Product p WHERE p.name = :name"),
	@NamedQuery(name="getProductsByDescription", query="FROM Product p WHERE p.description = :description"),
	@NamedQuery(name="getProductsByPrice", query="FROM Product p WHERE p.price = :price"),
	@NamedQuery(name="getProductsByPrice2", query="FROM Product p WHERE p.price2 = :price2")
})
@Table(name = "Products")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 8679802599881027433L;
	
	private long productId;
	private String type;
	private String name;
	private String description;
	private int price;
	private int price2;
	private List<Quote> quotes;
	
	public Product() {
	}

	public Product(String type, String name, String description, int price, int price2) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.price2 = price2;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PRODUCT")
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRICE")
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "PRICE2")
	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	
	@ManyToMany(mappedBy = "products")
	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		result = prime * result + price2;
		result = prime * result + (int) (productId ^ (productId >>> 32));
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
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		if (price2 != other.price2)
			return false;
		if (productId != other.productId)
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
		return "Product [productId=" + productId + ", type=" + type + ", name=" + name + ", description=" + description + ", price=" + price + ", price2=" + price2 + "]";
	}
}
