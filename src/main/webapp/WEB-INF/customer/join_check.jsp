<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.CustomerDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="customer" class="model.CustomerDTO" scope="page" />
<%-- 자바에서 객체를 생성하는 거랑 비슷  --%>
<jsp:setProperty name="customer" property="customerId" />
<jsp:setProperty name="customer" property="name" />   
<jsp:setProperty name="customer" property="gender" /> 
<jsp:setProperty name="customer" property="age" /> 
<jsp:setProperty name="customer" property="job" /> 
<jsp:setProperty name="customer" property="phone" /> 
<jsp:setProperty name="customer" property="password" /> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
   <% //회원가입 액션처리 
	   String userID=null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		if(userID!=null){
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 되어있습니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");	
		}   
   		// customer용 회원가입 창에서 null값 있을 경우 에러 뜨고 없을 경우 DB에 저장
      if (customer.getId() == null || customer.getPassword() == null || customer.getName() == null
         || customer.getGender() == 0 || customer.getPhone() == null || customer.getAge() == 0){
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('입력이 안 된 사항이 있습니다.')");
         script.println("history.back()");
         script.println("</script>");
      }
      else { 
         CustomerDAO customerDAO = new CustomerDAO();
         int result = customerDAO.create(customer); // 1 --> 삽입 성공, -2 --> 삽입 실패
         if (result == 1) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('회원가입이 완료되었습니다.')");
            script.println("location.href = 'main.jsp'");
            script.println("</script>");
         }
         else if(result == -2){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()"); // 이전 상황으로 되돌리기
            script.println("</script>");
         }   
      }
   %>
</body>
</html>