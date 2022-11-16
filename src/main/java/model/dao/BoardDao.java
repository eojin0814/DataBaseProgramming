package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CustomerDTO;
import model.Reservation;
import model.ReservationDTO;
import model.User;
import model.BoardDTO;

public class BoardDao {
	
	private JDBCUtil jdbcUtil = null;
	
	public BoardDao() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	
	//운전자가 boar등록시 boadid는 auto increment, user comment는 null, currentheadcount 는 0, realtimestate(실시간 탑승 허용 여부)는 1로 초기화
	public void create(BoardDTO board) throws SQLException {
		String sql = "INSERT INTO BOARD VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {board.getDriverId(),"BOARDID_SEQUENCE.NEXTVAL",board.getDriverId()
				,board.getArrival(),board.getDeparture(),board.getCarShareDate(),null,board.getHeadCount(),0,1};				
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
	
	//이거 comment부분 이야기 해봐야됨
	public void updateUserComment(String comment, int boardId) {
		String sql = "UPDATE BOARD "
				+ "SET COMMENT=? "
				+ "WHERE BOARDID=?";
		Object[] param = new Object[] {comment, boardId};				
		jdbcUtil.setSqlAndParameters(sql,param);	// JDBCUtil에 update문과 매개 변수 설정
	
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
	}
	
	//실시간 탑승 여부 핸들링 부분
	public void updateRealTimeState(int realtimestateNum, int boardId) {
		String sql = "UPDATE BOARD "
				+ "SET REALTIMESTATE=? "
				+ "WHERE BOARDID=?";
		
		Object[] param = new Object[] {realtimestateNum, boardId};				
		jdbcUtil.setSqlAndParameters(sql,param);	// JDBCUtil에 update문과 매개 변수 설정
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
	}
	
	
	//운전자가 삭제하기를 눌렀을 경우 -> board삭제, reservation 삭제, carshare삭제...
	public int remove(int baordId) throws SQLException {
		try {				
			
			String sql = "DELETE FROM BOARD WHERE BOARDID = ? ";		
			jdbcUtil.setSqlAndParameters(sql, new Object[] {baordId});
			int result = jdbcUtil.executeUpdate();// 보드 삭제
	
			
			List<Reservation> ResrvationList = new ArrayList<Reservation>();
			String sql1 = "SELECT RESERVATIONID "
					+ "FROM RESERVATION "
					+ "WHERE BOARDID= ? ";		
			jdbcUtil.setSqlAndParameters(sql1, new Object[] {baordId});	
			ResultSet reserId = jdbcUtil.executeQuery();
			while (reserId.next()) {
				Reservation reservation = new Reservation(			
						reserId.getInt("RESERVATIONID")
					);ResrvationList.add(reservation);
				}
			
			
			String sql2 = "DELETE FROM RESERVATION WHERE BOARDID = ? ";		
			jdbcUtil.setSqlAndParameters(sql2, new Object[] {baordId});	// 예약 삭제
			int sql2rst = jdbcUtil.executeUpdate();
			
			int[] sql3result = {0};
			int i=0;
			String sql3 = "DELETE FROM CARSHARE WHERE RESERVATIONID = ? ";
			for(Reservation list : ResrvationList) {
				jdbcUtil.setSqlAndParameters(sql3, new Object[] {list.getReservationId()});	// 카쉐어 삭제
				int sql3rst = jdbcUtil.executeUpdate();
				sql3result[i] = sql3rst;
				i++;
				}
			
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
	
	//머릿수 업데이트 메소드
}
