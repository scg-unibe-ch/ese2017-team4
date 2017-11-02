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
 *  id Used to differentiate all the packets even if they have the same contents and 
 *  address
 *  tour Tour The tour which a package is designated to
 *  address String Where the package goes to
 *  content String What is inside the package
 *  isDelivered Boolean whether the package got delivered or not
 *  expectedDeliveryTime Int Time in minutes we get from the logisticians experience
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
	
	
	/**
	 * Constructor of a package. Takes its address, expected delivery time and content as
	 * parameter and assigns them. Sets the boolean isDelivered to false.
	 * 
	 * @param address
	 * @param expectedDeliveryTime 
	 * @param content
	 */
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
	
	
	/**
	 * Returns its id.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * Sets its parameter input as id.
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Returns its address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Sets its parameter input as address.
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Returns its content.
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * Sets its parameter input as content.
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Returns its delivery status.
	 * 
	 * @return isDeliverd
	 */
	public boolean getIsDelivered() {
		return isDelivered;
	}
	/**
	 * Sets its parameter input as isDelivered.
	 * 
	 * @param isDeliverd
	 */
	public void setToDelivered() {
		isDelivered = true;
	}
	
	/**
	 * Returns its expected delivery time.
	 * 
	 * @return expectedDeliveryTime
	 */
	public int getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}
	/**
	 * Sets its parameter input as expectedDeliveryTime.
	 * 
	 * @param expectedDeliveryTime
	 */
	public void setExpectedDeliveryTime(int expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}
	
	/**
	 * Returns its tour.
	 * 
	 * @return tour
	 */
	public Tour getTour() {
		return this.tour;
	}
	/**
	 * Sets its parameter input as tour.
	 * @param tour
	 */
	public void setTour(Tour tour) {
		this.tour = tour;
	}
}
