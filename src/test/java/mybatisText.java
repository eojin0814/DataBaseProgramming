import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Board;
import model.BoardDTO;
import model.dao.BoardDao;
import model.dao.MatchingDAO;

public class mybatisText {
private static BoardDao boardDao = new BoardDao();
private static MatchingDAO matchingDao = new MatchingDAO();
	
	public static void main(String[] args) {
		System.out.println("CommentSessionRepositoryTest starts...");
		
		findLocationMatchingList("구성역","수유역");
		
		System.out.println("CommentSessionRepositoryTest ends...");

		selectBoardDetailsByBoardID(1);
//		findBoardByBoardId(1);
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
	
	public static void findLocationMatchingList(String arrival, String depature) {
		Map<String, String> loc = new HashMap<String, String>();
		
		loc.put("depature", depature);
		loc.put("arrival", arrival); 
		
		System.out.println(loc);
		List<Board> list = matchingDao.FindLocationMatching(loc);
		System.out.println(list);
	}

		
	
	public static void selectBoardDetailsByBoardID(int id) {
		BoardDTO bt = boardDao.selectBoardDetailsByBoardID(id);
		System.out.println("board : " + bt);
		System.out.println("driver : " + bt.getDriver().getDriverName());
	}
}