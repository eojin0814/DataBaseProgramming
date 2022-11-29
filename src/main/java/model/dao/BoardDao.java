package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.CustomerDTO;
import model.DriverDTO;
import model.Reservation;
import model.ReservationDTO;
import model.User;
import model.BoardDTO;
import model.CommentDTO;

public class BoardDao {
	
	private JDBCUtil jdbcUtil = null;
//	
//	public BoardDao() {			
//		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
//	}
//	myBatis - xml 버전
	private final String namespace = "repository.mybatis.mapper.BoardMapper";

	private SqlSessionFactory sqlSessionFactory;
	
	public BoardDao() {
		jdbcUtil = new JDBCUtil();
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
//	
//	public List<BoardDTO> selectAll() {
//		System.out.println("selectAll");
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			return sqlSession.selectList(
//					namespace + ".selectAllBoard");	
//		} finally {
//			System.out.println(sqlSession.selectList(
//					namespace + ".selectAllBoard"));
//			sqlSession.close();
//		}
//	}
//	
	
	//boardId통해서 boar정보와 driver정보 가지고 오기 - mybatis xml버전
//	public BoardDTO selectBoardDetailsByBoardID(int boardId) {
//		System.out.println("selectBoardDetailsByBoardID");
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			return (BoardDTO)sqlSession.selectOne(
//					namespace + ".selectBoardDetailsByBoardID", boardId);	
//		} finally {
//			sqlSession.close();
//		}
//	}
	//드라이버 아이디로 등록한 보드들 리스트로 보여주기

	public List<BoardDTO> showMyBoardsByDriverId(int driverId) {
		System.out.println("showMyBoardsByDriverId - dao");
		String sql = "select * from board where driverid=?";
		Object[] param = new Object[] {driverId};				
		for(Object o:param) {
			System.out.println(o);
		}
		System.out.println(param);
		jdbcUtil.setSqlAndParameters(sql, param);
	
		try {	
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			ResultSet rs = jdbcUtil.executeQuery();	
			
			while(rs.next()) {
				BoardDTO board = new BoardDTO(
						rs.getInt("DRIVERID"),
						rs.getInt("BOARDID"),
						rs.getString("ARRIVAL"),
						rs.getString("DEPARTURE"),
						rs.getString("ARRIVALTIME"),
						rs.getString("DEPARTURETIME"),
						rs.getString("CARSHAREDATE"),
						rs.getInt("HEADCOUNT"),
						rs.getInt("CURRENTHEADCOUNT"),
						rs.getInt("REALTIMESTATE")
				);
				
				list.add(board);
			}
			System.out.println(list);
			return list;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return null;	
	}
	
	public BoardDTO selectBoardDetailsByBoardID(int boardId) {
		System.out.println("selectBoardDetailsByBoardID - dao");
		String sql = "SELECT b.BOARDID, b.DRIVERID, b.ARRIVAL, b.DEPARTURE, b.ARRIVALTIME, b.DEPARTURETIME, b.CARSHAREDATE, b.HEADCOUNT, d.DRIVERNAME, d.LICENSE, d.CARNUMBER FROM DRIVER d, BOARD b WHERE d.DRIVERID = b.DRIVERID AND b.BOARDID = ? ";		
		Object[] param = new Object[] {boardId};				
		for(Object o:param) {
			System.out.println(o);
		}
		System.out.println(param);
		jdbcUtil.setSqlAndParameters(sql, param);
	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			System.out.println(rs.next());
			DriverDTO driver = new DriverDTO(
					rs.getString("DRIVERNAME"),rs.getInt("LICENSE"),rs.getInt("CARNUMBER")
					);
			System.out.println(driver);
			BoardDTO board = new BoardDTO(
					rs.getInt("DRIVERID"),
					rs.getString("ARRIVAL"),
					rs.getString("DEPARTURE"),
					rs.getString("ARRIVALTIME"),
					rs.getString("DEPARTURETIME"),
					rs.getString("CARSHAREDATE"),
					rs.getInt("HEADCOUNT"),
					driver
					);
			System.out.println(board);
			return board;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return null;	
	}
	
	public String findBoardByBoardId(int boardId) {
		System.out.println("select one");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (String)sqlSession.selectOne(
					namespace + ".findBoardByBoardId", boardId);	
		} finally {
			sqlSession.close();
		}
	}
	
	public CommentDTO selectComment(int boardId) {
		System.out.println("select comment dao");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (CommentDTO)sqlSession.selectOne(
					namespace + ".selectComment", boardId);	
		} finally {
			sqlSession.close();
		}
	}
	
//	운전자가 boar등록시 boadid는 auto increment, user comment는 null, currentheadcount 는 0, realtimestate(실시간 탑승 허용 여부)는 1로 초기화
	public void create(BoardDTO board) throws SQLException {
		System.out.println(board);
		String sql = "INSERT INTO BOARD VALUES (?, BOARDID_SEQUENCE.NEXTVAL , ?, ?, ?, ?, ?, ?, ?, ?, ?) ";		
		Object[] param = new Object[] {board.getDriverId(),board.getArrival()
				,board.getDepartureTime(),board.getArrivalTime(),board.getDeparture(),board.getCarShareDate(),null,board.getHeadCount(),0,1};				
		for(Object o:param) {
			System.out.println(o);
		}
		System.out.println(param);
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
	
	@SuppressWarnings("null")
	public List<BoardDTO> select() throws SQLException {
		String sql = "select * from BOARD";		
		jdbcUtil.setSqlAndParameters(sql, null);
		try {				
			List<BoardDTO> boardList = new ArrayList<BoardDTO>();
			ResultSet rs = jdbcUtil.executeQuery();	// insert 문 실행
		
			while(rs.next()) {
				BoardDTO board = new BoardDTO(
					rs.getInt("DRIVERID"),
					rs.getInt("BOARDID"),
					rs.getString("ARRIVAL"),
					rs.getString("DEPARTURE"),
					rs.getString("ARRIVALTIME"),
					rs.getString("DEPARTURETIME"),
					rs.getString("CARSHAREDATE"),
					rs.getInt("HEADCOUNT")
				);
				System.out.println(board);
				boardList.add(board);
			}
			return boardList;
			} catch (Exception ex) {
				return null;
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
	}
	
	//comment초기에 보드 아이디로 페이지 들어가면 보여줄 댓글 창
	public List<CommentDTO> findCommentByBoardId(int boardId) throws SQLException {
		System.out.println("dao.findcommentByBoardId 들어옴");
		String sql = "select c.commentid, c.boardid, c.details, u.name, u.customerid from comm c, customer u where c.customerid = u.customerid and c.boardid = ?";	
		Object[] param = new Object[] {boardId};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {				
			List<CommentDTO> commentList = new ArrayList<CommentDTO>();
			ResultSet rs = jdbcUtil.executeQuery();	// insert 문 실행
			while(rs.next()) {
				CommentDTO comment = new CommentDTO();
				CustomerDTO customer = new CustomerDTO();
				customer.setName(rs.getString("NAME"));
				customer.setId(rs.getInt("CUSTOMERID"));
				comment.setCommentNo(rs.getInt("COMMENTID"));
				comment.setBoardId(rs.getInt("BOARDID"));
				comment.setDetails(rs.getString("DETAILS"));
				comment.setCustomer(customer);
				System.out.println("dao " + comment.getCustomer().getName());
				System.out.println("dao " + comment);
				commentList.add(comment);
			}
			return commentList;
			} catch (Exception ex) {
				return null;
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
	}
	
	//전송 버튼 눌렀을때 user id와 board id를 comment table에 넣어주기
	public void insertComment(int customerId, int boardId, String details) throws Exception {
		System.out.println("dao.insertComment 들어옴");
		String sql = "insert into comm values (commentid_sequence.nextval, ?, ?, ?)";	
		Object[] param = new Object[] {customerId,boardId,details};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {				
			int rs = jdbcUtil.executeUpdate();	// insert 문 실행
			System.out.println(rs);		
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
	}
	
	

//	//이거 comment부분 이야기 해봐야됨
//	public void updateUserComment(String comment, int boardId) {
//		String sql = "UPDATE BOARD "
//				+ "SET COMMENT=? "
//				+ "WHERE BOARDID=?";
//		Object[] param = new Object[] {comment, boardId};				
//		jdbcUtil.setSqlAndParameters(sql,param);	// JDBCUtil에 update문과 매개 변수 설정
//	
//		try {				
//			int result = jdbcUtil.executeUpdate();	// update 문 실행
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 반환
//		}		
//	}
//	
//	//실시간 탑승 여부 핸들링 부분
//	public void updateRealTimeState(int realtimestateNum, int boardId) {
//		String sql = "UPDATE BOARD "
//				+ "SET REALTIMESTATE=? "
//				+ "WHERE BOARDID=?";
//		
//		Object[] param = new Object[] {realtimestateNum, boardId};				
//		jdbcUtil.setSqlAndParameters(sql,param);	// JDBCUtil에 update문과 매개 변수 설정
//		try {				
//			int result = jdbcUtil.executeUpdate();	// update 문 실행
//			
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 반환
//		}		
//	}
//	
//	
//	//운전자가 삭제하기를 눌렀을 경우 -> board삭제, reservation 삭제, carshare삭제...
//	public int remove(int baordId) throws SQLException {
//		try {				
//			
//			String sql = "DELETE FROM BOARD WHERE BOARDID = ? ";		
//			jdbcUtil.setSqlAndParameters(sql, new Object[] {baordId});
//			int result = jdbcUtil.executeUpdate();// 보드 삭제
//	
//			
//			List<Reservation> ResrvationList = new ArrayList<Reservation>();
//			String sql1 = "SELECT RESERVATIONID "
//					+ "FROM RESERVATION "
//					+ "WHERE BOARDID= ? ";		
//			jdbcUtil.setSqlAndParameters(sql1, new Object[] {baordId});	
//			ResultSet reserId = jdbcUtil.executeQuery();
//			while (reserId.next()) {
//				Reservation reservation = new Reservation(			
//						reserId.getInt("RESERVATIONID")
//					);ResrvationList.add(reservation);
//				}
//			
//			
//			String sql2 = "DELETE FROM RESERVATION WHERE BOARDID = ? ";		
//			jdbcUtil.setSqlAndParameters(sql2, new Object[] {baordId});	// 예약 삭제
//			int sql2rst = jdbcUtil.executeUpdate();
//			
//			int[] sql3result = {0};
//			int i=0;
//			String sql3 = "DELETE FROM CARSHARE WHERE RESERVATIONID = ? ";
//			for(Reservation list : ResrvationList) {
//				jdbcUtil.setSqlAndParameters(sql3, new Object[] {list.getReservationId()});	// 카쉐어 삭제
//				int sql3rst = jdbcUtil.executeUpdate();
//				sql3result[i] = sql3rst;
//				i++;
//				}
//			
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 반환
//		}		
//		return 0;
//	}
//	
	//머릿수 업데이트 메소드
}
