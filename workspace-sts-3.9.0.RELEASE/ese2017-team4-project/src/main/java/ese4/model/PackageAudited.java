package ese4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.hibernate.validator.constraints.Length;

import ese4.model.Status;

@Entity
@Table(name = "package_aud")
public class PackageAudited {
    
    @Column(name="id")
    private Integer id;
    
    @Id
    @GeneratedValue
    @Column(name="rev")
    private Integer rev;
    
    @Column(name="revtype")
    private Integer revtype;
    
    @Column(name="tour_id")
    private Integer tourId;
    
    @Column(name="address")
	private String address;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="height")
	private double height;
	
	@Column(name="length")
	private double length;
	
	@Column(name="width")
	private double width;
	
	@Column(name="is_delivered")
	private Status isDelivered;
	
	@Column(name="delivery_counter")
	private int deliveryCounter;
	
	@Column(name="not_deliverable_counter")
	private int notDeliverableCounter;
	
	@Column(name="is_status")
	private String isStatus;
	
	
	public PackageAudited() 
	{
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getAddress() {
		return address;
	}

	public Status getIsDelivered() {
		return isDelivered;
	}
	
	public String getIsStatus() {
		return isDelivered.getDisplayName();
	}
	
	public int getDeliveryCounter()
	{
		return deliveryCounter;
	}
	
	public int getNotDeliverableCounter()
	{
		return notDeliverableCounter;
	}
	
	public int getRev() {
		return rev;
	}
	
	public int getRevtype() {
		return revtype;
	}
}
