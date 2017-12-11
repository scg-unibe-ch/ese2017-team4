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
import javax.validation.constraints.Min;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import ese4.model.Status;

/**
 * Keeps track of its height, length, width and weight, where it has to be delivered.
 * The Package knows in which status its currently in, how many times it got tried to deliver and how many and
 * if the receiver didn't got the package
 * 
 * @author ese4
 * id Used to differentiate all the packets even if they have the same dimensions and 
 * Tour shows the tour in which the package is delivered
 * address String Where the package goes to
 * weight, height, length, width how big the package is and how much it weights.
 * isDelivered enum in which status the package currently locates
 *
 */
@Entity
@Audited
public class Package {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "tourId")
    private Tour tour;
    
	@Length(min =1, max = 50, message = "*Minimum 1 Zeichen, Maximum 50 Zeichen")
	private String address;
	
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double weight;
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double height;
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double length;
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double width;

	private Status status;
	
	private int deliveryCounter;
	
	private int notDeliverableCounter;
	
	private String statusDisplay;
	
	/**
	 * Constructor of a package. Takes its address and dimensions,
	 * Sets the Status "pendent" and creates a string-representation
	 * 
	 * @param address
	 * @param weight
	 * @param height
	 * @param length
	 * @param width
	 */
	public Package(String address, double weight, double height, double length, double width) 
	{
		this.address = address;
		this.weight = weight;
		this.height = height;
		this.length = length;
		this.width = width;
		this.status = Status.PENDENT;  
		this.statusDisplay = status.getDisplayName();
	}
	
	/**
	 * Default constructor if a package is create without any input
	 */
	public Package()
	{
		this.status = Status.PENDENT;
		this.statusDisplay = status.getDisplayName();
	}
	
	/**
	 * Returns its weight
	 * @return weight
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/**
	 * Sets its parameter input as weight
	 * Is bigger than 0
	 * @param weight
	 */
	public void setWeight(double weight) {
		assert(weight > 0);
		this.weight = weight;
	}
	
	/**
	 * Returns its height
	 * @return height
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Sets its parameter input as height
	 * Is bigger than 0
	 * @param height
	 */
	public void setHeight(double height) {
		assert(height > 0);
		this.height = height;
	}
	
	/**
	 * Returns its length
	 * @return length
	 */
	public double getLength() {
		return this.length;
	}
	
	/**
	 * Sets its parameter input as length
	 * Is bigger than 0
	 * @param length
	 */
	public void setLength(double length) {
		assert(length > 0);
		this.length = length;
	}
	
	/**
	 * Returns its width
	 * @return width
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Sets its parameter input as width
	 * Is bigger than 0
	 * @param width
	 */
	public void setWidth(double width) {
		assert(width > 0);
		this.width = width;
	}
	
	/**
	 * Returns its id.
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
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets its parameter input as address.
	 * @param address
	 */
	public void setAddress(String address) {
		assert(address.length() > 0);
		this.address = address;
	}
	
	/**
	 * Returns its tour.
	 * Can be null if no tour has been assigned
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
	
	/**
	 * Returns its delivery status.
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Sets status according to current situation of the package
	 * Is a valid status according to the enum
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
		this.setStatusDisplay(status);
	}
	
	/**
	 * Sets status parameter to 'geplant'
	 * and updates status
	 */
	public void placedInTour() {
		this.status = Status.GEPLANT;
		this.setStatusDisplay(status);
	}
	
	/**
	 * Sets status parameter to 'zugestellt'
	 * and updates status
	 */
	public void setToDelivered() {
		status = Status.ZUGESTELLT;
		this.setStatusDisplay(status);
	}
	
	/**
	 * Returns its status
	 * @return status
	 */
	public String getStatusDisplay() {
		return this.statusDisplay;
	}
	
	/**
	 * Updates statusDisplay according to the current status of the package
	 * @param status
	 */
	private void setStatusDisplay(Status status) {
		statusDisplay = status.getDisplayName();
	}
	
	/**
	 * Returns its derliveryCounter
	 * @return deliveryCounter
	 */
	public int getDeliveryCounter()
	{
		return deliveryCounter;
	}
	
	/**
	 * Increments deliveryCounter by 1
	 */
	public void incrementDeliveryCounter()
	{
		deliveryCounter++;
	}
	
	/**
	 * Returns its notDeliverableCounter
	 * @return notDeliverableCounter;
	 */
	public int getNotDeliverableCounter()
	{
		return notDeliverableCounter;
	}
	
	/**
	 * Increments notDeliverableCounter by one
	 */
	public void incrementNotDeliverableCounter()
	{
		notDeliverableCounter++;
	}
}
