package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DriverDTO;

public class DriverDAO {
private JDBCUtil jdbcUtil = null;
	
	public DriverDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 * @return 
	 */
	public int create(DriverDTO driver) throws SQLException {
		String sql = "INSERT INTO DRIVER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {driver.getName(), driver.getGender(), driver.getAge(), 
				driver.getJob(), driver.getPhone(), driver.getPassword(), 
				driver.getDriverId(), driver.getDriverId(), driver.getDriverStrId(), driver.getCarNumber(), driver.getLicense()};
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
		
		try {    
			jdbcUtil.executeUpdate();  // insert 문 실행
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return 0;
	}
	
	//식별자 driverId도 update를 해야 할까요
	public int update(DriverDTO driver) throws SQLException {
		String sql = "UPDATE DRIVER "
					+ "SET name=?, gender=?, age=?, job=?, phone=?, password=?, carNumber=?, getLicense=? "
					+ "WHERE driverStrId=?";
		Object[] param = new Object[] {driver.getName(), driver.getGender(), 
					driver.getAge(), driver.getJob(), driver.getPhone(), driver.getPassword(), 
					 driver.getDriverStrId(), driver.getCarNumber(), driver.getLicense()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String driverStrId) throws SQLException {
		String sql = "DELETE FROM DRIVER WHERE driverstrid=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {driverStrId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 주어진 driverStrID에 해당하는 사용자 정보를 데이터베이스에서 찾아 DriverDTO 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public DriverDTO findDriver(String driverStrId) throws SQLException {
        String sql = "SELECT name=?, gender=?, age=?, job=?, phone=?, password=?, carNumber=?, license=? "
        			+ "FROM DRIVER "
        			+ "WHERE driverStrId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {driverStrId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 정보 발견
				DriverDTO driver = new DriverDTO(		// DriverDTO 객체를 생성하여 정보를 저장
					driverStrId,
					rs.getString("name"),
					rs.getInt("gender"),
					rs.getInt("age"),
					rs.getString("job"),
					rs.getString("phone"),
					rs.getString("password"),
					rs.getString("carNumber"),
					rs.getInt("license")
					);
				return driver;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public boolean existingDriver(String driverStrId) throws SQLException {
		String sql = "SELECT count(*) FROM DRIVER WHERE driverStrId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {driverStrId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
}
