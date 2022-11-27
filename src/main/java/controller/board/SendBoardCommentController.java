package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class SendBoardCommentController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("comment"));
		System.out.println(request.getParameter("boardId"));
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("comment"));
		// TODO Auto-generated method stub
		return null;
	}

}
