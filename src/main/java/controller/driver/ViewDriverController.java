package controller.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.DriverManager;
import model.service.DriverNotFoundException;
import model.Driver;
import model.DriverDTO;

public class ViewDriverController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!DriverSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/driver/login/form";		// login form 요청으로 redirect
        }
    	
    	DriverManager manager = DriverManager.getInstance();
		String driverId = request.getParameter("driverId");
		
		DriverDTO driver = null;
		driver = manager.findDriver(driverId);	// 사용자 정보 검색	
		
		request.setAttribute("driver", driver);		// 사용자 정보 저장				
		return "/driver/view.jsp";				// 사용자 보기 화면으로 이동
    }
} 