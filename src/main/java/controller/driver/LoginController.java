package controller.driver;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.CustomerDTO;
import model.DriverDTO;
import model.dao.CustomerDAO;
import model.dao.DriverDAO;
import model.service.CustomerManager;
import model.service.DriverManager;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String driverStrId = request.getParameter("driverStrId");
		String password = request.getParameter("password");
		
		try {
			// 모델에 로그인 처리를 위임
			DriverManager manager = DriverManager.getInstance();
			manager.login(driverStrId, password);
	
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(DriverSessionUtils.DRIVER_SESSION_KEY, driverStrId);
            
            return "index.jsp";			
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/driver/loginForm.jsp";			
		}	
    }
  
}
