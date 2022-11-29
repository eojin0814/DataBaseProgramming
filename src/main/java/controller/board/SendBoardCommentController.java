package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.CommentDTO;
import model.service.BoardManager;
import model.service.UserManager;

public class SendBoardCommentController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("comment"));
		System.out.println(request.getParameter("boardId"));
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("comment"));
		
		//insert할거랑 update
//		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String details = request.getParameter("comment");
		
		//manger호출
		BoardManager boardMan = BoardManager.getInstance();
		List<CommentDTO> list = boardMan.updateComment(1, boardId, details);
		request.setAttribute("commentList", list);
		request.setCharacterEncoding("utf-8");
		System.out.println(list);
		return "/driver/m.jsp";
	}

}
