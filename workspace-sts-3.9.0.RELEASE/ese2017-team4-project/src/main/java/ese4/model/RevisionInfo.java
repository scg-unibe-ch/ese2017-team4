package ese4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import ese4.listener.RevListener;

@Entity
@RevisionEntity(RevListener.class)
@Table(name = "revinfo")
public class RevisionInfo {
	
	
	
	@Id
	@RevisionNumber
	@GeneratedValue
	@Column(name="rev")
	private Integer rev;
	 
	@RevisionTimestamp
	@Column(name="revtstmp")
	private long timestamp;
	 
	@Column(name="revuser")
	private String revisionUserName;
	 
	public String getUserName() {
		return revisionUserName;
	}

	public void setUserName(String userName) {
		this.revisionUserName = userName;
	}
	
	public long getRevisionTimestamp()
	{
		return timestamp;
	}
}
