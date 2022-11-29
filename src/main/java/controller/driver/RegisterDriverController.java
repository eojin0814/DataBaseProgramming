package controller.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.DriverDTO;
import model.service.ExistingUserException;
import model.service.DriverManager;

public class RegisterDriverController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	// POST request (회원정보가 parameter로 전송됨)
       	DriverDTO driver = new DriverDTO(
			request.getParameter("name"),
			Integer.parseInt(request.getParameter("gender")),
			Integer.parseInt(request.getParameter("age")),
			request.getParameter("job"),
			request.getParameter("phone"),
			request.getParameter("password"),
			Integer.parseInt(request.getParameter("driverId")),
			request.getParameter("driverStrId"),
			request.getParameter("carNumber"),
			Integer.parseInt(request.getParameter("license")),
			request.getParameter("info"));

		try {
			DriverManager manager = DriverManager.getInstance();
			manager.create(driver);
	        return "redirect:/";	// 성공 시 index.jsp로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("driver", driver);
			return "/driver/registerForm.jsp";
		}
    }
}
