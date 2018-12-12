package entities.DTO;

import java.io.Serializable;
import java.util.List;

import entities.Car;
import entities.Client;
import entities.Quote;

public class Request implements Serializable {
	private static final long serialVersionUID = -3534751870056158197L;
	
	private Client client;
	private Quote quote;
	private long userId;
	private Car car;
	private RequestType requestType;
	private List<String> productIds;
	
	public Request() {
	}
	
	public Request(Client client, Quote quote, long userId, Car car, RequestType requestType, List<String> productIds) {
		super();
		this.client = client;
		this.quote = quote;
		this.userId = userId;
		this.car = car;
		this.requestType = requestType;
		this.productIds = productIds;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public List<String> getProducts() {
		return productIds;
	}

	public void setProducts(List<String> productIds) {
		this.productIds = productIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((productIds == null) ? 0 : productIds.hashCode());
		result = prime * result + ((quote == null) ? 0 : quote.hashCode());
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		Request other = (Request) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (productIds == null) {
			if (other.productIds != null)
				return false;
		} else if (!productIds.equals(other.productIds))
			return false;
		if (quote == null) {
			if (other.quote != null)
				return false;
		} else if (!quote.equals(other.quote))
			return false;
		if (requestType != other.requestType)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [client=" + client + ", quote=" + quote + ", userId=" + userId + ", car=" + car + ", requestType=" + requestType + ", productIds=" + productIds + "]";
	}
}
