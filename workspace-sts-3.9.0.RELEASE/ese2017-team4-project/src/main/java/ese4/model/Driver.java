package ese4.model;

/**
 * Has a name and has a tour.
 * There is one tour per day.
 * 
 * @author ese4
 * Driver is a type of person
 *
 */
public class Driver extends Person {
	private Tour tour;
	
	public Driver(String name, int id)
	{
		super(name, id);
	}
	
	/**
	 * each driver gets one tour every day
	 * 
	 * @param tour
	 */
	public void setTour(Tour tour)
	{
		this.tour = tour;
	}
	
	public Tour getTour()
	{
		return tour;
	}

}
