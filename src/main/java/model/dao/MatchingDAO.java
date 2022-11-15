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
		
	
	//세션에 존재하는 customer 정보 가지고 오기, 클릭한 boardid가져오기
	//RESERVATION : CUSTOMERID, STATE, RESERVATIONID, BOARDID
	//여기서 확인해야하는 점 - RESERVATIONID는 autoinrement설정, insert시 자동으로 생성되는지 봐야함
	//state - 1 : 예약 안한 상태, state -  2 : 예약 대기 상태, state - 3:예약 수락 상태
	//신청시 실행되는 dao -> create<reservation 테이블 생성>
	public void create(int boardId, CustomerDTO customer) throws SQLException {
		String sql = "INSERT INTO RESERVATION VALUES (?, ?, ?)";		
		Object[] param = new Object[] {customer.getId(), 2, boardId};				
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			System.out.println(result);
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
	}
	
	//여기서 STATE가 0인경우 1인경우 2인경우 등 나눠서 생각 할 수 있을것 같우!!
	//이 메소드는 모든 나의 예약 정보를 가져올 때 사용하는 메소드<예약 대기나, 완료나, 취소와 상관 없는 모든 나의 예약 정보>
	public List<ReservationDTO> getMyReservation(CustomerDTO customer) throws SQLException {
		//세션에 있는 customer 객체를 보내준 뒤, board 테이블과 customer id가 저장되어 있는 reservation table join 후 reservation dto에 저장할 값들 뽑아 오기
		String sql = "SELECT DRIVERID, ARRIVAL, DEPARTURETIME, ARRIVALTIME, DEPARTURE, STATE "
				+ "FROM BOARD B JOIN RESERVATION R ON B.BOARDID = R.BOARDID "
				+ "WHERE R.CUSTOMERID=?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {customer.getId()});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReservationDTO> myReservation = new ArrayList<ReservationDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReservationDTO reservation = new ReservationDTO(			
					rs.getInt("DRIVERID"),
					rs.getString("ARRIVAL"),
					rs.getString("DEPARTURETIME"),
					rs.getString("ARRIVALTIME"),
					rs.getString("DEPARTURE"),
					rs.getInt("STATE")
					);
				myReservation.add(reservation);				
			}		
			return myReservation;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
}