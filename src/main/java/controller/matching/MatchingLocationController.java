package controller.matching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MatchingLocationController implements Controller {
	 private static final Logger log = LoggerFactory.getLogger(MatchingLocationController.class);

	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 	request.setCharacterEncoding("utf-8");
		 	List<Board> boardList = null;
	    	MatchingManager manager = MatchingManager.getInstance();
			String arrival = request.getParameter("arrival");
			String depature = request.getParameter("depature");
			System.out.println(arrival);
			System.out.println(depature);
			Map<String, String> loc = new HashMap<String, String>();
			loc.put(arrival, depature);
			boardList = manager.findLocationBoardList(arrival,depature);
			// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
			request.setAttribute("boardList", boardList);				
			return "/LocationMatching.jsp";        
	    }
}
