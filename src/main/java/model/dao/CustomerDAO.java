package model.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Community;
import model.CustomerDTO;
import model.User;

public class CustomerDAO {
private JDBCUtil jdbcUtil = null;
   
   public CustomerDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
   }
      
   /**
    * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
    * @return 
    */
   public int create(CustomerDTO cus) throws SQLException {
      String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";      
      Object[] param = new Object[] {cus.getId(),cus.getName(),cus.getGender(),cus.getAge(),cus.getJob(),cus.getPhone(),cus.getPassword(), cus.getInfo()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
      try {    
         jdbcUtil.executeUpdate(); // insert 문 실행
         return 1;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }   
      return -2; // 삽입 실패
   }
   
   //사용자 찾기
   public CustomerDTO findUser(String userId) throws SQLException {
        String sql = "SELECT password, name, email, phone, commId, cName "
                 + "FROM CUSTOMER c LEFT OUTER JOIN Community c ON u.commId = c.cId "
                 + "WHERE userid=? ";              
      jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query 실행
         if (rs.next()) {                  // 학생 정보 발견
            CustomerDTO cus = new CustomerDTO(      // User 객체를 생성하여 학생 정보를 저장
               rs.getString("strId"),
               rs.getString("name"),
               rs.getInt("gender"),
               rs.getInt("age"),
               rs.getInt("job"),
               rs.getString("phone"),
               rs.getString("password"),
               rs.getString("info")
               );
            return cus;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource 반환
      }
      return null;
   }
   
   //사용자 정보 수정
   public int update(CustomerDTO cus) throws SQLException {
      String sql = "UPDATE CUSTOMER "
               + "SET password=?, name=?, age=?, phone=?, job=?, gender=?, id=? "
               + "WHERE userid=?";
      Object[] param = new Object[] {cus.getPassword(), cus.getName(), 
               cus.getAge(), cus.getPhone(), cus.getJob(), cus.getGender(),
               (cus.getStrId()!= null) ? cus.getId() : null, 
               cus.getId()};
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
         
      try {            
         int result = jdbcUtil.executeUpdate();   // update 문 실행
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
   }
   // 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사
   public boolean existingUser(String userId) throws SQLException {
      String sql = "SELECT count(*) FROM CUSTOMER WHERE id=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query 실행
         if (rs.next()) {
            int count = rs.getInt(1);
            return (count == 1 ? true : false);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource 반환
      }
      return false;
   }
   
   //사용자 삭제
   public int remove(String userId) throws SQLException {
      String sql = "DELETE FROM CUSTOMER WHERE id=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 delete문과 매개 변수 설정

      try {            
         int result = jdbcUtil.executeUpdate();   // delete 문 실행
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
   }
}