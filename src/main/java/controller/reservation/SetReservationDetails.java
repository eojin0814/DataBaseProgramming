package controller.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.BoardDTO;
import model.CommentDTO;
import model.Community;
import model.dao.BoardDao;
import model.service.BoardManager;
import model.service.UserManager;

//driver 정보와, board 정보 가지고오기 comment 처리
public class SetReservationDetails implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("boardId"));
		//board id
		int boardIdInt = Integer.parseInt(request.getParameter("boardId"));
		
		
		BoardManager boardMan = BoardManager.getInstance();
		
		//클릭한 보드정보, 운전자 정보 가지고 오기
		BoardDTO board = boardMan.selectBoardDetailsByBoardID(boardIdInt);
	
		//board정보 request에 등록하기
		HttpSession session = request.getSession();
		request.setAttribute("board", board);
		request.setCharacterEncoding("utf-8");
		
		List<CommentDTO> list = boardMan.findCommentByBoardId(boardIdInt);
		request.setAttribute("commentList", list);
		request.setCharacterEncoding("utf-8");
		System.out.println(list);
		return "/reservation/view.jsp";
	}
}
