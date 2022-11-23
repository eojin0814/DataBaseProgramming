import java.util.List;

import model.BoardDTO;
import model.dao.BoardDao;

public class mybatisText {
private static BoardDao boardDao = new BoardDao();
	
	public static void main(String[] args) {
		System.out.println("CommentSessionRepositoryTest starts...");
		
		

		findBoardByBoardId(1);
	}
	public static void selectAll() {
	
		List<BoardDTO> list = boardDao.selectAll();
		
		System.out.println(list);
	}
	
	public static void findBoardByBoardId(int id) {
		BoardDTO bt = boardDao.findBoardByBoardId(id);
		System.out.println(bt);
	}
}
