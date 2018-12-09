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
	@NamedQuery(name="getAllDatatrackings", query="FROM Datatracking d ORDER BY d.datatrackId"),
	@NamedQuery(name="getDatatrackingsByTargetTable", query="FROM Datatracking d WHERE d.targetTable = :targetTable"),
	@NamedQuery(name="getDatatrackingsByTargetId", query="FROM Datatracking d WHERE d.targetId = :targetId"),
	@NamedQuery(name="getDatatrackingsByActionType", query="FROM Datatracking d WHERE d.actionType = :actionType"),
	@NamedQuery(name="getDatatrackingsByMadeBy", query="FROM Datatracking d WHERE d.madeBy = :madeBy")
})
@Table(name = "Datatrackings")
public class Datatracking implements Serializable {

	private static final long serialVersionUID = -2511424920022822784L;
	
	private long datatrackingId;
	private String targetTable;
	private long targetId;
	private String actionType;
	private String description;
	private long madeBy;
	
	public Datatracking() {
	}

	public Datatracking(String targetTable, long targetId, String actionType, String description, long madeBy) {
		this.targetTable = targetTable;
		this.targetId = targetId;
		this.actionType = actionType;
		this.description = description;
		this.madeBy = madeBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_DATATRACKING")
	public long getDatatrackingId() {
		return datatrackingId;
	}

	public void setDatatrackingId(long datatrackingId) {
		this.datatrackingId = datatrackingId;
	}

	@Column(name = "TARGET_TABLE")
	public String getTargetTable() {
		return targetTable;
	}

	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	@Column(name = "TARGET_ID")
	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	@Column(name = "ACTION_TYPE")
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "MADE_BY")
	@JsonIgnore
	public long getMadeBy() {
		return madeBy;
	}

	public void setMadeBy(long madeBy) {
		this.madeBy = madeBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
		result = prime * result + (int) (datatrackingId ^ (datatrackingId >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (madeBy ^ (madeBy >>> 32));
		result = prime * result + (int) (targetId ^ (targetId >>> 32));
		result = prime * result + ((targetTable == null) ? 0 : targetTable.hashCode());
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
		Datatracking other = (Datatracking) obj;
		if (actionType == null) {
			if (other.actionType != null)
				return false;
		} else if (!actionType.equals(other.actionType))
			return false;
		if (datatrackingId != other.datatrackingId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (madeBy != other.madeBy)
			return false;
		if (targetId != other.targetId)
			return false;
		if (targetTable == null) {
			if (other.targetTable != null)
				return false;
		} else if (!targetTable.equals(other.targetTable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Datatracking [datatrackingId=" + datatrackingId + ", targetTable=" + targetTable + ", targetId=" + targetId + ", actionType=" + actionType + ", description=" + description + ", madeBy="
				+ madeBy + "]";
	}
}
