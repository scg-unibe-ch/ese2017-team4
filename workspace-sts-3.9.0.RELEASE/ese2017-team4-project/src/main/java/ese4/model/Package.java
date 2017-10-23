package ese4.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Keeps track of it's content, where it has to be delivered and it's expected delivery time.
 * The Package knows if its delivered yet or not.
 * 
 * @author ese4
 * @Param id Used to differentiate all the packets even if they have the same contents and 
 * adress
 * @Param tour Tour The tour which a package is designated to
 * @Param address String Where the package goes to
 * @Param content String What is inside the package
 * @Param isDelivered Boolean wether the package got delivered or not
 * @Param expectedDeliveryTime Int Time in minutes we get from the logisticians experience
 *
 */
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "tourId")
    private Tour tour;
    
	private String address;
	private String content;
	
	
	
	private Boolean isDelivered;
	private int expectedDeliveryTime; //expected time in minutes?
	
	
	
	public Package(String address, int expectedDeliveryTime, String content) 
	{
		this.address = address;
		this.expectedDeliveryTime = expectedDeliveryTime;
		this.content = content;
		this.isDelivered = false;	//should not be delivered on creation
	}
	
	public Package()
	{
		this.isDelivered = false;
		this.expectedDeliveryTime = 0;
	}
	
	
	//setters - getters
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean getIsDelivered() {
		return isDelivered;
	}
	public void setToDelivered() {
		isDelivered = true;
	}
	
	public int getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}
	public void setExpectedDeliveryTime(int expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}
	
	public Tour getTour() {
		return this.tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}


}
