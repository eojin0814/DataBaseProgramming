package model;

import java.util.Date;
import java.util.List;


import java.io.Serializable;

@SuppressWarnings("serial")
public class DriverDTO implements Serializable{
	private int driverId;
	private int license;
	private String driverName;
	private int carNumber;
	
	
	public DriverDTO() {
		super();
		System.out.println("driverDTO - 1");
	}
	
	public DriverDTO(int driverId) {
		super();
		this.driverId = driverId;
	}


	public DriverDTO(int driverId, int carNumber, int license, String driverName) {
		super();
		System.out.println("driverDto-4");
		this.driverId = driverId;
		this.carNumber = carNumber;
		this.license = license;
		this.driverName = driverName;
	}
	
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}
	public int getLicense() {
		return license;
	}
	public void setLicense(int license) {
		this.license = license;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}

