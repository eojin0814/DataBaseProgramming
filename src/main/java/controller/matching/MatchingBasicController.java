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
import model.Community;
import model.User;
import model.service.UserManager;

//customer가 해당 board를 신청하였을 경우 파라미터 값으로 board id를 넘겨받는다 manager를 통해서 reservationDao.signup(board id)
public class MatchingBasicController implements Controller {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			
	    	UserManager manager = UserManager.getInstance();
			List<Community> commList = manager.findCommunityList();
			
			// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
			request.setAttribute("commList", commList);				
			return "/community/list.jsp";        
	    }
}
