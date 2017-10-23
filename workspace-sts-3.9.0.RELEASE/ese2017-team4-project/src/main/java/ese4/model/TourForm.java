package ese4.model;

import java.util.List;
/**
 * Is used in the tour form.
 * 
 * @author ese4
 *
 */
public class TourForm {
	
	private List<Integer> packIds;
	
	private Integer driverId;
	
	/**
	 * Sets its parameter input as packIds.
	 * @param tour
	 */
	public void setPackIds(List<Integer> packIds) {
		this.packIds=packIds;
	}
	/**
	 * Returns its packIds.
	 * @return packIds
	 */
	public List<Integer> getPackIds() {
		return this.packIds;
	}
	/**
	 * Sets its parameter input as driverId.
	 * @param driverId
	 */
	public void setDriverId(Integer driverId) {
		this.driverId=driverId;
	}
	/**
	 * Returns its driverId.
	 * @return driverId
	 */
	public Integer getDriverId() {
		return this.driverId;
	}

}
