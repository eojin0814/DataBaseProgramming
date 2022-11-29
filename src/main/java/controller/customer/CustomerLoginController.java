package controller.customer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.CustomerDTO;
import model.dao.CustomerDAO;
import model.service.CustomerManager;
import model.service.UserManager;

public class CustomerLoginController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try {
			// 모델에 로그인 처리를 위임
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
	
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/list";			
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/customer/loginForm.jsp";			
		}
	}
	
	//로그인 기능
	   public CustomerDTO login_check(HttpServletRequest request, HttpServletResponse response) {
	      request.setCharacterEncoding("UTF-8");
	      CustomerDTO cdto = new CustomerDTO();//bean
	      CustomerDAO cdao = new CustomerDAO();//mdao
	      CustomerManager cmg = new CustomerManager();
	      
	      cdto.setId(request.getParameter("id"));
	      cdto.setPassword(request.getParameter("password"));
	        if (cmg.login(cdto.getId(), cdto.getPassword())) { // 로그인 성공 시
	         //메인페이지로 이동
	         } else { // 로그인 실패 시
	           //로그인창으로 다시 이동
	           }
	           return cdto;
	         }

}


