package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.driver.DriverSessionUtils;
import model.BoardDTO;
import model.CommentDTO;
import model.service.BoardManager;

public class ShowBoardsController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("driverId"));
		int id = Integer.parseInt(request.getParameter("driverId"));
		
		BoardManager boardMan = BoardManager.getInstance();
		List<BoardDTO> list = boardMan.showMyBoardsByDriverId(id);
		
		HttpSession session = request.getSession();
        session.setAttribute("boardList", list);
		request.setCharacterEncoding("utf-8");
		System.out.println(list);
		return "/driver/myBoards.jsp";
	}

}
