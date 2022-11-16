package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;
import model.CustomerDTO;
import model.ReservationDTO;
import model.StationDTO;
import model.User;
import model.BoardDTO;

/*전반적으로 매칭에 관여하는 matchingDao
 * */
public class MatchingDAO {
private JDBCUtil jdbcUtil = null;
	
	public MatchingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	public List<BoardDTO> locBaseMatching(String arrival, String departure) {//위치를 검색했을때 같은 목적지 가진 보드 출력
	      //customerStation은 사용자 입력값
	      String query = "SELECT * "
	      		+ "FROM BOARD "
	      		+ "WHERE BOARDID IN (SELECT BOARDID "
	            + "FROM BOARD " 
	            + "WHERE ARRIVAL=? OR DEPARTURE=?)";   
	      jdbcUtil.setSqlAndParameters(query, new Object[] {arrival, departure});         
	      
	      try { 	
	           ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
				List<BoardDTO> boardList = new ArrayList<BoardDTO>();	// User들의 리스트 생성
				while (rs.next()) {
					BoardDTO board = new BoardDTO(			// User 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("DRIVERID"),
						rs.getInt("BOARDID"),
						rs.getString("ARRIVAL"),
						rs.getString("DEPARTURE"),
						rs.getString("ARRIVALTIME"),
						rs.getString("DEPARTURETIME"),
						rs.getString("carShareDate"),
						rs.getInt("HEADCOUNT")
					);
					boardList.add(board);				// List에 User 객체 저장
				}		
				return boardList;
	         } catch (SQLException ex) {
	            ex.printStackTrace();
	         } finally {  
	            jdbcUtil.close();   
	         }
		return null;   
	   } 
	public List<CustomerDTO> proBaseMatching(ReservationDTO reservation, BoardDTO board, CustomerDTO customer, int customId) {//job, gender, age기반 추천매칭
	    CustomerDTO cus = null;
	    CustomerDTO cusFinal;
	    List<CustomerDTO> cusList;
	    String sql = "SELECT gender, job, age "
	          + "FROM CUSTOMER "
	           + "WHERE CUSTOMERID = ?";
	    
	    jdbcUtil.setSqlAndParameters(sql, new Object[]{customId});  // JDBCUtil 에 query 및 파라미터 설정
	    
	    try {      
	        ResultSet rs = jdbcUtil.executeQuery();
	
	//          List<CustomerDTO> cusList = new ArrayList<CustomerDTO>();   // 리스트 생성
	          while (rs.next()) {   // 커서를 통해 한 행씩 fetch
	
	          cus = new CustomerDTO(  // Employee 객체를 생성하고 생성자를 통해 컬럼 값 저장
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("gender"),
	                rs.getInt("age"),
	                rs.getString("job"),
	                rs.getString("phone"));
	          }
	      } catch (Exception ex) {
	          ex.printStackTrace();
	      } finally {
	          jdbcUtil.close();    // ResultSet, PreparedStatement, Connection 반환
	      }
	    
	    int age = cus.getAge();
	    int gender = cus.getGender();
	    String job = cus.getJob();
	    
	    String sql2 = "SELECT gender, job, age "
	          + "FROM CUSTOMER "
	          + "WHERE gender = ?, job = ?, age = ?";
	    
	    jdbcUtil.setSqlAndParameters(sql2, new Object[]{gender, job, age});  // JDBCUtil 에 query 및 파라미터 설
	    
	    try { 
	          ResultSet rs = jdbcUtil.executeQuery();  
	          cusList = new ArrayList<CustomerDTO>(); 
	          while (rs.next()) {   // 커서를 통해 한 행씩 fetch
	             cusFinal = new CustomerDTO(  // Employee 객체를 생성하고 생성자를 통해 컬럼 값 저장
	                   rs.getInt("id"),
	                   rs.getString("name"),
	                   rs.getInt("gender"),
	                   rs.getInt("age"),
	                   rs.getString("job"),
	                   rs.getString("phone"));
	             cusList.add(cusFinal);
	             
	             }
	         } catch (Exception ex) {
	             ex.printStackTrace();
	         } finally {
	             jdbcUtil.close();    // ResultSet, PreparedStatement, Connection 반환
	         }
	    return cusList;
	     
	 }
		//운전자가 수락을 눌렀을 경우 carshare table만들고, reservation의 state값 변경해주는 dao
		public void create(int carShareId, BoardDTO board, ReservationDTO reservation, StationDTO station) throws SQLException {
		      String sql = "INSERT INTO CARSHARE VALUES (?, ?, ?, ?)";      
		      Object[] param = new Object[] {carShareId, board.getCarShareDate(),reservation.getReservationId(), station.getStationId()};            
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
		return false; 
	}
}