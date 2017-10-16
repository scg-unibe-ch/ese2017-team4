package ese4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Keeps track of it's content, where it has to be delivered and it's expected delivery time.
 * The Package knows if its delivered yet or not.
 * 
 * @author ese4
 *
 */
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
	private String address;
	private String content;
	
	
	
	private Boolean isDelivered;
	private int expectedDeliveryTime; //expected time in minutes?
	
	
	
	public Package(String address, int expectedDeliveryTime, String content) 
	{
		this.address = address;
		this.expectedDeliveryTime = expectedDeliveryTime;
		this.content = content;
		this.isDelivered = false;	//should not be delivered on creation
	}
	
	public Package()
	{
		this.isDelivered = false;
		this.expectedDeliveryTime = 0;
	}
	
	
	//setters - getters
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public boolean getIsDelivered()
	{
		return isDelivered;
	}
	
	public void setToDelivered() 
	{
		isDelivered = true;
	}
	
	public int getExpectedDeliveryTime()
	{
		return expectedDeliveryTime;
	}
	
	public void setExpectedDeliveryTime(int expectedDeliveryTime)
	{
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

}
