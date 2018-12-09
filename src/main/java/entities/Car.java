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
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllCars", query="FROM Car c ORDER BY c.brand"),
	@NamedQuery(name="getCarByBrand", query="FROM Car c WHERE c.brand = :brand"),
	@NamedQuery(name="getCarByModel", query="FROM Car c WHERE c.model = :model")
})
@Table(name = "Cars")
public class Car implements Serializable {

	private static final long serialVersionUID = 1211379791182576495L;
	
	private long carId;
	private String brand;
	private String model;
	private String link;
	private List<ChargeRate> chargeRates;
	
	public Car() {
	}

	public Car(String brand, String model, String link) {
		this.brand = brand;
		this.model = model;
		this.link = link;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CAR")
	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	@Column(name = "BRAND")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name = "LINK")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Car_Charge_Rates", 
        joinColumns = { @JoinColumn(name = "ID_CAR") }, 
        inverseJoinColumns = { @JoinColumn(name = "ID_CHARGE_RATE") }
    )
	public List<ChargeRate> getChargeRates() {
		return chargeRates;
	}

	public void setChargeRates(List<ChargeRate> chargeRates) {
		this.chargeRates = chargeRates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + (int) (carId ^ (carId >>> 32));
		result = prime * result + ((chargeRates == null) ? 0 : chargeRates.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		Car other = (Car) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (carId != other.carId)
			return false;
		if (chargeRates == null) {
			if (other.chargeRates != null)
				return false;
		} else if (!chargeRates.equals(other.chargeRates))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", brand=" + brand + ", model=" + model + ", link=" + link + ", chargeRates=" + chargeRates + "]";
	}
}
