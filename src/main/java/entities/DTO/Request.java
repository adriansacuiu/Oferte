package entities.DTO;

import java.io.Serializable;
import java.util.List;

import entities.Client;
import entities.Product;
import entities.Quote;

public class Request implements Serializable {
	private static final long serialVersionUID = -3534751870056158197L;
	
	private Client client;
	private Quote quote;
	private List<Product> products;
	
	public Request() {
	}
	
	public Request(Client client, Quote quote, List<Product> products) {
		this.client = client;
		this.quote = quote;
		this.products = products;
	}

	public Client getClient() {
		return client;
	}
	
	public Quote getQuote() {
		return quote;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((quote == null) ? 0 : quote.hashCode());
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
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (quote == null) {
			if (other.quote != null)
				return false;
		} else if (!quote.equals(other.quote))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [client=" + client + ", quote=" + quote + ", products=" + products + "]";
	}
}
