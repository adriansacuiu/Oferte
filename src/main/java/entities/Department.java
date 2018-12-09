package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllDepartments", query="FROM Department d ORDER BY d.name"),
	@NamedQuery(name="getDepartmentsByName", query="FROM Department d WHERE d.name = :name"),
	@NamedQuery(name="getDepartmentsByLocation", query="FROM Department d WHERE d.location = :location"),
	@NamedQuery(name="getDepartmentsByAddress", query="FROM Department d WHERE d.address = :address"),
	@NamedQuery(name="getDepartmentsByCountry", query="FROM Department d WHERE d.country.idCountry = :idCountry")
})
@Table(name = "DEPARTMENTS")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idDepartment;
	private String name;
	private String location;
	private String address;

	private Country country;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_DEPARTMENT")
	public long getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(long idDepartment) {
		this.idDepartment = idDepartment;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LOCATION")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn(name = "ID_COUNTRY")
	@JsonIgnore
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (idDepartment ^ (idDepartment >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (idDepartment != other.idDepartment)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [idDepartment=" + idDepartment + ", name=" + name + ", location=" + location + ", address=" + address + "]";
	}

}
