package ese4;

/**
 * Keeps track of it's content, where it has to be delivered and it's expected delivery time.
 * The Package knows if its delivered yet or not.
 * 
 * @author ese4
 *
 */
public class Package {
	private String address;
	private int expectedDeliveryTime; //expected time in minutes?
	private String content;
	private Boolean isDelivered;
	
	public Package(String address, int expectedDeliveryTime, String content) 
	{
		this.address = address;
		this.expectedDeliveryTime = expectedDeliveryTime;
		this.content = content;
		this.isDelivered = false;	//should not be delivered on creation
	}
	
	
	//setters - getters
	public void setToDelivered() 
	{
		isDelivered = true;
	}

	public String getAddress()
	{
		return address;
	}
	
	public int getExpectedDeliveryTime()
	{
		return expectedDeliveryTime;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public boolean getIsDelivered()
	{
		return isDelivered;
	}
}
