package entities;

import java.io.Serializable;
import java.sql.Date;

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
	@NamedQuery(name="getAllRequests", query="FROM Request r ORDER BY r.status DESC"),
	@NamedQuery(name="getRequestsByDate", query="FROM Request r WHERE r.date = :date"),
	@NamedQuery(name="getRequestsByStatus", query="FROM Request r WHERE r.status = :status"),
	@NamedQuery(name="getRequestsByUser", query="SELECT r FROM Request r INNER JOIN r.user u WHERE u.username=:username"),
	@NamedQuery(name="getRequestsByAsset", query="FROM Request r WHERE r.asset.idAsset = :idAsset"),
	@NamedQuery(name="getNewRequestByUserAndAsset", query="FROM Request r WHERE status='New' and r.user.idUser = :idUser and r.asset.idAsset = :idAsset"),
	@NamedQuery(name="rejectRequestsByIdAsset", query="UPDATE Request r SET r.status = 'Rejected' WHERE r.status = 'New' AND r.asset.idAsset = :idAsset")
})
@Table(name = "REQUESTS")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idRequest;
	private Date date;
	private String status;

	private User user;
	private Asset asset;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_REQUEST")
	public long getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(long idRequest) {
		this.idRequest = idRequest;
	}

	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@ManyToOne
	@JoinColumn(name = "ID_ASSET")
	@JsonIgnore
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (idRequest ^ (idRequest >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idRequest != other.idRequest)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [idRequest=" + idRequest + ", date=" + date + ", status=" + status + "]";
	}

	
	
}
