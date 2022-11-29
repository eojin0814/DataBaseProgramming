package model.service;

import java.sql.SQLException;
import java.util.List;

import model.DriverDTO;
import model.dao.DriverDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class DriverManager {
	private static DriverManager driverMan = new DriverManager();
	private DriverDAO driverDAO;

	public DriverManager() {
		try {
			driverDAO = new DriverDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static DriverManager getInstance() {
		return driverMan;
	}
	
	public int create(DriverDTO driver) throws SQLException, ExistingUserException {
		if (driverDAO.existingDriver(driver.getDriverStrId()) == true) {
			throw new ExistingUserException(driver.getDriverStrId() + "는 존재하는 아이디입니다.");
		}
		return driverDAO.create(driver);
	}

	public int update(DriverDTO driver) throws SQLException {
		return driverDAO.update(driver);
	}	

	public int remove(String driverId) throws SQLException {
		return driverDAO.remove(driverId);
	}

	public DriverDTO findDriver(String driverStrId)
		throws SQLException, UserNotFoundException {
		DriverDTO driver = driverDAO.findDriver(driverStrId);
		
		if (driver == null) {
			throw new UserNotFoundException(driverStrId + "는 존재하지 않는 아이디입니다.");
		}		
		return driver;
	}


	public boolean login(String driverStrId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		DriverDTO driver = findDriver(driverStrId);

		if (!driver.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public DriverDAO getDriverDAO() {
		return this.driverDAO;
	}
}
