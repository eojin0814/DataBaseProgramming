package model;

import java.util.Date;
import java.util.List;

public class CustomerDTO {
	
	private int id;
	private String name;
	private int gentder;
	private int age;
	private String job;
	private String phone;
	private List<ReservationDTO> customerReservationInfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGentder() {
		return gentder;
	}
	public void setGentder(int gentder) {
		this.gentder = gentder;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<ReservationDTO> getCustomerReservationInfo() {
		return customerReservationInfo;
	}
	public void setCustomerReservationInfo(List<ReservationDTO> customerReservationInfo) {
		this.customerReservationInfo = customerReservationInfo;
	}
}
