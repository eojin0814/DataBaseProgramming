package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;
import model.CustomerDTO;
import model.ReservationDTO;
import model.User;
import model.service.BoardDTO;

/*전반적으로 매칭에 관여하는 matchingDao
 * */
public class MatchingDAO {
private JDBCUtil jdbcUtil = null;
	
	public MatchingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	public BoardDTO locBaseMatching(BoardDTO board, StationDTO station, String customerStation) {//위치를 검색했을때 같은 목적지 가진 보드 출력
	      //customerStation은 사용자 입력값
	      String query = "SELECT arrival "
	            + "FROM board " 
	            + "WHERE arrival = ?";   
	      jdbcUtil.setSqlAndParameters(query, new Object[] {customerStation});         
	      
	      try { //일단 목적지만,,,
	            ResultSet rs = jdbcUtil.executeQuery();        
	            if (rs.next()) {
	               String arrival = rs.getInt("arrival");
	              
	               board = new Board("arrival");
	            }
	         } catch (SQLException ex) {
	            ex.printStackTrace();
	         } finally {  
	            jdbcUtil.close();   
	         }   
	         return board; 
	       } 
	   }
	   
	   public BoardDTO proBaseMatching(BoardDTO board, CustomerDTO customer) {//job, gender, age기반 추천매칭
		   
	      String sql = "SELECT gender, job, age, customerId "
	            + "FROM CUSTOMER "
	             + "GROUP BY gender, job, age";
	      
	      try { 
	            ResultSet rs = jdbcUtil.executeQuery();        
	            if (rs.next()) {
	               String arrival = rs.getInt("arrival");
	              
	               board = new Board("arrival");
	            }
	         } catch (SQLException ex) {
	            ex.printStackTrace();
	         } finally {  
	            jdbcUtil.close();   
	         }   
	         return board; 
	       } 
	   }
	   
	//운전자가 수락을 눌렀을 경우 carshare table만들고, reservation의 state값 변경해주는 dao
	public void create(int carShareId, BoardDTO board, ReservationDTO reservation, StationDTO station) throws SQLException {
	      String sql = "INSERT INTO CARSHARE VALUES (?, ?, ?, ?)";      
	      Object[] param = new Object[] {carShareId, board.getCarShareDate(),reservation.getReservationId(), station.getStationId};            
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
	
	//머릿수 가능 여부 확인 메소드
	public Boolean checkHeadCount(int boardId) {
		String sql = "SELECT CURRENTHEADCOUNT, HEADCOUNT FROM BOARD WHRER BOARDID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object []{boardId});
        
	      try {            
	         ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				if (rs.next()) {	
					int currentHead = rs.getInt("CURRENTHEADCOUNT");
					int head = rs.getInt("HEADCOUNT");
					
					if(currentHead < head) {
						return true;
					}
					else {
						return false;
					}
				}
	         } catch (Exception ex) {
	         jdbcUtil.rollback();
	         ex.printStackTrace();
	      } finally {      
	         jdbcUtil.commit();
	         jdbcUtil.close();   // resource 諛섑솚
	      } 
}