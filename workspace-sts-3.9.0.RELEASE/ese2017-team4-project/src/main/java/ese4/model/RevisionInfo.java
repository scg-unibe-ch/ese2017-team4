package ese4.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import ese4.listener.RevListener;

/**
 * 
 * @author michael.roetheli
 * Saves the time, revision number and the currently logged in user of a specific revision.
 *
 */
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
	private Date revtstmp;
	 
	@Column(name="revuser")
	private String revuser;
	
	public String getRevuser() {
		return revuser;
	}

	public void setUserName(String userName) {
		this.revuser = userName;
	}
	
	public Date getRevtstmp()
	{
		return revtstmp;
	}
}
