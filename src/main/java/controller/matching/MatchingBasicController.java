package controller.matching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.DeleteUserController;
import controller.user.UserSessionUtils;
import model.Board;
import model.Community;
import model.User;
import model.service.MatchingManager;
import model.service.UserManager;

//customer가 해당 board를 신청하였을 경우 파라미터 값으로 board id를 넘겨받는다 manager를 통해서 reservationDao.signup(board id)
public class MatchingBasicController implements Controller {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			//로그인 확인하고
		 	if (!UserSessionUtils.hasLogined(request.getSession())) {
	            return "redirect:/user/login/form";		// login form 요청으로 redirect
	        }
		    //현재 유저 아이디 얻어오기
		 	String userId = UserSessionUtils.getLoginUserId(request.getSession());		
		 	// 유저 정보 넘겨주기
	    	MatchingManager manager = MatchingManager.getInstance();
			List<Board> boardList = manager.findBasicBoardList(userId);
			
			// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
			request.setAttribute("BoardList", boardList);				
			return "/community/list.jsp";        
	    }
}
