package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;
import model.CustomerDTO;
import model.Reservation;
import model.ReservationDTO;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Community 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class ReservationDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReservationDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
		
	}
	
	//세션에 존재하는 customer 정보 가지고 오기, 클릭한 boardid가져오기
		//RESERVATION : CUSTOMERID, STATE, RESERVATIONID, BOARDID
		//여기서 확인해야하는 점 - RESERVATIONID는 autoinrement설정, insert시 자동으로 생성되는지 봐야함
		//state - 1 : 예약 안한 상태, state -  2 : 예약 대기 상태, state - 3:예약 수락 상태
		//신청시 실행되는 dao -> create<reservation 테이블 생성>
	public void create(int boardId, int userId) throws SQLException {
		String sql = "INSERT INTO RESERVATION VALUES (?, ?, ?, ?)";		
		Object[] param = new Object[] {"RESERVATIONID_SEQUENCE.NEXTVAL", userId, 2, boardId};				
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
		
		
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	//여기서 STATE가 0인경우 1인경우 2인경우 등 나눠서 생각 할 수 있을것 같우!!
	//이 메소드는 모든 나의 예약 정보를 가져올 때 사용하는 메소드<예약 대기나, 완료나, 취소와 상관 없는 모든 나의 예약 정보>
	public List<ReservationDTO> waitReservation(int userId) throws SQLException {
		//세션에 있는 customer 객체를 보내준 뒤, board 테이블과 customer id가 저장되어 있는 reservation table join 후 reservation dto에 저장할 값들 뽑아 오기
		String sql = "SELECT DRIVERID, ARRIVAL, DEPARTURETIME, ARRIVALTIME, DEPARTURE, STATE "
				+ "FROM BOARD B JOIN RESERVATION R ON B.BOARDID = R.BOARDID "
				+ "WHERE R.CUSTOMERID=?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {userId});		// JDBCUtil에 query문 설정
					
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
					rs.getInt("STATE"),
					rs.getInt("RESERVATIONID")
					);
				myReservation.add(reservation);				
			}		
			System.out.println(myReservation);
			return myReservation;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	// 확정 예약
	public List<ReservationDTO> confirmReservation(int userId) throws SQLException {
		//세션에 있는 customer 객체를 보내준 뒤, board 테이블과 customer id가 저장되어 있는 reservation table join 후 reservation dto에 저장할 값들 뽑아 오기
		String sql = "SELECT DRIVERID, ARRIVAL, DEPARTURETIME, ARRIVALTIME, DEPARTURE, STATE, RESERVATIONID "
				+ "FROM BOARD B JOIN RESERVATION R ON B.BOARDID = R.BOARDID "
				+ "WHERE R.CUSTOMERID= ?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {userId});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ReservationDTO> myReservation = new ArrayList<ReservationDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				ReservationDTO reservation = new ReservationDTO(			
					rs.getInt("DRIVERID"),
					rs.getString("ARRIVAL"),
					rs.getString("DEPARTURE"),
					rs.getString("ARRIVALTIME"),
					rs.getString("DEPARTURETIME"),
					rs.getInt("STATE"),
					rs.getInt("RESERVATIONID")
					);
				myReservation.add(reservation);				
			}		
			System.out.println(myReservation);
			return myReservation;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 예약확정
	 * + 보드에 이용자 업데이트
	 * insert carshare 
	 */
	public int update(int boardId) throws SQLException {
		try {		
			String sql  = "SELECT RESERVATIONID "
					+ "FROM RESERVATION "
					+ "WHERE BOARDID= ? ";
			jdbcUtil.setSqlAndParameters(sql,  new Object[] {boardId});
			ResultSet result = jdbcUtil.executeQuery();
			while (result.next()) {
				Reservation reservation = new Reservation(			
						result.getInt("RESERVATIONID")
					);
			}
			// 보드ID를 통해 reservationId를 얻어오고
			String sql2 = "UPDATE RESERVATION "
						+ "SET STATE= ? "
						+ "WHERE RESERVATIONID= ? ";
			Object[] param = new Object[] {3 , new Object[] {result}};				
			jdbcUtil.setSqlAndParameters(sql2, param);
			int result2 = jdbcUtil.executeUpdate();
			
			return result2;
			//reservationId를 통해 state 값을 변경
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
	 * 주어진 reservationID에 해당하는 유저의 예약 삭제 
	 * + 추가로 보드의 이용자수도 줄이기 기능 추가
	 */
	public int remove(int reservationId) throws SQLException {
		String sql = "DELETE FROM RESERVATION WHERE RESERVATIONID=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {reservationId});	// JDBCUtil에 delete문과 매개 변수 설정

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

	
}
