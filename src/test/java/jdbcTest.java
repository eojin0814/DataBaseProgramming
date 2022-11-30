import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Board;
import model.BoardDTO;
import model.dao.BoardDao;
import model.dao.MatchingDAO;
import model.dao.ReservationDAO;

public class jdbcTest {
private static BoardDao boardDao = new BoardDao();
private static MatchingDAO matchingDao = new MatchingDAO();
private static ReservationDAO reservationdao = new ReservationDAO();
	
	public static void main(String[] args) throws SQLException {
		System.out.println("CommentSessionRepositoryTest starts...");
		
		//findLocationMatchingList("구성역","수유역");
		
		//System.out.println("CommentSessionRepositoryTest ends...");

		//selectBoardDetailsByBoardID(1);
		System.out.println("test:" + matchingDao.FindLocationMatching("수유역","월곡역"));
		
//		findBoardByBoardId(1);
	}
}

	
//	public static void selectAll() throws SQLException {
//	
//		List<BoardDTO> list = boardDao.select(); // all - 제거해줌 오류나서
//		
//		System.out.println(list);
//	}
//	
//	public static void findBoardByBoardId(int id) {
//		BoardDTO bt = boardDao.findBoardByBoardId(id);
//		System.out.println(bt);
	
	
//	}
//	
	