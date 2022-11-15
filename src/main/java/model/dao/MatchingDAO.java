package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;
import model.CustomerDTO;
import model.ReservationDTO;
import model.User;

/*전반적으로 매칭에 관여하는 matchingDao
 * */
public class MatchingDAO {
private JDBCUtil jdbcUtil = null;
	
	public MatchingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	
	

}