package controller.driver;

import java.sql.DriverManager;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.Community;
import model.DriverDTO;

public class UpdateDriverController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		String updateStrId = request.getParameter("driverStrId");
    		
    		DriverManager manager = DriverManager.getInstance();
    		DriverDTO driver = manager.findDriver(updateStrId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("driver", driver);			

			HttpSession session = request.getSession();        
			return "/driver/login.jsp";	// 로그인 화면으로 이동 (forwarding)
	    }	
    	
    	// POST request (회원정보가 parameter로 전송됨)
    	DriverDTO updateDriver = new DriverDTO(
    			request.getParameter("name"),
    			Integer.parseInt(request.getParameter("gender")),
    			Integer.parseInt(request.getParameter("age")),
    			request.getParameter("job"),
    			request.getParameter("phone"),
    			request.getParameter("password"),
    			Integer.parseInt(request.getParameter("driverId")),
    			request.getParameter("driverStrId"),
    			request.getParameter("carNumber"),
    			Integer.parseInt(request.getParameter("license")));

		DriverManager manager = DriverManager.getInstance();
		manager.update(updateDriver);			
        return "redirect:/driver/list";			
    }
}