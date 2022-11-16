package model;

import java.util.Date;
import java.util.List;

public class CustomerDTO {
	
	private int id;
	private String name;
	private int gender;
	private int age;
	private String job;
	private String phone;
	private List<ReservationDTO> customerReservationInfo;
	
	public CustomerDTO(int gender, int age, String job) {
		super();
		this.gender = gender;
		this.age = age;
		this.job = job;
	}
	
	public CustomerDTO(int id, String name, int gender, int age, String job, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
	}
	
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gentder) {
		this.gender = gender;
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
