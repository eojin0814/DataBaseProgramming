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


public class CustomerLoginController implements Controller{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      String userId = request.getParameter("id");
      String password = request.getParameter("password");
//      System.out.println(userId);
//      System.out.println(password);
      try {
         // 모델에 로그인 처리를 위임
         CustomerManager cmg = CustomerManager.getInstance();
         cmg.login(userId, password);
   
         // 세션에 사용자 이이디 저장
         HttpSession session = request.getSession();
         session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
         
         return "index.jsp";         
      } catch (Exception e) {
         /* UserNotFoundException이나 PasswordMismatchException 발생 시
          * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
          */
         request.setAttribute("loginFailed", true);
         request.setAttribute("exception", e);
            return "/customer/joinForm.jsp";         
      	}
   
      }
}

