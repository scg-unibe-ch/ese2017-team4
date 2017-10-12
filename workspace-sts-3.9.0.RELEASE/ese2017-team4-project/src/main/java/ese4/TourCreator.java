package ese4;
import java.util.ArrayList;

/**
 * is responsible for creating Tours.
 * 
 * @author ese4
 *
 */
public class TourCreator {
	Warehouse warehouse;
	
	public TourCreator(Warehouse warehouse)
	{
		this.warehouse = warehouse;
	}
	
	public void makeTours()
	{
		
	}
	
	/**
	 * TODO: atm takes first 5 packages from warhouse and puts them in the Tour for the specified driver
	 * 
	 * @param driver that will get the tour
	 */
	public void makeTour(Driver driver)	//atm a tour has max 5 packages
	{
		Package pack;
		Tour tour = new Tour();
		for(int i = 0; i < 5; i++)			
		{
			pack = warehouse.getPack(0);	//get the first pack
			tour.addPackageToTour(pack);	//add it to the tour
			warehouse.removePack(0); 		//remove the added package
			//do this 10 times	TODO: change maybe depending on size or whatever
		}
		
		
		
	}
}
