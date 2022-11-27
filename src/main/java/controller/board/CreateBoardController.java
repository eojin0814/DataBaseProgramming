package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.comm.CreateCommunityController;
import model.BoardDTO;
import model.DriverDTO;
import model.service.BoardManager;
import model.service.UserManager;

public class CreateBoardController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateCommunityController.class);

	@Override

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("createController");
		System.out.println(request.getParameter("arrival"));
    	BoardDTO board = new BoardDTO(
    		//Integer.parseInt(request.getParameter("driverId")),	
			10,
    		request.getParameter("arrival"),
			request.getParameter("departure"),
			request.getParameter("arrivalTime"),
			request.getParameter("departureTime"),
			"2001/1/1",
			Integer.parseInt(request.getParameter("headCount"))
			);		
        
    	System.out.println(board);
		try {
			BoardManager manager = BoardManager.getInstance();
			manager.createBoard(board);
			
	    	log.debug("Create board : {}", board);
	        return "redirect:/driver/register/board/form";	// 성공 시 커뮤니티 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
          
		}
		return null;
    }
}
