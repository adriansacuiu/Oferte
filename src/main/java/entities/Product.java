package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllProducts", query="FROM Product p ORDER BY p.productId"),
	@NamedQuery(name="getProductsByModel", query="FROM Product p WHERE p.model = :model"),
	@NamedQuery(name="getProductsByModelType", query="FROM Product p WHERE p.modelType = :modelType"),
	@NamedQuery(name="getProductsByPower", query="FROM Product p WHERE p.power = :power"),
	@NamedQuery(name="getProductsByNumberOfConnectors", query="FROM Product p WHERE p.numberOfConnectors = :numberOfConnectors"),
	@NamedQuery(name="getProductsByConnectorType", query="FROM Product p WHERE p.connectorType = :connectorType"),
	@NamedQuery(name="getProductsByPrice", query="FROM Product p WHERE p.price = :price"),
	@NamedQuery(name="getProductsByPrice2", query="FROM Product p WHERE p.price2 = :price2")
})
@Table(name = "Products")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 8679802599881027433L;
	
	private String productId;
	private String model;
	private String modelType;
	private double power;
	private int numberOfConnectors;
	private String connectorType;
	private int price;
	private int price2;
	private List<Quote> quotes;
	
	public Product() {
	}

	public Product(String productId, String model, String modelType, double power, int numberOfConnectors, String connectorType, int price, int price2, List<Quote> quotes) {
		this.productId = productId;
		this.model = model;
		this.modelType = modelType;
		this.power = power;
		this.numberOfConnectors = numberOfConnectors;
		this.connectorType = connectorType;
		this.price = price;
		this.price2 = price2;
		this.quotes = quotes;
	}

	@Id
	@Column(name = "ID_PRODUCT")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "MODEL_TYPE")
	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	@Column(name = "POWER")
	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	@Column(name = "NUMBER_OF_CONNECTORS")
	public int getNumberOfConnectors() {
		return numberOfConnectors;
	}

	public void setNumberOfConnectors(int numberOfConnectors) {
		this.numberOfConnectors = numberOfConnectors;
	}

	@Column(name = "CONNECTOR_TYPE")
	public String getConnectorType() {
		return connectorType;
	}

	public void setConnectorType(String connectorType) {
		this.connectorType = connectorType;
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
		result = prime * result + ((connectorType == null) ? 0 : connectorType.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((modelType == null) ? 0 : modelType.hashCode());
		result = prime * result + numberOfConnectors;
		long temp;
		temp = Double.doubleToLongBits(power);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + price;
		result = prime * result + price2;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((quotes == null) ? 0 : quotes.hashCode());
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
		if (connectorType == null) {
			if (other.connectorType != null)
				return false;
		} else if (!connectorType.equals(other.connectorType))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (modelType == null) {
			if (other.modelType != null)
				return false;
		} else if (!modelType.equals(other.modelType))
			return false;
		if (numberOfConnectors != other.numberOfConnectors)
			return false;
		if (Double.doubleToLongBits(power) != Double.doubleToLongBits(other.power))
			return false;
		if (price != other.price)
			return false;
		if (price2 != other.price2)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (quotes == null) {
			if (other.quotes != null)
				return false;
		} else if (!quotes.equals(other.quotes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", model=" + model + ", modelType=" + modelType + ", power=" + power + ", numberOfConnectors=" + numberOfConnectors + ", connectorType="
				+ connectorType + ", price=" + price + ", price2=" + price2 + ", quotes=" + quotes + "]";
	}
}
