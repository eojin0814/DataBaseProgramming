package controller.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.DeleteUserController;
import controller.user.UserSessionUtils;
import model.User;
import model.service.ReservationManager;
import model.service.UserManager;

public class ReservationCreateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			
			int boardId =Integer.parseInt(request.getParameter("boardId"));
			int userId = Integer.parseInt(UserSessionUtils.getLoginUserId(request.getSession()));
			// 유저 찐 아이디 인지 유저 걍 아이디인지 일단은 찐 아이디로 함
			
			ReservationManager manager = ReservationManager.getInstance();
			manager.createReservation(boardId,userId);
				
		    
		    return "redirect:/community/list";	
		
	}
}
