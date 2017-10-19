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
 * A Tour contains one or more Package.
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
	
	public User getDriver()
	{
		return this.driver;
	}
	public void setDriver(User driver)
	{
		this.driver = driver;
	}
	public List<Package> getPacks() {
		return this.packages;
	}
	public void setPacks(List<Package> packages)
	{
		this.packages = packages;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * the packages should be added in the order they have to be delivered.
	 * @param pack
	 */
	public void addPackageToTour(Package pack)
	{
		packages.add(pack);
	}
	
	/**
	 * TODO::should go through the packages of the tour and shows the address of the first package that is not yet delivered
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
	 * sets the selected Package to delivered
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
	
	public void setFinished()
	{
		isFinished = true;
	}
	
}
