<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form  method="POST" action="<c:url value='/customer/register/form' />">
<input type="text" name="customerId" placeholder="id">
<input type="text" name="name" placeholder="name">
<input type="text" name="gender" placeholder="gender">
<input type="text" name="age" placeholder="age">
<input type="text" name="job" placeholder="job">
<input type="text" name="phone" placeholder="phone">
<input type="text" name="password" placeholder="password">
<button type="submit">등록</button>
</form>
</body>
</html>