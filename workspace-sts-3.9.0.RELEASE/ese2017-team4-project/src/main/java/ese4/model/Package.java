package ese4.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ese4.model.Status;

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
	
	private double weight;
	private double height;
	private double length;
	private double width;
	
	@Transient
	private Status isDelivered;
	
	public String isStatus;
	private int expectedDeliveryTime; //expected time in minutes???
	
	
	/**
	 * Constructor of a package. Takes its address, expected delivery time and content as
	 * parameter and assigns them. Sets the boolean isDelivered to false.
	 * 
	 * @param address
	 * @param expectedDeliveryTime 
	 * @param content
	 */
	public Package(String address, int expectedDeliveryTime, double weight, double height, double length, double width) 
	{
		this.address = address;
		this.expectedDeliveryTime = expectedDeliveryTime;
		this.weight = weight;
		this.height = height;
		this.length = length;
		this.width = width;
		this.isDelivered = Status.PENDANT;		//should not be delivered on creation
	}
	
	public Package()
	{
		this.isDelivered = Status.PENDANT;
		this.expectedDeliveryTime = 0;
		this.isStatus = this.isDelivered.getDisplayName();
	}
	
	/**
	 * Returns its id.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setWidth(double width) {
		this.width = width;
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
	 * Returns its delivery status.
	 * 
	 * @return isDeliverd
	 */
	public Status getIsDelivered() {
		return isDelivered;
	}
	/**
	 * Sets its parameter input as isDelivered.
	 * 
	 * @param isDeliverd
	 */
	public void setToDelivered() {
		isDelivered = Status.ZUGESTELLT;
		this.isStatus = this.isDelivered.getDisplayName();
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
	
	public String toString() {
		return this.getId().toString();
	}
	
	public void placedInTour() {
		this.isDelivered = Status.GEPLANT;
		this.isStatus = this.isDelivered.getDisplayName();
	}
}
