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
		
	public void create(int carShareId, BoardDTO board, ReservationDTO reservation, StationDTO station) throws SQLException {
	      String sql = "INSERT INTO CARSHARE VALUES (?, ?, ?, ?)";      
	      Object[] param = new Object[] {carShareId, board.getCarshare_Date,reservation.getReservationId, station.getStationId};            
	      jdbcUtil.setSqlAndParameters(sql, param);
	         
	      try {            
	         int result = jdbcUtil.executeUpdate();   // insert 臾   떎 뻾
	         System.out.println(result);
	         } catch (Exception ex) {
	         jdbcUtil.rollback();
	         ex.printStackTrace();
	      } finally {      
	         jdbcUtil.commit();
	         jdbcUtil.close();   // resource 諛섑솚
	      }   
	}
	
	public void create(int carShareId, BoardDTO board, ReservationDTO reservation, StationDTO station) throws SQLException {
	      String sql = "INSERT INTO CARSHARE VALUES (?, ?, ?, ?)";      
	      Object[] param = new Object[] {carShareId, board.getCarshare_Date,reservation.getReservationId, station.getStationId};            
	      jdbcUtil.setSqlAndParameters(sql, param);
	         
	      try {            
	         int result = jdbcUtil.executeUpdate();   // insert 臾   떎 뻾
	         System.out.println(result);
	         } catch (Exception ex) {
	         jdbcUtil.rollback();
	         ex.printStackTrace();
	      } finally {      
	         jdbcUtil.commit();
	         jdbcUtil.close();   // resource 諛섑솚
	      }   
	}

}