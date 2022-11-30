package controller.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.DeleteUserController;
import controller.user.UserSessionUtils;
import model.ReservationDTO;
import model.User;
import model.service.ReservationManager;
import model.service.UserManager;

public class ListReservationController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			
			int userId =Integer.parseInt(request.getParameter("userId"));
	        userId=1;
			
			ReservationManager manager = ReservationManager.getInstance();
			List<ReservationDTO> WaitReservation = manager.selectWaitReservation(userId);
			
			List<ReservationDTO> ConfirmReservation = manager.selectConfirmReservation(userId);	
		    
			request.setAttribute("WaitReservation", WaitReservation);	
			request.setAttribute("ConfirmReservation", ConfirmReservation);
			
		    return "/customer/reservation/Info.jsp";	
		
	}
}
