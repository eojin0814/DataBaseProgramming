package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.compiler.ast.ASTList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.comm.CreateCommunityController;
import model.BoardDTO;
import model.service.BoardManager;

public class ListBoardController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateCommunityController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListBoardController");
		
//    	BoardDTO board = new BoardDTO(
//    		Integer.parseInt(request.getParameter("driverId")),
//			request.getParameter("arrival"),
//			request.getParameter("departure"),
//			request.getParameter("arrivalTime"),
//			request.getParameter("departureTime"),
//			request.getParameter("carShareDate"),
//			Integer.parseInt(request.getParameter("headCount"))
//			);		
        
		try {
			BoardManager manager = BoardManager.getInstance();
			List<BoardDTO> boardlist = manager.selectAllBoard();
			
			HttpSession session = request.getSession();
			request.setAttribute("boardList", boardlist);
			
	    	log.debug("Create Community : {}", boardlist);
	    	 return "/";	// 성공시 
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
          
		}
		return null;
	}
}
