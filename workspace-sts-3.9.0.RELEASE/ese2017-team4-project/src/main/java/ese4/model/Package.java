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
 * Keeps track of its size and weight, where it has to be delivered and it's expected delivery time.
 * The Package which status its currently in.
 * 
 * @author ese4
 *  id Used to differentiate all the packets even if they have the same dimensions and 
 *  address
 *  tour Tour The tour which a package is designated to
 *  address String Where the package goes to
 *  weight, height, length, width how big the package is and how much it weights.
 *  isDelivered enum where the package currently stands
 *  isStatus String represantation of isDelivered
 *  expectedDeliveryTime Int Time in minutes we get from the logisticians experience
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
    
	@Length(max = 50, message = "*Adresse ist zu lange")
	private String address;
	
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double weight;
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double height;
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double length;
	@Min(value=0, message = "*Nur Positive Zahlen")
	private double width;

	private Status isDelivered;
	
	private int deliveryCounter;
	
	private int notDeliverableCounter;
	
	private String isStatus = "pendent";
	
	/**
	 * Constructor of a package. Takes its address, expected delivery time and content as
	 * parameter and assigns them. Sets the boolean isDelivered to false.
	 * 
	 * @param address
	 * @param expectedDeliveryTime 
	 * @param content
	 */
	public Package(String address, double weight, double height, double length, double width) 
	{
		this.address = address;
		this.weight = weight;
		this.height = height;
		this.length = length;
		this.width = width;
		this.isDelivered = Status.PENDENT;  
		this.isStatus = "pendent";
	}
	
	public Package()
	{
		this.isDelivered = Status.PENDENT;
	}
	
	/**
	 *Returns its weight
	 *
	 * @return weight
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/**
	 * Sets its parameter input as weight
	 * 
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * Returns its height
	 * 
	 * @return height
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Sets its parameter input as height
	 * 
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Returns its length
	 * 
	 * @return length
	 */
	public double getLength() {
		return this.length;
	}
	
	/**
	 * Sets its parameter input as length
	 * 
	 * @param length
	 */
	public void setLength(double length) {
		this.length = length;
	}
	
	/**
	 * Returns its width
	 * 
	 * @return width
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Sets its parameter input as width
	 * 
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
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
	 * 
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
	 * Returns its tour.
	 * 
	 * @return tour
	 */
	public Tour getTour() {
		return this.tour;
	}
	
	/**
	 * Sets its parameter input as tour.
	 * 
	 * @param tour
	 */
	public void setTour(Tour tour) {
		this.tour = tour;
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
	 * Sets isDelivered parameter to 'geplant'
	 * and updates isStatus
	 */
	public void placedInTour() {
		this.isDelivered = Status.GEPLANT;
		this.isStatus = this.isDelivered.getDisplayName();
	}
	
	/**
	 * Sets isDelivered parameter to 'zugestellt'
	 * and updates isStatus
	 */
	public void setToDelivered() {
		isDelivered = Status.ZUGESTELLT;
		this.isStatus = this.isDelivered.getDisplayName();
	}
	
	/**
	 * Sets status according to the current 'isDelivered' status
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.isDelivered = status;
		this.isStatus = this.isDelivered.getDisplayName();
	}
	
	/**
	 * Returns its isStatus
	 * 
	 * @return isStatus
	 */
	public String getIsStatus() {
		return this.isStatus;
	}
	
	/**
	 * Returns its derliveryCounter
	 * 
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
	 * 
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
