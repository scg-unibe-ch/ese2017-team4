package ese4.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

/**
 * Knows its Driver, if its finished, has a List of all packages it contains
 * and has a estimated Delivery Time
 * 
 * @author ese4
 * id is used to differentiate all the tours
 * estimatedDeliveryTime is based on the experience of the logistician
 * 
 */
@Entity
@Audited
public class Tour {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="driverId")
	private User driver;
	
	@OneToMany(mappedBy="tour")
	private List<Package> packages;
	
	private boolean isFinished;		
	
	private int estimatedDeliveryTime;
	
	/**
	 * Default constructor
	 * If a Tour is created without any input
	 */
	public Tour() {
		isFinished=false;
		packages = new ArrayList<Package>();
	}
	
	/**
	 * Constructor of a tour. Takes its packages and driver as
	 * parameter and assigns them.
	 * @param packages assigned to the tour
	 * @param driver which is responsible for this tour
	 */
	public Tour(List<Package> packages, User driver) {
		this.packages = packages;
		this.driver = driver;
		isFinished=false;
	}
	
	/**
	 * Returns its driver.
	 * @return driver
	 */
	public User getDriver()
	{
		return this.driver;
	}
	
	/**
	 * Sets its parameter input as driver.
	 * Is not null
	 * @param driver
	 */
	public void setDriver(User driver)
	{
		assert(driver != null);
		this.driver = driver;
	}
	
	/**
	 * Returns its packages.
	 * @return packages
	 */
	public List<Package> getPacks() {
		return this.packages;
	}
	
	/**
	 * Sets its parameter input as packages.
	 * @param packages
	 */
	public void setPacks(List<Package> packages)
	{
		this.packages = packages;
	}
	
	/**
	 * Returns its id.
	 * @return id
	 */
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * Sets its parameter input as id.
	 * Non negative integer
	 * @param id
	 */
	public void setId(int id)
	{
		assert(id >= 0);
		this.id = id;
	}
	
	/**
	 * Adds single packages to the list of packages.
	 * @param pack
	 */
	public void addPackageToTour(Package pack) 
	{
		packages.add(pack);
	}
		
	/**
	 * Sets boolean isFinished to true.
	 */
	public void setFinished()
	{
		isFinished = true;
	}
	
	/**
	 * Looks if the Tour is Finished
	 * @return isFinished
	 */
	public boolean getIsFinished() 
	{
		return isFinished;
	}
	
	/**
	 * Returns its estimatedDeliveryTime
	 * @return estimatedDeliveryTime
	 */
	public int getEstimatedDeliveryTime()
	{
		return estimatedDeliveryTime;
	}
	
	/**
	 * Sets its parameter input as estimatedDeliveryTime
	 * Positive integer
	 * @param time
	 */
	public void setEstimatedDeliveryTime(int time)
	{
		assert(time > 0);
		this.estimatedDeliveryTime = time;
	}
}
