import java.util.List;

import model.BoardDTO;
import model.dao.BoardDao;

public class mybatisText {
private static BoardDao boardDao = new BoardDao();
	
	public static void main(String[] args) {
		System.out.println("CommentSessionRepositoryTest starts...");
		
		

		selectBoardDetailsByBoardID(1);
	}
	
	public static void selectBoardDetailsByBoardID(int id) {
		BoardDTO bt = boardDao.selectBoardDetailsByBoardID(id);
		System.out.println("board : " + bt);
		System.out.println("driver : " + bt.getDriver().getDriverName());
	}
}