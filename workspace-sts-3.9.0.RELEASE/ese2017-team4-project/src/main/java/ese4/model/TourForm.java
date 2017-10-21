package ese4.model;

import java.util.List;

public class TourForm {
	
	private List<Integer> packIds;
	
	private Integer driverId;
	
	public void setPackIds(List<Integer> packIds) {
		this.packIds=packIds;
	}
	public List<Integer> getPackIds() {
		return this.packIds;
	}
	public void setDriverId(Integer driverId) {
		this.driverId=driverId;
	}
	public Integer getDriverId() {
		return this.driverId;
	}

}
