package model;

import java.util.Date;
import java.util.List;

public class DriverDTO {
	
	private int driverId;
	

	public DriverDTO(int driverId) {
		super();
		this.driverId = driverId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "DriverDTO [driverId=" + driverId + "]";
	}
	
	
}
