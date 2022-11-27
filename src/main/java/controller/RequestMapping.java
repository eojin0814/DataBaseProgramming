package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.board.*;
import controller.comm.*;
import controller.customer.CustomerLoginController;
import controller.customer.RegisterCustomerController;
import controller.reservation.SetReservationDetails;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	
    	
    	//board
    	//모든 board 테이블의 내용 가지고 오기
    	mappings.put("/driver", new ListBoardController());
//    	mappings.put("/driver/list", new ForwardController("/driver/boardList.jsp"));
    	mappings.put("/driver/register/board/form", new ForwardController("/driver/registerBoard.jsp"));
    	mappings.put("/driver/register/board", new CreateBoardController());
    	
    	//board 클릭시 넘어가는 예약페이지
    	mappings.put("/reservation/view/init",  new SetReservationDetails());
    	mappings.put("/customer/login/form", new ForwardController("/customer/loginForm.jsp"));
    	mappings.put("/customer/login", new CustomerLoginController());
    	mappings.put("/customer/register/form",  new ForwardController("/customer/registerForm.jsp"));
    	mappings.put("/customer/register",  new RegisterCustomerController());
    	
    	//board에 댓글 단거 처리하기
    	mappings.put("/board/comment", new SendBoardCommentController());

    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());
        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
//      mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
//      mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/register", new RegisterUserController());

        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합
//      mappings.put("/user/update/form", new UpdateUserFormController());
//      mappings.put("/user/update", new UpdateUserController());        
        mappings.put("/user/update", new UpdateUserController());
        
        mappings.put("/user/delete", new DeleteUserController());
        
        // 커뮤니티 관련 request URI 추가
        mappings.put("/community/list", new ListCommunityController());
        mappings.put("/community/view", new ViewCommunityController());
        mappings.put("/community/create/form", new ForwardController("/community/creationForm.jsp"));
        mappings.put("/community/create", new CreateCommunityController());
        mappings.put("/community/update", new UpdateCommunityController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}