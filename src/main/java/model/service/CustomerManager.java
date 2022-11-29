package model.service;

import java.sql.SQLException;

import model.CustomerDTO;
import model.User;
import model.dao.CommunityDAO;
import model.dao.CustomerDAO;
import model.dao.UserDAO;

public class CustomerManager {
   private static CustomerManager customerMan = new CustomerManager();
   private CustomerDAO customerDAO;

   private CustomerManager() {
      try {
         customerDAO = new CustomerDAO();
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static CustomerManager getInstance() {
      return customerMan;
   }
   
   //사용자 정보 등록
//   public int customerCreate(CustomerDTO customer) throws SQLException, ExistingUserException {
//      if (userDAO.existingUser(user.getUserId()) == true) {
//         throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
//      }
//      return customerDAO.create(customer);
//   }
   //사용자 정보 수정
   public int update(CustomerDTO customer) throws SQLException {
      return customerDAO.update(customer);
   }
   //
   public int remove(String id) throws SQLException {
      return customerDAO.remove(id);
      
   }
   //사용자 로그인 성공시 메인페이지로 이동(사용자 찾기)
   public CustomerDTO findUser(String id) throws SQLException, UserNotFoundException  {
      CustomerDTO cus = customerDAO.findUser(id);
      if (cus == null) {
         throw new UserNotFoundException(id + "는 존재하지 않는 아이디입니다.");
      }
      return cus;
   }
   
 //로그인 시 패스워드 검사
   public boolean login(String userId, String password)
         throws SQLException, UserNotFoundException, PasswordMismatchException {
         CustomerDTO cus = findUser(userId);

         if (!cus.matchPassword(password)) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
         }
         return true;
      }
   
}