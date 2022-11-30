package model;

import java.util.Date;
import java.util.List;


import java.io.Serializable;

@SuppressWarnings("serial")
public class DriverDTO implements Serializable{
	
	//user에 있는 것과 동일한 정보
	private String name;
	private int gender;
	private int age;
	private int job;
	private String phone;
	private String password;
	
	@Override
	public String toString() {
		return "DriverDTO [carNumber=" + carNumber + ", license=" + license + ", driverName=" + driverName + "]";
	}

	public DriverDTO(String driverName,int license, int carNumber) {
		super();
		this.carNumber = carNumber;
		this.license = license;
		this.driverName = driverName;
	}
	//driver에만 있는 정보
	private int driverId; //pk로 자동으로 생성되도록
	private String driverStrId; //실제 사용자가 로그인 할 때 입력하는 아이디
	private int carNumber;
	private int license;
	private String driverName;
	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public DriverDTO() {
		super();
	}
	

	/**
	 * @param driverId
	 */
	public DriverDTO(int driverId) {
		super();
		this.driverId = driverId;
	}

	//매칭을 위한 생성자
	public DriverDTO(int gender, int age, int job) {
		super();
		this.gender = gender;
		this.age = age;
		this.job = job;
	}
		
	//전체 생성자
	public DriverDTO(String name, int gender, int age, int job, String phone, String password, int driverId,
			String driverStrId, int carNumber, int license) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
		this.password = password;
		this.driverId = driverId;
		this.driverStrId = driverStrId;
		this.carNumber = carNumber;
		this.license = license;
	}
		
	//password, license 제외한 생성자 - 보여줘도 되는 나의 정보
	public DriverDTO(String name, int gender, int age, int job, String phone, String driverStrId, int carNumber) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
		this.driverStrId = driverStrId; 
		this.carNumber = carNumber;
	}
	
	public DriverDTO(String driverStrId, String name, int gender, int age, int job, String phone, String password, int carNumber, int license) {
		super();
		this.driverStrId = driverStrId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.phone = phone;
		this.password = password;
		this.carNumber = carNumber;
		this.license = license;
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

	public void setDriverStrId(String driverStrId) {
		this.driverStrId = driverStrId;
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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverStrId() {
		return driverStrId;
	}
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameDriver(String driverStrid) {
        return this.driverStrId.equals(driverStrid);
    }

//안 필요할 거 같음
//	@Override
//	public String toString() {
//		return "Driver [DriverId=" + driverStrId + ", password=" + password + ", name=" + name +", phone="
//				+ phone + ", gender=" + gender + " + "]";
//	}	
	
}
