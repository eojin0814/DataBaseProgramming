package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.CustomerDTO;
import model.User;
import model.service.CustomerManager;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterCustomerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * public CustomerDTO(int id, String name, int gender, int age, String job, String phone, String password) {
		 */
		System.out.println("controller들어옴");
	 	CustomerDTO customer = new CustomerDTO(
				request.getParameter("customerId"),
				request.getParameter("name"),
				Integer.parseInt(request.getParameter("gender")),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("job"),
				request.getParameter("phone"),
				request.getParameter("password"));
			
	       try {
				CustomerManager customerMan = CustomerManager.getInstance();
				customerMan.customerCreate(customer);
				System.out.println("성공");
		        return "/";	// 성공 시 사용자 리스트 화면으로 redirect
		        
			} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
//	            request.setAttribute("registerFailed", true);
//				request.setAttribute("exception", e);
//				request.setAttribute("user", user);
				return "/customer/registerForm.jsp";
			}
	}

}
