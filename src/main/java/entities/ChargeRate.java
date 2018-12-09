package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllChargeRates", query="FROM ChargeRates c ORDER BY c.chargeRate"),
	@NamedQuery(name="getChargeRateByQuoteId", query="FROM ChargeRates c WHERE c.chargeRate = :chargeRate")
})
public class ChargeRate implements Serializable {

	private static final long serialVersionUID = -749229385033781297L;
	
	private int chargeRateId;
	private int chargeRate;
	private List<Car> cars;
	
	public ChargeRate() {
	}

	public ChargeRate(int chargeRateId, int chargeRate) {
		this.chargeRateId = chargeRateId;
		this.chargeRate = chargeRate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CHARGE_RATE")
	public int getChargeRateId() {
		return chargeRateId;
	}

	public void setChargeRateId(int chargeRateId) {
		this.chargeRateId = chargeRateId;
	}

	@Column(name = "CHARGE_RATE")
	public int getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(int chargeRate) {
		this.chargeRate = chargeRate;
	}

	@ManyToMany(mappedBy = "chargeRates")
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chargeRate;
		result = prime * result + chargeRateId;
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
		ChargeRate other = (ChargeRate) obj;
		if (chargeRate != other.chargeRate)
			return false;
		if (chargeRateId != other.chargeRateId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChargeRates [chargeRateId=" + chargeRateId + ", chargeRate=" + chargeRate + "]";
	}
}
