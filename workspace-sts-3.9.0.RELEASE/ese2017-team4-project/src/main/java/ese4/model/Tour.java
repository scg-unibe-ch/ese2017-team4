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

/**
 * Contains one or more packages.
 * Has methods to see where the next deliveryAddress is.
 * 
 * @author ese4
 *
 */
@Entity
public class Tour {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="driverId")
	private User driver;
	
	@OneToMany(mappedBy="tour")
	private List<Package> packages;
	
	@Transient
	private int numberDeliveredPacks;	//TODO CHANGE
	
	private boolean isFinished;			//true if the tour is finished aka all packages are delivered
	
	public Tour() {
		isFinished=false;
		numberDeliveredPacks=0;
	}
	
	/**
	 * Constructor of a tour. Takes its packages and driver as
	 * parameter and assigns them.
	 * 
	 * @param packages
	 * @param driver The driver which is responsible for this tour
	 */
	public Tour(List<Package> packages, User driver) {
		this.packages = packages;
		this.driver = driver;
		isFinished=false;
		numberDeliveredPacks=0;
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
	 * 
	 * @param driver
	 */
	public void setDriver(User driver)
	{
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
	 * 
	 * @param packages
	 */
	public void setPacks(List<Package> packages)
	{
		this.packages = packages;
	}
	/**
	 * Returns its id.
	 * 
	 * @return id
	 */
	public int getId()
	{
		return this.id;
	}
	/**
	 * Sets its parameter input as id.
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
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
	 * Gets address of next package to be delivered.
	 * 
	 * TODO: use later 
	 * 
	 * @return the next address
	 */
	public String getNextAddress()
	{
		String address;
		if(isFinished == false)
			address = packages.get(numberDeliveredPacks).getAddress();
		else
			address = "No more Packages to deliver, go home";
		return address;
	}
	
	/**
	 * Sets the selected Package to delivered.
	 * 
	 * TODO: use later
	 * 
	 * @param package that was delivered
	 */
	public void setPackageToDelivered(Package pack)
	{
		pack.setToDelivered();
		numberDeliveredPacks++;
		if(numberDeliveredPacks == packages.size()) {
			setFinished();
		}
	}
	
	/**
	 * Sets boolean isFinished to true.
	 */
	public void setFinished()
	{
		isFinished = true;
	}
	
}
