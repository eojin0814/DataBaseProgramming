package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Community;
import model.CustomerDTO;
import model.DriverDTO;
import model.ReservationDTO;
import model.StationDTO;
import model.User;
import model.Board;
import model.BoardDTO;

/*전반적으로 매칭에 관여하는 matchingDao
 * */
public class MatchingDAO {
	private JDBCUtil jdbcUtil = null;
	private SqlSessionFactory sqlSessionFactory;
	private final String namespace = "repository.mybatis.mapper.BoardMapper";	
	
	public MatchingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
		
		//String resource = "mybatis-config.xml";
		//InputStream inputStream;
		//try {
		//	inputStream = Resources.getResourceAsStream(resource);
		//} catch (IOException e) {
		//	throw new IllegalArgumentException(e);
		//}
		//sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
		
	public List<Board> FindLocationMatching(String arrival,String depature) {
		//위치를 검색했을때 같은 목적지 가진 보드 출력
	    // 도착지 또는 출발지가 같은 경우 
		// 경유지가 포함되는 경우 
		List<Board> boardList = new ArrayList<Board>();
        Board board = null;
        
		String sql = "SELECT DRIVERID, ARRIVAL, DEPARTURE, ARRIVALTIME, DEPARTURETIME,  CARSHAREDATE, HEADCOUNT, CURRENTHEADCOUNT   "
	              + "FROM BOARD "
	               + "WHERE ARRIVAL = ? or DEPARTURE = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[]{arrival,depature});  
		 try {      
	            ResultSet rs = jdbcUtil.executeQuery();
	            while (rs.next()) {   
	                   board = new Board( 
	                         rs.getInt("driverId"),
	                         rs.getString("arrival"),
	                         rs.getString("departure"),
	                         rs.getString("arrivalTime"),
	                         rs.getString("departureTime"),
	                         rs.getString("carShareDate"),
	                         rs.getInt("headCount"),
	                         rs.getInt("currentheadcount"));
	                   boardList.add(board);
	                  }
	          } catch (Exception ex) {
	              ex.printStackTrace();
	          } finally {
	              jdbcUtil.close();    
	          }
		return boardList;
	   } 
	public List<Board> FindBasicMatching(String userId) {//job, gender, age기반 추천매칭
		
		//1) userId를 통해 유저의 gender job age를 가지고 오고
		//2) 동일한 조건을 가진 드라이버를 가지고 오고
		//3) 드라이버의 보드를 가지고 오는 일
		
        CustomerDTO customer = null;
        List<DriverDTO> driverList = null;
        DriverDTO driver = null;
        List<Board> boardList = null;
        Board board = null;
        
        String sql = "SELECT gender, job, age "
              + "FROM CUSTOMER "
               + "WHERE CUSTOMESTRID = ? ";
        
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});  
        try {      
            ResultSet rs = jdbcUtil.executeQuery();

              while (rs.next()) {  

              customer = new CustomerDTO( 
                    rs.getInt("gender"),
                    rs.getInt("age"),
                    rs.getInt("job"));
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          } finally {
              jdbcUtil.close();    
          }
        
        int age = customer.getAge();
        int gender = customer.getGender();
        int job = customer.getJob();
        
        String sql2 = "SELECT DRIVERID "
              + "FROM DRIVER "
              + "WHERE gender = ? or job = ? or age = ? ";
        
        jdbcUtil.setSqlAndParameters(sql2, new Object[]{gender, job, age});  // JDBCUtil 에 query 및 파라미터 설
        
        try { 
              ResultSet rs = jdbcUtil.executeQuery();  
              
              while (rs.next()) {   
                 driver = new DriverDTO( 
                       rs.getInt("driverId"));
                 driverList.add(driver);
                 }
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();    // ResultSet, PreparedStatement, Connection 반환
             }
        
        String sql3 = "SELECT * "
                + "FROM BOARD "
                + "WHERE DRIVERID = ? ";
          
          jdbcUtil.setSqlAndParameters(sql3, new Object[]{driverList});  // JDBCUtil 에 query 및 파라미터 설
          
          try { 
                ResultSet rs = jdbcUtil.executeQuery();  
                
                while (rs.next()) {   
                   board = new Board( 
                         rs.getInt("driverId"),
                         rs.getString("arrival"),
                         rs.getString("depature"),
                         rs.getString("arrivalTime"),
                         rs.getString("departureTime"),
                         rs.getString("carShareDate"),
                         rs.getInt("headCount"),
                         rs.getInt("CURRENTHEADCOUNT"));
                   boardList.add(board);
                   }
                
               } catch (Exception ex) {
                   ex.printStackTrace();
               } finally {
                   jdbcUtil.close();    // ResultSet, PreparedStatement, Connection 반환
               }
          return boardList;
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