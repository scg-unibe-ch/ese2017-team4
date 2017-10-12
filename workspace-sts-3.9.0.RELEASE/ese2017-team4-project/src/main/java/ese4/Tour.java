package ese4;

import java.util.ArrayList;

/**
 * A Tour contains one or more Package.
 * Has methods to see where the next deliveryAddress is.
 * 
 * @author ese4
 *
 */
public class Tour {
	private ArrayList<Package> packs;
	private int numberDeliveredPacks;	//TODO CHANGE
	private boolean isFinished;			//true if the tour is finished aka all packages are delivered
	
	public Tour() {
		isFinished=false;
		packs = new ArrayList<Package>();
		numberDeliveredPacks=0;
	}
	
	/**
	 * the packages should be added in the order they have to be delivered.
	 * @param pack
	 */
	public void addPackageToTour(Package pack)
	{
		packs.add(pack);
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
			address = packs.get(numberDeliveredPacks).getAddress();
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
		if(numberDeliveredPacks == packs.size()) {
			setFinished();
		}
	}
	
	public void setFinished()
	{
		isFinished = true;
	}
	
}
