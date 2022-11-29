package model;

import java.util.Date;
import java.util.List;

public class CustomerDTO {
	
	private int id;
	private String name;
	private int gender;
	private int age;
	private int job; //1 학생 2 직장인
	private String phone;
	private String password;
	private List<ReservationDTO> customerReservationInfo;
	
	
	
	
	
	
	
	
	
	
	//매칭을 위한 생성자
	public CustomerDTO(int gender, int age, int job) {
		super();
		this.gender = gender;
		this.age = age;
		this.job = job;
	}
	
	//password포함 생성자 - 보여주면 안되는 나의 정보
	public CustomerDTO(int id, String name, int gender, int age, int job, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
		this.password = password;
	}
	
	//password제외한 생성자 - 보여줘도 되는 나의 정보
	public CustomerDTO(int id, String name, int gender, int age,int job, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
	}
	
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public int getJob() {
		return job;
	}
	public void setJob(int job) {
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

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", job=" + job
				+ ", phone=" + phone + ", password=" + password + ", customerReservationInfo=" + customerReservationInfo
				+ "]";
	}
	
	
}
