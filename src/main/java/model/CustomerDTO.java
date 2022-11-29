package model;

import java.util.Date;
import java.util.List;

public class CustomerDTO {
	
	private String id;
	private String name;
	private int gender;
	private int age;
	private String job;
	private String phone;
	private String password;
	private List<ReservationDTO> customerReservationInfo;
	
	public CustomerDTO() {}
	
	//매칭을 위한 생성자
	public CustomerDTO(int gender, int age, String job) {
		super();
		this.gender = gender;
		this.age = age;
		this.job = job;
	}
	
	//password포함 생성자 - 보여주면 안되는 나의 정보
	public CustomerDTO(String id, String name, int gender, int age, String job, String phone, String password) {
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
	public CustomerDTO(String id, String name, int gender, int age, String job, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
	}
	
	public void update(CustomerDTO updateCus) {
		 this.password = updateCus.password;
	     this.name = updateCus.name;
	     this.job = updateCus.job;
	     this.phone = updateCus.phone;
	     this.age = updateCus.age;
	     
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public void setGender(int gender) {
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
	//로그인 시 비밀번호 검사
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.id.equals(userid);
    }
}
