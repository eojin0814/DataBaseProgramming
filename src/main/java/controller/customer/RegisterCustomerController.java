package controller.customer;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.matching.MatchingLocationController;
import model.CustomerDTO;
import model.User;
import model.service.CustomerManager;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterCustomerController implements Controller{
	 private static final Logger log = LoggerFactory.getLogger(RegisterCustomerController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int num= (request.getParameter("gender")=="1") ? 1 : 2;
	 	CustomerDTO customer = new CustomerDTO(
				request.getParameter("id"),
				request.getParameter("name"),
				num,
				Integer.parseInt(request.getParameter("age")),
				Integer.parseInt(request.getParameter("job")),
				request.getParameter("phone"),
				request.getParameter("password"),
				request.getParameter("info"));
	 	log.debug(customer.toString());
			
	       try {
				CustomerManager customerMan = CustomerManager.getInstance();
				int i = customerMan.customerCreate(customer);
				if(i == 1) {
					System.out.println("성공");
		        	return "redirect:/";	
		        }
		        
			} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
//	            request.setAttribute("registerFailed", true);
//				request.setAttribute("exception", e);
//				request.setAttribute("user", user);
				return "/customer/joinForm.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "/customer/joinForm.jsp";//실패
	}

}
