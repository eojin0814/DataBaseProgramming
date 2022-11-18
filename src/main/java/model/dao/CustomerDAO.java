package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Community;
import model.CustomerDTO;

public class CustomerDAO {
private JDBCUtil jdbcUtil = null;
	
	public CustomerDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 * @return 
	 */
	public void create(CustomerDTO cus) throws SQLException {
		String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {cus.getId(),cus.getName(),cus.getGender(),cus.getAge(),cus.getJob(),cus.getPhone(),cus.getPassword()};				
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
	}
}
