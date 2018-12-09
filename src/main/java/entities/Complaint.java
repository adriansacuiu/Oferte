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
	@NamedQuery(name="getAllComplaints", query="FROM Complaint c ORDER BY c.title DESC, c.priority DESC"),
	@NamedQuery(name="getComplaintsByTitle", query="FROM Complaint c WHERE c.title = :title"),
	@NamedQuery(name="getComplaintsByDescription", query="FROM Complaint c WHERE c.description = :description"),
	@NamedQuery(name="getComplaintsByPriority", query="FROM Complaint c WHERE c.priority = :priority"),
	@NamedQuery(name="getComplaintsByStatus", query="FROM Complaint c WHERE c.status = :status"),
	@NamedQuery(name="getComplaintsByUser", query="SELECT c FROM Complaint c INNER JOIN c.user u WHERE u.username=:username"),
	@NamedQuery(name="getComplaintsByAsset", query="FROM Complaint c WHERE c.asset.idAsset = :idAsset")
})
@Table(name = "COMPLAINTS")
public class Complaint implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idComplaint;
	private String title;
	private String description;
	private String priority;
	private String status;

	private User user;
	private Asset asset;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_COMPLAINT")
	public long getIdComplaint() {
		return idComplaint;
	}

	public void setIdComplaint(long idComplaint) {
		this.idComplaint = idComplaint;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRIORITY")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (idComplaint ^ (idComplaint >>> 32));
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Complaint other = (Complaint) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idComplaint != other.idComplaint)
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Complaint [idComplaint=" + idComplaint + ", title=" + title + ", description=" + description + ", priority=" + priority + ", status=" + status + "]";
	}

}
